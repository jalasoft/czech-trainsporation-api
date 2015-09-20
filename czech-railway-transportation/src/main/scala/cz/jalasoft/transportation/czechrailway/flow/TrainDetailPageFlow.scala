package cz.jalasoft.transportation.czechrailway.flow

import java.net.URI
import java.text.SimpleDateFormat
import java.util.Date

import cz.jalasoft.net.http.HttpClient
import cz.jalasoft.net.http.URIBuilder._
import cz.jalasoft.transportation.Transport
import cz.jalasoft.transportation.czechrailway.ConnectionProperties._
import cz.jalasoft.transportation.czechrailway.Loggable
import cz.jalasoft.transportation.czechrailway.page._
import cz.jalasoft.transportation.exception.{TransportInfoRetrievalException, TransportRetrievalException}

import scala.collection._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}

object TrainDetailPageFlow {

  private val DATE_PATTERN = "dd.MM.YYYY"
  private val TRAIN_LOOKUP_REQUEST_PAYLOAD_PATTERN = "Mask=%s&form-date=%s&cmdSearch=Vyhledat"

  private lazy val LOOKUP_URL = http
    .host(host)
    .port(port)
    .path(lookupTrainPath)
    .build

  private def trainDetailUri(path : String) : URI = {
    http.host(host)
    .port(port)
    .path(path)
    .build
  }

  //private lazy val client = new NettyHttpClient

  private def lookupTrainRequestBody(train : String) = {
    val formattedDate = new SimpleDateFormat(DATE_PATTERN).format(new Date());

    s"Mask=${train}&form-date=${formattedDate}&cmdSearch=Vyhledat"
  }

  def apply(client : HttpClient) = new TrainDetailPageFlow(client)

}

/**
 * Created by Honza Lastovicka on 11.7.15.
 */
final class TrainDetailPageFlow private (client : HttpClient) extends Loggable {

  def flow(train : Transport) : TrainDetailPage = {
    debug(s"Startin loading train detail page for train ${train.fullName()}")

    val singletonSeq = flow(train.fullName())

    if (singletonSeq.size != 1) {
      val message = "Unexpected number of train detail pages: " + singletonSeq.size + ", expected 1"
      throw new TransportInfoRetrievalException(train, message)
    }

    singletonSeq head
  }

  def flow(train : String) : Seq[TrainDetailPage] = {
    debug(s"Starting loading train details for train ${train}")

    searchTrain(train) match {
      case Success(p: TrainDetailPage) => Seq(p)
      case Success(p: MultipleTrainsPage) => loadMultipleTrainDetails(train, p)
      case Success(p: NoTrainPage) => Seq()
      case Success(p: UnknownPage) => throw new TransportRetrievalException(train, "Unknown page")
      case Failure(e : Exception) => throw new TransportRetrievalException(train, e)
      case Failure(e) => throw e
    }
  }

  private def searchTrain(train : String) : Try[Page] = {
    val response = sendLookupRequest(train)
    debug("Loading train detail responded with code " + response.getStatusCode)

    response match {
      case ok if response.isStatusOK => Success(Page(response.getContentAsString))
      case _ => Failure(new TransportRetrievalException(train, "An error occurred during retrieving response for train search request: " + response.getStatusCode + ": " + response.getReason))
    }
  }

  private def sendLookupRequest(train : String) = client
    .postRequest()
    .to(TrainDetailPageFlow.LOOKUP_URL)
    .withFormParametersPayload(TrainDetailPageFlow.lookupTrainRequestBody(train))
    .send

  private def loadMultipleTrainDetails(train : String, trainsPage : MultipleTrainsPage) : Seq[TrainDetailPage] = {
    val urls : Seq[(String, String)] = trainsPage.trainsHostsAndPaths
    debug("Starting loading trains details for " + urls.size + " pages")

    val triedPages : Seq[Try[TrainDetailPage]] =
      urls.map({
        case (path, params) => Future { loadTrainDetailPage(train, path, params)}
      }).map(Await.result(_, Duration.Inf))

    triedPages collect { case Failure(e) => error("Retrieving of a page detailed ended with an error", e)}

    triedPages collect {
      case Success(p) if p.isCzechRailwayCompany => p
    }
  }

  private def loadTrainDetailPage(train : String, path: String, params : String) : Try[TrainDetailPage] = {

      debug("Starting loading trains detail page for url params " + params)

      val response = sendTrainDetailRequest(path, params)
      debug("Trains info page retrieved with status code " + response.getStatusCode)

      if (!response.isStatusOK) {
        Failure(new TransportRetrievalException(train, "An error occurred during retrievig train detail response for train " + train + ": " + response.getStatusCode + ": " + response.getReason))
      }

      Page(response.getContentAsString) match {
        case p : TrainDetailPage => Success(p)
        case _ => Failure(new TransportRetrievalException(train, "An error occurred during retrieving train detail for train " + train + ": unknown format of train detail page."))
      }
  }

  private def sendTrainDetailRequest(path : String, params : String) = client.getRequest
    .to(TrainDetailPageFlow.trainDetailUri(path))
    .rawUrlParameters(params)
    .send()

}

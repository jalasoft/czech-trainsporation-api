package cz.jalasoft.transportation.czechrailway.flow

import java.net.URI
import java.text.SimpleDateFormat
import java.util.Date

import cz.jalasoft.net.http.HttpClient
import cz.jalasoft.net.http.URIBuilder._
import cz.jalasoft.transportation.czechrailway.page.{MultipleTrainsPage, Page, TrainDetailPage}
import cz.jalasoft.transportation.czechrailway.{ConfigurationProperties, Loggable}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}
import scala.collection._
import scala.concurrent.duration._

object TrainDetailPageFlow extends ConfigurationProperties {

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

  def apply(train : String, client : HttpClient) = new TrainDetailPageFlow(train, client)
}

/**
 * Created by honzales on 11.7.15.
 */
class TrainDetailPageFlow private (train : String, client : HttpClient) extends Loggable {

  def loadTrainsDetail : Seq[TrainDetailPage] = {
    debug(s"Starting loading train details for train ${train}")

    val triedTrainDetail = loadTrainDetail

    triedTrainDetail match {
      case Failure(e) => throw e //TODO throw transportation specific exception
      case Success(p: TrainDetailPage) => Seq(p)
      case Success(p: MultipleTrainsPage) => loadMultipleTrainDetails(p)
    }
  }

  private def loadTrainDetail : Try[Page] = {
    val response = sendLookupRequest(train)
    debug("Loading train detail responded with code " + response.getStatusCode)

    response match {
      case ok if response.isStatusOK => Success(Page(response.getContentAsString))
      case _ => Failure(new RuntimeException(response.getReason))
    }
  }

  private def sendLookupRequest(train : String) = client
    .postRequest()
    .to(TrainDetailPageFlow.LOOKUP_URL)
    .withFormParametersPayload(TrainDetailPageFlow.lookupTrainRequestBody(train))
    .send

  private def loadMultipleTrainDetails(trainsPage : MultipleTrainsPage) : Seq[TrainDetailPage] = {
    val urls : Seq[(String, String)] = trainsPage.trainsHostsAndPaths
    debug("Starting loading trains details for " + urls.size + " pages")

    val runningLoadings : Seq[Future[TrainDetailPage]] =
      urls.map({
        case (path, params) => Future { loadTrainDetailPage(path, params)}
      })

    for(future <- runningLoadings) yield {
      Await.result(future, Duration.Inf)
    }
  }

  private def loadTrainDetailPage(path: String, params : String) : TrainDetailPage = {
    try {
      debug("Starting loading trains detail page for url params " + params)

      val response = sendTrainDetailRequest(path, params)
      debug("Trains info page retrieved with status code " + response.getStatusCode)

      Page(response.getContentAsString) match {
        case p : TrainDetailPage => p
      }
    } catch {
      case exc : Exception => {
        error("An error occurred during retrieving information for url params, reason: {}, params: {}", exc.getMessage, params)
        throw exc
      }
    }
  }

  private def sendTrainDetailRequest(path : String, params : String) = client.getRequest
    .to(TrainDetailPageFlow.trainDetailUri(path))
    .rawUrlParameters(params)
    .send()

}

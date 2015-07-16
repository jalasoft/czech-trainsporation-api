package cz.jalasoft.transportation.czechrailway

import java.net.URI
import java.text.SimpleDateFormat
import java.util.Date

import cz.jalasoft.net.http.HttpClient
import cz.jalasoft.net.http.URIBuilder._
import cz.jalasoft.net.http.netty.NettyHttpClient
import cz.jalasoft.transportation.czechrailway.page.{Page, MultipleTrainsPage, TrainDetailPage}

import scala.util.{Success, Failure, Try}


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

  def from(train : String) = new TrainDetailPageFlow(train, new NettyHttpClient)
}

/**
 * Created by honzales on 11.7.15.
 */
class TrainDetailPageFlow private (train : String, client : HttpClient) extends Loggable {

  def loadTrainsDetail : Try[Seq[TrainDetailPage]] = {
    debug(s"Starting loading train details for train ${train}")

    val triedTrainDetail = loadTrainDetail

    try {
      triedTrainDetail match {
        case Failure(e) => Failure(e)
        case Success(p: TrainDetailPage) => Success(Seq(p))
        case Success(p: MultipleTrainsPage) => loadTrainDetails(p)
      }
    } finally {
      debug("Closing Http client")
      client.close()
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

  private def loadTrainDetails(trainsPage : MultipleTrainsPage) : Try[Seq[TrainDetailPage]] = {
    val urls = trainsPage.trainsHostsAndPaths
    debug("Starting loading trains details for " + urls.size + " pages")

    var result = Nil

    for((path, params) <- urls) yield {
      val maybePage = loadTrainDetailPage(path, params)
      maybePage match {
        case Failure(exc) => Failure(exc)
        case Success(page) => result :+ page
      }
    }
    Success(result)
  }

  private def loadTrainDetailPage(path: String, params : String) : Try[TrainDetailPage] = {
    try {
      debug("Starting loading trains detail page for url params " + params)

      val response = sendTrainDetailRequest(path, params)
      debug("Trains info page retrieved with status code " + response.getStatusCode)

      Page(response.getContentAsString) match {
        case p : TrainDetailPage => Success(p)
      }
    } catch {
      case exc : Exception => {
        error("An error occurred during retrieving information for url params, reason: {}, params: {}", exc.getMessage, params)
        Failure(exc)
      }
    }
  }

  private def sendTrainDetailRequest(path : String, params : String) = client.getRequest
    .to(TrainDetailPageFlow.trainDetailUri(path))
    .rawUrlParameters(params)
    .send()

}

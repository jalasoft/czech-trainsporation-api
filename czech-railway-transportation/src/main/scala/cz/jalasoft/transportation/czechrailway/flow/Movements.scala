package cz.jalasoft.transportation.czechrailway.flow

import cz.jalasoft.transportation.czechrailway.page._
import cz.jalasoft.transportation.exception.TransportRetrievalException

import scala.util.{Failure, Success, Try}

/**
 * Created by honzales on 30.7.15.
 */
object Movements {

  val multipleTrains : PartialFunction[Try[Page], Try[Seq[TrainDetailPage]]] = {
    case Success(page : MultipleTrainsPage) => Success(Seq())
  }

  val singleTrain : PartialFunction[Try[Page], Try[Seq[TrainDetailPage]]] = {
    case Success(page : TrainDetailPage) => Success(Seq(page))
  }

  val noTrain : PartialFunction[Try[Page], Try[Seq[TrainDetailPage]]] = {
    case Success(page : NoTrainPage) => Success(Seq())
  }

  val failureDueToUnknownPage : PartialFunction[Try[Page], Try[Seq[TrainDetailPage]]] = {
    case Success(page : UnknownPage) => Failure(new RuntimeException())
  }

  val failureDueToFailedPage : PartialFunction[Try[Page], Try[Seq[TrainDetailPage]]] = {
    case Failure(e) => Failure(e)
  }
}

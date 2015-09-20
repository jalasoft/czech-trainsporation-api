package cz.jalasoft.transportation.czechrailway.page

import cz.jalasoft.util.text.TextFragment
import scala.collection.JavaConversions._

final class SchedulePageFragment(fragment : TextFragment) {

  private val FIRST_STOP_FRAGMENT_BEGIN = "<span class=\"connectt\">"
  private val FIRST_STOP_FRAGMENT_END = "</span>"
  private val REGULAR_STOP_FRAGMENT_BEGIN = "<span class=\"connectm content\">"
  private val REGULAR_STOP_FRAGMENT_END = "</span>"
  private val LAST_STOP_FRAGMENT_BEGIN = "<span class=\"connectb\">"
  private val LAST_STOP_FRAGMENT_END = "</span>"
  
  def firstStation : TrainStopPageFragment = {
    val firstStops = fragment.findFragmentsBetween(FIRST_STOP_FRAGMENT_BEGIN, FIRST_STOP_FRAGMENT_END)
    new TrainStopPageFragment(firstStops.first())
  }

  def regularStations : Seq[TrainStopPageFragment] = {
    val regularStops = fragment.findFragmentsBetween(REGULAR_STOP_FRAGMENT_BEGIN, REGULAR_STOP_FRAGMENT_END);
    regularStops.map { new TrainStopPageFragment(_)}.toSeq
  }

  def lastStation : TrainStopPageFragment = {
    val lastStops = fragment.findFragmentsBetween(LAST_STOP_FRAGMENT_BEGIN, LAST_STOP_FRAGMENT_END)
    new TrainStopPageFragment(lastStops.first)
  }
}

class TrainStopPageFragment(fragment : TextFragment) {

  private val PATTERN = "<span class=\"right tright w60\">(.*)</span>\\s<span class=\"right tright w60\">(.*)</span>\\s(.*)\\s</span>"

  private lazy val matcher = {
    val result = fragment.findFragmentsMatching(PATTERN)
    result
  }

  def station : String = {
    val k = matcher.get(3)
    k.text()
  }
}

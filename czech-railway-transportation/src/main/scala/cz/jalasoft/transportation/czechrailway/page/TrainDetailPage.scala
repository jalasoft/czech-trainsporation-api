package cz.jalasoft.transportation.czechrailway.page

import java.util.Optional

import cz.jalasoft.util.text.{RegexFragment, TextFragment}

/**
 * Created by honzales on 12.7.15.
 */
final class TrainDetailPage(fragment : TextFragment) extends Page(fragment) {

  private val TRAIN_NUMBER_SECTION_BEGIN = """<div class="group map">"""
  private val TRAIN_NUMBER_SECTION_END = """Na mapě</a></span>"""
  private val TRAIN_NUMBER_PATTERN = "<span class=\"train\"><strong>((.*) (\\((.*)\\))?)</strong></span>";
  private val TRAIN_NUMBER_PATTERN3 = "<span class=\"train\"><strong>(.*)</strong></span>"
  private val CZECH_RAILWAY_CARRIER_NAME = "<a href=\"http://www.cd.cz\" target=\"_blank\">České dráhy, a.s.</a>"
  private val SCHEDULE_SECTION_BEGIN = "<h2>Jízdní řád</h2>"
  private val SCHEDULE_SECTION_END = "<br /><br class=\"reset\" />"

  private lazy val trainNumberFragment : RegexFragment = readTrainNumberFragment
  private lazy val checkCzechRailwayCarrierPresence = fragment.text().contains(CZECH_RAILWAY_CARRIER_NAME)

  private def readTrainNumberFragment = {
    val section = fragment.findFragmentsBetween(TRAIN_NUMBER_SECTION_BEGIN, TRAIN_NUMBER_SECTION_END).first()
    val result = section.findFragmentsMatching(TRAIN_NUMBER_PATTERN)

    result.first
  }

  def isCzechRailwayCompany : Boolean = {
    checkCzechRailwayCarrierPresence
  }

  def trainFullName(): String = trainNumberFragment.getGroupTextFragment(0).text()

  def trainName(): Optional[String] = {
    val name = trainNumberFragment.getGroupTextFragment(3).text
    name match {
      case null => Optional.empty()
      case someName => Optional.of(someName)
    }
  }

  def trainCode(): String = trainNumberFragment.getGroupTextFragment(1).text

  def providerName(): String = "České dráhy, a.s."

  def schedule() : SchedulePageFragment = {
    val fragments = fragment.findFragmentsBetween(SCHEDULE_SECTION_BEGIN, SCHEDULE_SECTION_END)

    new SchedulePageFragment(fragments.first())
  }
}

package cz.jalasoft.transportation.czechrailway.page

/**
 * Created by honzales on 12.7.15.
 */
trait TrainDetailPage extends Page {

  private val TRAIN_NUMBER_SECTION_BEGIN = """<div class="group map">"""
  private val TRAIN_NUMBER_SECTION_END = """Na mapě</a></span>"""
  private val TRAIN_NUMBER_PATTERN = """<span class="train"><strong>(.*)</strong></span>"""

  lazy val trainNumber : String = readTrainNumber

  private def readTrainNumber : String = {
    val section = fragment.findFragmentsBetween(TRAIN_NUMBER_SECTION_BEGIN, TRAIN_NUMBER_SECTION_END).first()
    val regexFragment = section.findFragmentsMatching(TRAIN_NUMBER_PATTERN).first()

    val value = regexFragment.getGroupTextFragment(0).text
    value
  }

}

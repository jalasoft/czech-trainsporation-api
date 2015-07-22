package cz.jalasoft.transportation.czechrailway.page

import java.util.Optional

import cz.jalasoft.transportation.Transport
import cz.jalasoft.util.text.{TextFragment, RegexFragment}

/**
 * Created by honzales on 12.7.15.
 */
trait TrainDetailPage extends Page with Transport {

  private val TRAIN_NUMBER_SECTION_BEGIN = """<div class="group map">"""
  private val TRAIN_NUMBER_SECTION_END = """Na mapě</a></span>"""
  private val TRAIN_NUMBER_PATTERN = "<span class=\"train\"><strong>((.*) \\((.*)\\))</strong></span>";
 private val TRAIN_NUMBER_PATTERN3 = "<span class=\"train\"><strong>(.*)</strong></span>"

  private lazy val trainNumberFragment : RegexFragment = readTrainNumberFragment

  private def readTrainNumberFragment = {
    val section = fragment.findFragmentsBetween(TRAIN_NUMBER_SECTION_BEGIN, TRAIN_NUMBER_SECTION_END).first()
    val result = section.findFragmentsMatching(TRAIN_NUMBER_PATTERN)

    result.first
  }

  override def fullIdentification(): String = trainNumberFragment.getGroupTextFragment(0).text()

  override def name(): Optional[String] = {
    val name = trainNumberFragment.getGroupTextFragment(2).text
    name match {
      case null => Optional.empty()
      case someName => Optional.of(someName)
    }
  }

  override def code(): String = trainNumberFragment.getGroupTextFragment(1).text

  override def carrierName(): String = ???


}

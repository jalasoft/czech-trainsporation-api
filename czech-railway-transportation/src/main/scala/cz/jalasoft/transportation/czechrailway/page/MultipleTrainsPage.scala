package cz.jalasoft.transportation.czechrailway.page

import cz.jalasoft.transportation.czechrailway.ConfigurationProperties
import cz.jalasoft.util.text.FragmentConverters._
import scala.collection.JavaConversions._

/**
 * Created by honzales on 8.7.15.
 */
trait MultipleTrainsPage extends Page with ConfigurationProperties {

  private val TRAIN_LINK_PATTERN = "<a href=\"(.*)\\?(.*)\" class=\"train thin\""
  //private val TRAIN_LINK_PATTERN = """<a href="(.*)\\?(.*)" class="train thin""""

  def trainsHostsAndPaths : Seq[(String, String)] = {
    val findings = fragment.findFragmentsMatching(TRAIN_LINK_PATTERN)

    val iterable = for(occurrence <- findings) yield {
      val path = occurrence.getGroupTextFragment(0).text
      val params = occurrence.getGroupTextFragment(1).convert(toHtmlEscaped).text

      (path, params)
     }
     iterable.toSeq
  }
  
}

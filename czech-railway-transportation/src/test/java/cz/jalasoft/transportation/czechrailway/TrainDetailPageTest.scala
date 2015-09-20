package cz.jalasoft.transportation.czechrailway

import cz.jalasoft.transportation.czechrailway.page.{TrainDetailPage, Page}
import org.junit.{Assert, Test, Before}

import scala.io.Source

/**
 * Created by honzales on 20.7.15.
 */
class TrainDetailPageTest {

  @Before
  private val page = {
    val text = Source.fromInputStream(ClassLoader.getSystemResourceAsStream("train_detail_page.txt")).mkString
    Page(text) match {
      case p : TrainDetailPage => p
    }
  }

  @Test
  def getsExpectedTrainNumber = {
    val number = page.trainCode()
    Assert.assertEquals("R 844 ", number)
  }

  @Test
  def pokus = {
    page.
  }
}

package cz.jalasoft.transportation.czechrailway

import cz.jalasoft.transportation.czechrailway.page.{TrainDetailPage, Page}
import org.testng.Assert
import org.testng.annotations.{BeforeTest, Test}

import scala.io.Source

/**
 * Created by honzales on 20.7.15.
 *
 */
class TrainDetailPageTest {

  @BeforeTest
  private val page = {
    val text = Source.fromInputStream(ClassLoader.getSystemResourceAsStream("train_detail_page.txt")).mkString
    Page(text) match {
      case p : TrainDetailPage => p
    }
  }

  @Test(enabled = false)
  def getsExpectedTrainNumber = {
    val number = page.trainCode()
    Assert.assertEquals("R 844 ", number)
  }
}

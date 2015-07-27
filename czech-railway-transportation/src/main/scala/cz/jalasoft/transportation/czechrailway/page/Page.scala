package cz.jalasoft.transportation.czechrailway.page

import cz.jalasoft.util.text.{Fragment, TextFragment}

/**
 * Created by honzales on 8.7.15.
 */
case class Page protected(fragment : TextFragment) {

  require(fragment != null, "Text fragment must not be null")
}

object Page {

  private val MULTIPLE_TRAINS_PAGE_HINT = "<div id=\"header\">Nalezené vlaky</div>"
  private val NO_TRAIN_HINT = "Vlak nebyl nalezen."
  private val TRAIN_PAGE_HINT = "<div id=\"header\">Detail vlaku</div>";

  def apply(text: String) : Page = {
    val fragment = Fragment.fromText(text);

    text match {
      case _ if text.contains(MULTIPLE_TRAINS_PAGE_HINT) => new MultipleTrainsPage(fragment)
      case _ if text.contains(NO_TRAIN_HINT) => new NoTrainPage(fragment)
      case _ if text.contains(TRAIN_PAGE_HINT) => new TrainDetailPage(fragment)
      case _ => new UnknownPage(fragment)
    }
  }
}

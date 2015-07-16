package cz.jalasoft.transportation.czechrailway.page

import cz.jalasoft.util.text.{Fragment, TextFragment}

/**
 * Created by honzales on 8.7.15.
 */
trait Page {

  val fragment : TextFragment;

  protected def get[T](f: TextFragment => T) : T = f(fragment)


}

object Page {

  private val MULTIPLE_TRAINS_PAGE_HINT = "<div id=\"header\">Nalezené vlaky</div>"
  private val TRAIN_PAGE_HINT = """<title>Informace o vlaku | České dráhy, a.s.</title>""";

  def apply(text: String) : Page = {
    val fragment = Fragment.fromText(text);

    text match {
      case content1 if text.contains(MULTIPLE_TRAINS_PAGE_HINT) =>
        new UnknownPage(fragment) with MultipleTrainsPage

      case content2 if text.contains(TRAIN_PAGE_HINT) =>
        new UnknownPage(fragment) with TrainDetailPage
    }
  }
}

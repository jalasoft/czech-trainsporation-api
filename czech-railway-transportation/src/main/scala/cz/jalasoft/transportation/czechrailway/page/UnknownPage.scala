package cz.jalasoft.transportation.czechrailway.page

import cz.jalasoft.util.text.TextFragment

/**
 * Created by honzales on 9.7.15.
 */
case class UnknownPage(override val fragment : TextFragment) extends Page {

  require(fragment != null, "Text fragment must not be null")

}

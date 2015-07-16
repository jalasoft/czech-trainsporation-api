package cz.jalasoft.transportation.czechrailway

import org.slf4j.LoggerFactory

/**
 * Created by honzales on 15.7.15.
 */
trait Loggable {

  private val logger = LoggerFactory.getLogger(getClass)

  protected def debug(msg : String) = logger.debug(msg)
  protected def debug(msg : String, params : java.lang.Object*) = logger.debug(msg, params)
  protected def error(msg : String) = logger.error(msg)
  protected def error(msg : String, params : java.lang.Object*) = logger.error(msg, params)
}

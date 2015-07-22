package cz.jalasoft.transportation.czechrailway

import org.slf4j.LoggerFactory

/**
 * Created by honzales on 15.7.15.
 */
trait Loggable {

  private val logger = LoggerFactory.getLogger(getClass)

  protected def debug(msg : => String) : Unit = {
    if (logger.isDebugEnabled) {
      logger.debug(msg)
    }
  }

  protected def debug(msg : => String, params : java.lang.Object*) : Unit = {
    if (logger.isDebugEnabled()) {
      logger.debug(msg, params)
    }
  }

  protected def error(msg : => String) : Unit = {
    if (logger.isErrorEnabled()) {
      logger.error(msg)
    }
  }

  protected def error(msg : => String, params : java.lang.Object*) : Unit = {
    if (logger.isErrorEnabled()) {
      logger.error(msg, params)
    }
  }
}

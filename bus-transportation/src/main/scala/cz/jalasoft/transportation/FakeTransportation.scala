package cz.jalasoft.transportation

import java.util

import org.slf4j.LoggerFactory

/**
 * Created by honzales on 28.6.15.
 */
class FakeTransportation extends Transportation {

  private val LOGGER = LoggerFactory.getLogger(classOf[FakeTransportation])
  private val fakeCarrier = new FakeCarrier
  
  LOGGER.debug("Fake transportation has been instantiated")

  override def lookupTransport(transport: String): util.Collection[Transport] = {
    LOGGER.debug("Transport is being lokked up for " + transport)
    null
  }

  override def querySchedule(transport: Transport): Schedule = {
    LOGGER.debug("Schedule is being queried for transport {}.", transport)
    null
  }

  override def carrier(): Carrier = fakeCarrier

  override def queryPosition(transport: Transport): Position = {
    LOGGER.debug("Position is being queried for transport " + transport)
    null
  }
}

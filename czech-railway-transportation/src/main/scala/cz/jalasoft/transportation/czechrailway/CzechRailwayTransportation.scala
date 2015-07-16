package cz.jalasoft.transportation.czechrailway

import cz.jalasoft.net.http.netty.NettyHttpClient
import cz.jalasoft.transportation._

/**
 * Created by honzales on 28.6.15.
 */
class CzechRailwayTransportation extends Transportation with Loggable {

  private val httpClient = new NettyHttpClient();

  debug("Fake transportation has been instantiated")

  override def lookupTransport(transport: String): java.util.Collection[Transport] = {
    debug("Transport is being lokked up for " + transport)


    null
  }

  override def querySchedule(transport: Transport): Schedule = {
    debug("Schedule is being queried for transport {}.", transport)
    null
  }

  override def carrier(): Carrier = CzechRailwayCarrier

  override def queryPosition(transport: Transport): Position = {
    debug("Position is being queried for transport " + transport)
    null
  }
}

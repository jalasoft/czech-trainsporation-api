package cz.jalasoft.transportation.czechrailway

import cz.jalasoft.net.http.netty.NettyHttpClient
import cz.jalasoft.transportation._
import cz.jalasoft.transportation.czechrailway.flow.TrainDetailPageFlow

/**
 * Created by honzales on 28.6.15.
 */
class CzechRailwayTransportation extends Transportation with Loggable {

  debug("Czech railway transportation has been instantiated")

  override def lookupTransport(transport: String): java.util.Collection[Transport] = {
    require(transport != null && !transport.isEmpty, "Train namemust not be null or empty")

    debug("Transport is being lokked up for " + transport)

    val client = new NettyHttpClient

    try {
      val result = TrainDetailPageFlow(transport, client).loadTrainsDetail
    } finally {
      client.close()
    }

    new java.util.ArrayList[Transport]
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

package cz.jalasoft.transportation.czechrailway

import cz.jalasoft.net.http.netty.NettyHttpClient
import cz.jalasoft.transportation._
import cz.jalasoft.transportation.czechrailway.flow.TrainDetailPageFlow
import cz.jalasoft.transportation.czechrailway.page.TrainDetailPage

import scala.collection.JavaConversions._

/**
 * Created by honzales on 28.6.15.
 */
class CzechRailwayTransportation extends Transportation with Loggable {

  debug("Czech railway transportation has been instantiated")

  private val trainPageToTransport : TrainDetailPage => Transport = {
    p => {
      Transport
        .newTransport
        .carrier(p.providerName)
        .code(p.trainCode)
        .name(p.trainName.orElse(null))
        .get()
    }
  }

  override def findTransport(transport: String): java.util.Collection[Transport] = {
    require(transport != null && !transport.isEmpty, "Train name must not be null or empty")

    debug("Transport is being looked up for " + transport)

    val client = new NettyHttpClient

    try {
      TrainDetailPageFlow(client) flow(transport) map trainPageToTransport
    } finally {
      client.close()
    }
  }

  override def querySchedule(transport: Transport): Schedule = {
    require(transport != null, "Transport must not be null")

    debug("Schedule is being queried for transport {}.", transport)

    val client = new NettyHttpClient

    try {
      val page = (TrainDetailPageFlow(client) flow(transport))
      val schedule = page.schedule()

    } finally {
      client.close()
    }
    null
  }


  override def queryPosition(transport: Transport): Position = {
    debug("Position is being queried for transport " + transport)
    null
  }

  override def carrier(): Carrier = CzechRailwayCarrier

}

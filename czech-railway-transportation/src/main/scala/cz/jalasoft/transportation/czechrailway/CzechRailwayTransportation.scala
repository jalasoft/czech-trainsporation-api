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
        .providedBy(p.providerName)
        .withCode(p.trainCode)
        .named(p.trainName.orElse(null))
        .get()
    }
  }

  override def lookupTransport(transport: String): java.util.Collection[Transport] = {
    require(transport != null && !transport.isEmpty, "Train name must not be null or empty")

    debug("Transport is being looked up for " + transport)

    val client = new NettyHttpClient

    try {
      TrainDetailPageFlow(transport, client).flow map trainPageToTransport
    } finally {
      client.close()
    }
  }

  override def querySchedule(transport: Transport): Schedule = {
    require(transport != null, "Transport must not be null")

    debug("Schedule is being queried for transport {}.", transport)

    /*transport match {
      case t : TrainDetailPage => t
      case _ => {
        val details = lookupTransport(transport fullIdentification())
        if (details.size != 0) {
          val message = "Unexpected number of train detail pages (" + details.size + ") for train " + transport + "."
          throw new TransportInfoRetrievalException(transport, message)
        }
        details.iterator.next
      }
    }*/
    null
  }

  override def carrier(): Carrier = CzechRailwayCarrier

  override def queryPosition(transport: Transport): Position = {
    debug("Position is being queried for transport " + transport)
    null
  }
}

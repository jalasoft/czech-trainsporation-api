package cz.jalasoft.transportation.czechrailway

import cz.jalasoft.util.configuration.annotation.{Configuration, Key, SourceType}
import cz.jalasoft.util.configuration.{Configuration => ObjectConfig}

trait ConfigurationProperties {

  private lazy val config = new ObjectConfig().instantiate(classOf[ConnectionProperties])

  protected def host: String = config.host
  protected def port: Int = config.port
  protected def lookupTrainPath: String = config.lookupTrainPath
}

/**
 * Created by honzales on 7.7.15.
 */
@Configuration(
  source = SourceType.CLASSPATH,
  name = "configuration.properties")
trait ConnectionProperties {

  @Key("server_host")
  def host: String

  @Key("server_port")
  def port: Int

  @Key("train_info_path")
  def lookupTrainPath: String
}

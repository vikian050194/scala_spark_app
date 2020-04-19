import org.apache.spark
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Minutes, StreamingContext}

trait TrafficSniffer{
  def gotPacket(size: Int)
}

class TestTrafficSniffer (val handler: Int => Unit,val values: Iterator[Int]) extends TrafficSniffer {
  def getNextValue(): Int = values.next()

  def handleNewPacket(): Unit ={
    gotPacket(getNextValue())
  }

  override def gotPacket(size: Int): Unit = {
    handler(size)
  }
}

class LiveTrafficSniffer extends TrafficSniffer{
  override def gotPacket(size: Int): Unit = ???
}

trait Producer{
  def sendMessage(value: String)
}

class TestProducer (val handler: String => Unit) extends Producer {
  override def sendMessage(value: String): Unit = {
    handler(value)
  }
}

class LiveProducer extends Producer{
  import java.util.Properties
  import org.apache.kafka.clients.producer._

  val sparkConf = new SparkConf().setAppName("scala_spark_app")
  val ssc = new StreamingContext(sparkConf, Minutes(5))

//  df = spark.streaming.dstream.where("age > 21").select("name.first").show()

  ssc.start()
  ssc.awaitTermination()

  val TOPIC="alerts"
//
//  val  props = new Properties()
//  props.put("bootstrap.servers", "localhost:2181")
//  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
//  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
//
//  val producer = new KafkaProducer[String, String](props)
//
////  override def finalize(): Unit = {
////    producer.close()
////    super.finalize()
////  }

  override def sendMessage(value: String): Unit = {
    val record = new ProducerRecord(TOPIC, "key", "out of limit")
//    producer.send(record)
  }
}



object ProducerApp extends App {

}
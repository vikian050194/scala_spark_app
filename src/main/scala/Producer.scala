trait TrafficSniffer{
  def gotPacket(size: Int)
}

class TestTrafficSniffer (val handler: Int => Unit,val values: Iterator[Int]) extends TrafficSniffer {
//  val newPacketHandler = this.handler;
  def getNextValue(): Int = values.next()

  def handleNewPacket(): Unit ={
    gotPacket(getNextValue())
  }

  override def gotPacket(size: Int): Unit = {
    handler(size)
  }
}

object Producer extends App {
  import java.util.Properties

  import org.apache.kafka.clients.producer._

  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:2181")

  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    val TOPIC="alerts"

    for(i<- 1 to 50){
      val record = new ProducerRecord(TOPIC, "key", s"hello $i")
      producer.send(record)
    }

    val record = new ProducerRecord(TOPIC, "key", "the end "+new java.util.Date)
    producer.send(record)

    producer.close()
}

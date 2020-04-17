import java.util
import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._

import java.util.Properties

object Consumer extends App{

    val TOPIC="alerts"

    val  props = new Properties()
    props.put("bootstrap.servers", "localhost:2181")

    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "something")

    val consumer = new KafkaConsumer[String, String](props)

    consumer.subscribe(util.Collections.singletonList(TOPIC))

    while(true){
      val records=consumer.poll(100)
      for (record<-records.asScala){
        println(record)
      }
    }
  }

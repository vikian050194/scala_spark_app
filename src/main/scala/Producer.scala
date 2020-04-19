import java.util.{Spliterator, Spliterators}
import java.util.stream.StreamSupport

import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}

import scala.util.Random

//trait TrafficSniffer{
//  def gotPacket(size: Int)
//}
//
//class TestTrafficSniffer (val handler: Int => Unit,val values: Iterator[Int]) extends TrafficSniffer {
//  def getNextValue(): Int = values.next()
//
//  def handleNewPacket(): Unit ={
//    gotPacket(getNextValue())
//  }
//
//  override def gotPacket(size: Int): Unit = {
//    handler(size)
//  }
//}

//
//def getRandomStream(): Stream[Int] = {
//  val spliterator = Spliterators.spliteratorUnknownSize(new RandomIterator(), 0);
//
//  // Get a Sequential Stream from spliterator
//  StreamSupport.stream(spliterator, false);
//}

//class RandomTrafficSniffer (val handler: Int => Unit) extends TrafficSniffer {
//  val random = new Random()
//
//  def handleNewPacket(): Unit ={
//    gotPacket(random.nextInt())
//  }
//
//  override def gotPacket(size: Int): Unit = {
//    handler(size)
//  }
//}

//class LiveTrafficSniffer extends TrafficSniffer{
//  override def gotPacket(size: Int): Unit = ???
//}

//trait Producer{
//  def sendMessage(value: String)
//}
//
//class TestProducer (val handler: String => Unit) extends Producer {
//  override def sendMessage(value: String): Unit = {
//    handler(value)
//  }
//}
//
//class LiveProducer extends Producer{
//  import java.util.Properties
//  import org.apache.kafka.clients.producer._
//
//  val sparkConf = new SparkConf().setAppName("scala_spark_app")
//  val ssc = new StreamingContext(sparkConf, Seconds(1))
//
//  val source = (new RandomIterator()).toSeq
//  val rdd = ssc.sparkContext.parallelize(source)
//  val result = rdd.reduceByKey((acc, value) => acc + value)
//  result foreach println
//
//  ssc.start()
//  ssc.awaitTermination()
//
//  val TOPIC="alerts"
////
////  val  props = new Properties()
////  props.put("bootstrap.servers", "localhost:2181")
////  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
////  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
////
////  val producer = new KafkaProducer[String, String](props)
//
//  override def sendMessage(value: String): Unit = {
//    val record = new ProducerRecord(TOPIC, "key", "out of limit")
////    producer.send(record)
//  }
//}

class RandomIterator() extends Iterator[(String, Int)]{
  val random = new Random()

  override def hasNext: Boolean = true
  override def next(): (String, Int) = {
    ("127.0.0.1", random.nextInt())
  }
}

object ProducerApp extends App {
  println("start")

  val sparkConf = new SparkConf().setMaster("local[1]").setAppName("scala_spark_app")
  val ssc = new StreamingContext(sparkConf, Seconds(1))
//  val sc = new SparkContext(sparkConf)

  val source = new RandomIterator().toSeq
  val rdd = ssc.sparkContext.parallelize(source)
  val result = rdd.reduceByKey((acc, value) => acc + value).foreach((t) => println(t._2))

  ssc.start()
  ssc.awaitTermination()
}

////trait Producer{
////  def sendMessage(value: String)
////}
////
////class TestProducer (val handler: String => Unit) extends Producer {
////  override def sendMessage(value: String): Unit = {
////    handler(value)
////  }
////}
////
////class LiveProducer extends Producer{
////  import java.util.Properties
////  import org.apache.kafka.clients.producer._
////
////  val sparkConf = new SparkConf().setAppName("scala_spark_app")
////  val ssc = new StreamingContext(sparkConf, Seconds(1))
////
////  val source = (new RandomIterator()).toSeq
////  val rdd = ssc.sparkContext.parallelize(source)
////  val result = rdd.reduceByKey((acc, value) => acc + value)
////  result foreach println
////
////  ssc.start()
////  ssc.awaitTermination()
////
////  val TOPIC="alerts"
//////
//////  val  props = new Properties()
//////  props.put("bootstrap.servers", "localhost:2181")
//////  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
//////  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
//////
//////  val producer = new KafkaProducer[String, String](props)
////
////  override def sendMessage(value: String): Unit = {
////    val record = new ProducerRecord(TOPIC, "key", "out of limit")
//////    producer.send(record)
////  }
////}
//
//class RandomIterator() extends Iterator[(String, Int)]{
//  val random = new Random()
//
//  override def hasNext: Boolean = true
//  override def next(): (String, Int) = {
//    ("127.0.0.1", random.nextInt())
//  }
//}
//
//object ProducerApp extends App {
//  println("start")
//
//  val sparkConf = new SparkConf().setMaster("local[1]").setAppName("scala_spark_app")
//  val ssc = new StreamingContext(sparkConf, Seconds(1))
////  val sc = new SparkContext(sparkConf)
//
//  val source = new RandomIterator().toSeq
//  val rdd = ssc.sparkContext.parallelize(source)
//  val result = rdd.reduceByKey((acc, value) => acc + value).foreach((t) => println(t._2))
//
//  ssc.start()
//  ssc.awaitTermination()
//}

package scala.spark.app

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

import scala.util.Random

object EntryPoint {
  val queue = new java.util.concurrent.ConcurrentLinkedQueue[String]()

  def main(args: Array[String]) = {

    new Thread("Queue Consumer") {
      override def run() {
        while (true) {
          val v = queue.poll()
          if (v != null)
            println("### " + v)
        }
      }
    }.start()


    import org.apache.spark._
    import org.apache.spark.streaming._

    // Create a local StreamingContext with two working thread and batch interval of 1 second.
    // The master requires 2 cores to prevent a starvation scenario.

    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(5))

    val stream = ssc.receiverStream(new MyReceiver(new RandomGenerator()))

    //output to file is the most straightforward, try it
    //stream.reduce(_ + _).saveAsTextFiles("/home/ivan/Dev/Toys/OderskyCourse/SimpleSpark/output/output")

    // foreach RDD is a bit more complicated
    // read about it here https://spark.apache.org/docs/latest/streaming-programming-guide.html#a-quick-example
    // in the 'foreachRDD' section
    stream.reduce(_ + _).foreachRDD(rdd => rdd.foreach(r => output(r)))
    ssc.start()
    ssc.awaitTermination()
  }

  def output(value: Int): Unit = {
    queue.add(s"${Thread.currentThread().getName}: $value")
  }

  class RandomGenerator extends Iterator[Int] with Serializable {
    val random = new Random()

    override def hasNext: Boolean = true

    override def next(): Int = random.nextInt() % 100
  }

  class MyReceiver(iterator: Iterator[Int]) extends Receiver[Int](StorageLevel.MEMORY_AND_DISK_2) {
    override def onStart(): Unit = {
      // Start the thread that receives data over a connection
      new Thread("Socket Receiver") {
        override def run() { receive() }
      }.start()
    }

    private def receive(): Unit = {
      while (true) {
        store(iterator.next())
      }
    }

    override def onStop(): Unit = {}
  }
}
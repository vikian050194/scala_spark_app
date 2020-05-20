//import scala.io.Source
//
//object Sandbox extends App{
//  println("Start")
//
////  class Cash(val value: Double, val currency: String){
////    override def toString: String = s"${value}${currency}"
////  }
////
////  object Cash{
////    def apply(value: Double, currency: String): Cash = new Cash(value, currency)
////
////    def apply(value: Double): Cash = new Cash(value, "USD")
////  }
////
////  val twoDollars = Cash(2)
//
//
//
////  case class Cash(val value: Double, val currency: String){
////    override def toString: String = s"${value}${currency}"
////  }
////  val twoDollars1 = Cash(2, "USD")
////  val twoDollars2 = Cash(2, "USD")
////  val twoEuros = Cash(2, "EUR")
////
////  println(twoDollars1 == twoDollars2)
////  println(twoDollars1 == twoEuros)
////
////  def isDollar(cash: Cash): Boolean =
////    cash.currency match {
////      case "USD" => println("Yeah"); true
////      case _ => println("No"); false
////    }
////
////  isDollar(twoDollars1)
////  isDollar(twoEuros)
//
//
////
////  import java.io.File
////
////  trait FileOps{
////    self: File =>
////
////    def isScalaFile: Boolean = this.getName.endsWith("scala")
////
////    def readText: Iterator[String] =
////      if(this.isScalaFile)
////        Source.fromFile(this).getLines()
////      else
////        Iterator.empty
////
////    def printLines: Unit =
////      this.readText foreach println
////  }
////
//////  val dcFile = new File("./src/main/scala/Hello.scala") with FileOps;
////  val dcFile = new File(".") with FileOps;
////  val files = dcFile.listFiles()
////  println(dcFile.getName)
////  println(dcFile.isScalaFile)
////  dcFile.printLines
//
//
//
////  abstract class Passenger
////
////  class BusinessPassenger extends Passenger
////  class EconomyPassenger extends Passenger
////
////  class CorporatePassenger extends BusinessPassenger
////
////  class AirplaneSeat[-T]
////
////  def reserveForCorporate(airplaneSeat: AirplaneSeat[CorporatePassenger]): Unit =
////    println("Corporate seat is reserved")
////
////  def reserveForBusiness(airplaneSeat: AirplaneSeat[BusinessPassenger]): Unit =
////    println("Business seat is reserved")
////
////  reserveForCorporate(new AirplaneSeat[CorporatePassenger])
////  reserveForCorporate(new AirplaneSeat[BusinessPassenger])
////
////  reserveForBusiness(new AirplaneSeat[BusinessPassenger])
////  reserveForBusiness(new AirplaneSeat[CorporatePassenger])
//
//
//
//
//
//
//
//
//
//
////  import org.pcap4j.core.PacketListener
////  import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode
////  import org.pcap4j.util.NifSelector
////
////  val filter = null
////
////  val nif = new NifSelector().selectNetworkInterface
////  if (nif == null) System.exit(1)
////
////  val handle = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 10)
////
////  //  if (filter != null && (filter.length ne 0)) handle.setFilter(filter, BpfCompileMode.OPTIMIZE)
////
////  val listener = new PacketListener() {
////    def gotPacket(packet: Nothing): Unit = {
////      val sb = new StringBuilder
////      sb.append("A packet captured at ").append(handle.getTimestamp).append(":")
////      System.out.println(sb)
////      //      System.out.println(packet)
////      //      val ipV4Packet = packet.get(classOf[IpV4Packet])
////      //      val dstAddr = ipV4Packet.getHeader.getDstAddr()
////    }
////  }
////
////  handle.loop(5, listener)
//
//  //  val addr = InetAddress.getByName("192.168.0.105")//192.168.10.100
//  //  val nif = Pcaps.getDevByAddress(addr)
//  //
//  //  val snapLen = 65536
//  //  val mode = PromiscuousMode.PROMISCUOUS
//  //  val timeout = 10
//  //  val handle = nif.openLive(snapLen, mode, timeout)
//  //
//  //  val packet = handle.getNextPacketEx()
//  //  handle.close()
//  //
//  //  val ipV4Packet = packet.get(classOf[IpV4Packet])
//  //  val dstAddr = ipV4Packet.getHeader.getDstAddr()
//  //  System.out.println(dstAddr)
//  //  val srcAddr = ipV4Packet.getHeader.getSrcAddr()
//  //  System.out.println(srcAddr)
//
//  println("Finish")
//}

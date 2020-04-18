class ExampleSpec extends org.scalatest.FlatSpec with org.scalatest.matchers.should.Matchers {
  "A TestTrafficSniffer" should "emmit values in right order" in {
    val actualValues = Iterator[Int](1, 2, 3)
    val expectedValues = Iterator[Int](1, 2, 3)

    val sniffer = new TestTrafficSniffer(size => {size should be (expectedValues.next())}, actualValues)

    sniffer.handleNewPacket()
    sniffer.handleNewPacket()
    sniffer.handleNewPacket()
  }
}
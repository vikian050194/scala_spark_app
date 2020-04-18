class UnitTest extends org.scalatest.FlatSpec with org.scalatest.matchers.should.Matchers {
  "A TestTrafficSniffer" should "emmit values in right order" in {
    val actualValues = Iterator[Int](1, 2, 3)
    val expectedValues = Iterator[Int](1, 2, 3)

    val sniffer = new TestTrafficSniffer(size => {size should be (expectedValues.next())}, actualValues)

    sniffer.handleNewPacket()
    sniffer.handleNewPacket()
    sniffer.handleNewPacket()
  }

  "A TestProducer" should "send message" in {
    val actual = "test-alert"
    val expected = "test-alert"

    val producer = new TestProducer(message => {message should be (expected)})

    producer.sendMessage(actual)
  }
}
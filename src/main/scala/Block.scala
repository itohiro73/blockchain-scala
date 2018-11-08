import java.time.LocalDateTime

class Block(val timestamp: LocalDateTime, val data: String, val previousHash: String = "") {
  val hash = this.calculateHash()

  def calculateHash(): String = {
    val value = this.timestamp + this.data + this.previousHash
    HashFunction.sha256(value)
  }

  override def toString: String = {
    "{\n\tTimestamp: " + this.timestamp +
    "\n\tData: " + this.data +
    "\n\tPrevious Hash: " + this.previousHash +
    "\n\tHash: " + this.hash +
    "\n}"
  }
}

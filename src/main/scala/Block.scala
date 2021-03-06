import java.time.LocalDateTime

class Block(val timestamp: LocalDateTime, val transactions: Seq[Transaction], val previousHash: String = "") {
  var hash = this.calculateHash()
  var nonce = 0

  def calculateHash(): String = {
    val value = this.timestamp + this.transactions.mkString + this.previousHash + this.nonce
    HashFunction.sha256(value)
  }

  def mineBlock(difficulty: Int): Unit = {
    while(this.hash.substring(0, difficulty) != Array.fill(difficulty)("0").mkString) {
      this.nonce += 1
      this.hash = this.calculateHash()
    }
  }

  override def toString: String = {
    "{\n\tTimestamp: " + this.timestamp +
    "\n\tTransaction: " + this.transactions.mkString("\n\t\t{\n\t\t\t", "\n\t\t\t", "\n\t\t}") +
    "\n\tPrevious Hash: " + this.previousHash +
    "\n\tHash: " + this.hash +
    "\n\tNonse: " + this.nonce +
    "\n}"
  }
}

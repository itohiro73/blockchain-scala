import java.time.LocalDateTime

class Blockchain {
  var chain = Seq[Block](createGenesisBlock())

  def createGenesisBlock(): Block = {
    new Block(LocalDateTime.now(), "A genesis block.")
  }

  def addBlock(block: Block): Unit = {
    this.chain = this.chain :+ block
  }

  def getLastBlock(): Block = this.chain.last

  def isChainValid(): Boolean = {
    for(i <- 1 until this.chain.length) {
      val currentBlock = this.chain(i)
      val previousBlock = this.chain(i-1)
      if(currentBlock.hash != currentBlock.calculateHash())
        return false
      if(currentBlock.previousHash != previousBlock.hash)
        return false
    }
    return true
  }

  override def toString: String = this.chain.mkString("\n")
}

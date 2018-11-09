import java.time.LocalDateTime

class Blockchain {
  var chain = Seq[Block](createGenesisBlock())
  val difficulty = 4
  var pendingTransactions = Seq[Transaction]()
  val miningReward = 100

  def createGenesisBlock(): Block = {
    new Block(LocalDateTime.now(), Seq(new Transaction("", "Initial Pool Address", 500)))
  }

  def addTransaction(transaction: Transaction): Unit = {
    this.pendingTransactions = this.pendingTransactions :+ transaction
  }

  def minePendingTransactions(rewardAddress: String): Unit = {
    val newBlock = new Block(LocalDateTime.now(), this.pendingTransactions)
    newBlock.mineBlock(this.difficulty)

    println("Block successfully mined: " + newBlock)
    this.chain = this.chain :+ newBlock

    this.pendingTransactions = Seq[Transaction](new Transaction("Reward", rewardAddress, this.miningReward))
  }

  def getBalanceOfAddress(address: String): Double = {
    var balance = 0.0

    this.chain.flatMap(block => block.transactions).foreach(transaction => {
      if(transaction.fromAddress == address)
        balance -= transaction.amount
      if(transaction.toAddress == address)
        balance += transaction.amount
    })
    balance
  }

  def addBlock(block: Block): Unit = {
    block.mineBlock(difficulty)
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

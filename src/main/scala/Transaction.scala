class Transaction(val fromAddress: String, val toAddress: String, val amount: Double) {
  override def toString: String = {
    "From Address: " + fromAddress + ", To Address: " + toAddress + ", Amount: " + amount
  }
}

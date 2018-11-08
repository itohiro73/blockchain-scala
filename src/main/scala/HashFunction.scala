import java.math.BigInteger
import java.security.MessageDigest

object HashFunction {
  val MD_SHA256 = MessageDigest.getInstance("SHA-256")

  def sha256(input: String): String =
  {
    String.format("%064x", new BigInteger(1, MD_SHA256.digest(input.getBytes("UTF-8"))))
  }
}

import org.junit.Assert.*
import org.junit.Test
import java.security.*

/**
 * @author juancho
 */
class day4Test() {

    @Test fun star7() {
        val result1 = getResult("abcdef")
        assert(result1 == "609043")

        val result2 = getResult("pqrstuv")
        assert(result2 == "1048970")

        val result3 = getResult("yzbqklnj", "000000")
    }

    private fun getResult(prefix: String, minValue: String = "00000"): String {
        var loop = 0
        do {
            val result = md5("$prefix$loop").toHexString()
            if (result.startsWith(minValue)) {
                break
            } else {
                loop++
            }
        } while (true)
        return "$loop"
    }

    /**
     *  Set of chars for a half-byte.
     */
    private val CHARS = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

    /**
     *  Returns the string of two characters representing the HEX value of the byte.
     */
    internal fun Byte.toHexString(): String {
        val i = this.toInt()
        val char2 = CHARS[i and 0x0f]
        val char1 = CHARS[i shr 4 and 0x0f]
        return "$char1$char2"
    }

    /**
     *  Returns the HEX representation of ByteArray data.
     */
    internal fun ByteArray.toHexString(): String {
        val builder = StringBuilder()
        for (b in this) {
            builder.append(b.toHexString())
        }
        return builder.toString()
    }

    private fun md5(text: String): ByteArray {
        val digester = MessageDigest.getInstance("MD5")
        return digester.digest(text.toByteArray())
    }
}
import java.util.HashMap
import javax.crypto.Cipher
import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec


fun main() {


    HashMap

}


//java 7 이하
fun hash(_h: Int): Int {
    var h = _h
    h = h xor (h ushr 20 xor (h ushr 12))
    return h xor (h ushr 7) xor (h ushr 4)
}

// java 8 이상
fun hash(value: Any): Int {
    return value.hashCode() xor (value.hashCode() ushr 16)
}

object Encryptor {
    private const val MAIN_ALGORISM = "AES"
    private const val ALGORISM = "$MAIN_ALGORISM/GCM/NoPadding"

    operator fun invoke(key: String, value: String): EncryptData {
        val cipher = Cipher.getInstance(ALGORISM).apply {
            init(Cipher.ENCRYPT_MODE, createKey(key))
        }
        return EncryptData(
            encryptData = cipher.doFinal(value.toByteArray()),
            iv = cipher.iv
        )
    }

    private fun createKey(key: String): SecretKey {
        return SecretKeySpec(key.toByteArray(), MAIN_ALGORISM)
    }
}

object Decryptor {
    private const val MAIN_ALGORISM = "AES"
    private const val ALGORISM = "$MAIN_ALGORISM/GCM/NoPadding"

    operator fun invoke(key: String, encryptedData: EncryptData): String? {
        val gcmParameterSpec = GCMParameterSpec(128, encryptedData.iv)
        return Cipher.getInstance(ALGORISM).apply {
            init(Cipher.DECRYPT_MODE, createKey(key), gcmParameterSpec)
        }.run {
            String(doFinal(encryptedData.encryptData))
        }
    }

    private fun createKey(key: String): SecretKey {
        return SecretKeySpec(key.toByteArray(), MAIN_ALGORISM)
    }
}

object HmacSHA256 {

    private const val ALGORISM = "HmacSHA256"

    operator fun invoke(key: String, data: String): String {
        return Mac.getInstance(ALGORISM).apply {
            init(createKey(key))
        }.run {
            String(doFinal(data.toByteArray()))
        }
    }

    private fun createKey(key: String): SecretKeySpec {
        return SecretKeySpec(key.toByteArray(), ALGORISM)
    }
}

data class EncryptData(val encryptData: ByteArray, val iv: ByteArray)
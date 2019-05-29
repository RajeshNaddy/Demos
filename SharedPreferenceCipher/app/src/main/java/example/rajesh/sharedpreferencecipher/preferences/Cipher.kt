package example.rajesh.sharedpreferencecipher.preferences

import android.util.Base64
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Cipher: This is responsible to encrypt and decrypt data value while storing in shared preferences
 */
object Cipher {


    //AES only supports key sizes of 16, 24 or 32 bytes.
    private const val secretKey: String = "#RAJESHTEST@12$#12345678"// here we used 24 char
    private const val iv4Key: String = "@12#RAJESHTEST$@" // here we used 16 char
    //Its your choice to choose either 16, 24 or 32 bytes.

    private val charset = Charset.forName("UTF-8")
    private var ivSpec: IvParameterSpec = IvParameterSpec(iv4Key.toByteArray(charset))
    private var secKeySpec: SecretKeySpec = SecretKeySpec(secretKey.toByteArray(charset), "AES")
    private var cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS7Padding") //transformation scheme.

    fun encrypt(value: String): String {
        cipher.init(Cipher.ENCRYPT_MODE, secKeySpec, ivSpec)
        return Base64.encodeToString(
            cipher
                .doFinal(value.toByteArray(charset)),
            Base64.DEFAULT
        )
    }

    fun encryptInt(value: Int): String {
        cipher.init(Cipher.ENCRYPT_MODE, secKeySpec, ivSpec)
        val valueText = value.toString().trim()
        return Base64.encodeToString(
            cipher
                .doFinal(valueText.toByteArray(charset)),
            Base64.DEFAULT
        )
    }

    fun encryptLong(value: Long): String {
        cipher.init(Cipher.ENCRYPT_MODE, secKeySpec, ivSpec)
        val valueText = value.toString().trim()
        return Base64.encodeToString(
            cipher
                .doFinal(valueText.toByteArray(charset)),
            Base64.DEFAULT
        )
    }

    fun encryptFloat(value: Float): String {
        cipher.init(Cipher.ENCRYPT_MODE, secKeySpec, ivSpec)
        val valueText = value.toString().trim()
        return Base64.encodeToString(
            cipher
                .doFinal(valueText.toByteArray(charset)),
            Base64.DEFAULT
        )
    }

    fun encryptDouble(value: Double): String {
        cipher.init(Cipher.ENCRYPT_MODE, secKeySpec, ivSpec)
        val valueText = value.toString().trim()
        return Base64.encodeToString(
            cipher
                .doFinal(valueText.toByteArray(charset)),
            Base64.DEFAULT
        )
    }

    fun encryptBoolean(value: Boolean): String {
        cipher.init(Cipher.ENCRYPT_MODE, secKeySpec, ivSpec)
        val valueText = value.toString().trim()
        return Base64.encodeToString(
            cipher
                .doFinal(valueText.toByteArray(charset)),
            Base64.DEFAULT
        )
    }

    fun decrypt(value: String): String {
        if (value.isNotEmpty()) {
            cipher.init(Cipher.DECRYPT_MODE, secKeySpec, ivSpec)
            return String(cipher.doFinal(Base64.decode(value, Base64.DEFAULT)))
        }
        return ""
    }

    fun decryptInt(value: String): Int {
        if (value.isNotEmpty()) {
            cipher.init(Cipher.DECRYPT_MODE, secKeySpec, ivSpec)
            val valueText = String(cipher.doFinal(Base64.decode(value, Base64.DEFAULT)))
            return valueText.trim().toInt()
        }
        return 0
    }

    fun decryptLong(value: String): Long {
        if (value.isNotEmpty()) {
            cipher.init(Cipher.DECRYPT_MODE, secKeySpec, ivSpec)
            val valueText = String(cipher.doFinal(Base64.decode(value, Base64.DEFAULT)))
            return valueText.trim().toLong()
        }
        return 0
    }

    fun decryptFloat(value: String): Float {
        if (value.isNotEmpty()) {
            cipher.init(Cipher.DECRYPT_MODE, secKeySpec, ivSpec)
            val valueText = String(cipher.doFinal(Base64.decode(value, Base64.DEFAULT)))
            return valueText.trim().toFloat()
        }
        return 0.0f
    }

    fun decryptDouble(value: String): Double {
        if (value.isNotEmpty()) {
            cipher.init(Cipher.DECRYPT_MODE, secKeySpec, ivSpec)
            val valueText = String(cipher.doFinal(Base64.decode(value, Base64.DEFAULT)))
            return valueText.trim().toDouble()
        }
        return 0.0
    }

    fun decryptBoolean(value: String): Boolean {
        if (value.isNotEmpty()) {
            cipher.init(Cipher.DECRYPT_MODE, secKeySpec, ivSpec)
            val valueText = String(cipher.doFinal(Base64.decode(value, Base64.DEFAULT)))
            return valueText.trim().toBoolean()
        }
        return false
    }


}
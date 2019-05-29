package example.rajesh.sharedpreferencecipher.preferences

import android.content.Context
import android.content.SharedPreferences
import example.rajesh.sharedpreferencecipher.SPApplication.Companion.appContext

/**
 * Created by Rajesh Naddy on 30/5/19.
 */
abstract class PreferencesImpl(fileName: String) : IPreferences {

    private var mPreferences: SharedPreferences =
        appContext!!.getSharedPreferences(fileName, Context.MODE_PRIVATE)


    override fun getEditor(): SharedPreferences.Editor {
        return mPreferences.edit()
    }

    override fun clearAll() {
        getEditor().clear().apply()
    }

    override fun remove(key: String) {
        getEditor().remove(key).apply()
    }

    override fun getAll(): Map<String, *> {
        return mPreferences.all
    }

    override fun setString(key: String, value: String) {
        getEditor().putString(key, Cipher.encrypt(value)).apply()
    }

    override fun setInt(key: String, value: Int) {
        getEditor().putString(key, Cipher.encryptInt(value)).apply()
//        getEditor().putInt(key, InStoreCipher.encryptInt(value)).apply()
    }

    override fun setLong(key: String, value: Long) {
//        getEditor().putLong(key, value).apply()
        getEditor().putString(key, Cipher.encryptLong(value)).apply()
    }

    override fun setFloat(key: String, value: Float) {
//        getEditor().putFloat(key, value).apply()
        getEditor().putString(key, Cipher.encryptFloat(value)).apply()
    }

//    override fun setDouble(key: String, value: Double) {
//        getEditor().put
//    }

    override fun setBoolean(key: String, value: Boolean) {
//        getEditor().putBoolean(key, value)
        getEditor().putString(key, Cipher.encryptBoolean(value)).apply()
    }

    override fun setStringSet(key: String, value: MutableSet<String>) {
        getEditor().putStringSet(key, value)
    }

    override fun getString(key: String): String {
//        return mPreferences.getString(key, "")
        if (mPreferences.getString(key, "").isNullOrEmpty()) return ""
        return Cipher.decrypt(mPreferences.getString(key, ""))
    }

    fun getStringWithoutCipher(key: String): String { //for testing purpose
       return mPreferences.getString(key, "")
    }

    override fun getInt(key: String): Int {
//        return mPreferences.getInt(key, 0)
        if (mPreferences.getString(key, "").isNullOrEmpty()) return 0
        return Cipher.decryptInt(mPreferences.getString(key, ""))
    }

    override fun getLong(key: String): Long {
//        return mPreferences.getLong(key, 0)
        return Cipher.decryptLong(mPreferences.getString(key, ""))
    }

    override fun getFloat(key: String): Float {
//        return mPreferences.getFloat(key, 0.0f)
        return Cipher.decryptFloat(mPreferences.getString(key, ""))
    }

    override fun getBoolean(key: String): Boolean {
//        return mPreferences.getBoolean(key, false)
        return Cipher.decryptBoolean(mPreferences.getString(key, ""))
    }

    override fun getStringSet(key: String): MutableSet<String> {
        return mPreferences.getStringSet(key, null)
    }
}
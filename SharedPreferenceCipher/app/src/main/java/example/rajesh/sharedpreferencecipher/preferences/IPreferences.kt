package example.rajesh.sharedpreferencecipher.preferences

import android.content.SharedPreferences

/**
 * Created by Rajesh Naddy on 30/5/19.
 * interface contains all the important methods
 */

interface IPreferences {
    //    fun initialize(context: Context, fileName:String)

    //    common operations
    fun getEditor(): SharedPreferences.Editor
    fun clearAll()
    fun remove(key: String)
    fun getAll(): Map<String, *>

    //    setters
    fun setString(key: String, value: String)
    fun setInt(key: String, value: Int)
    fun setLong(key: String, value: Long)
    fun setFloat(key: String, value: Float)
    //    fun setDouble(key: String, value: Double)
    fun setBoolean(key: String, value: Boolean)
    fun setStringSet(key: String, value: MutableSet<String>)

    //    getters
    fun getString(key: String): String
    fun getInt(key: String): Int
    fun getLong(key: String): Long
    fun getFloat(key: String): Float
    //    fun getDouble(key: String): Double
    fun getBoolean(key: String): Boolean
    fun getStringSet(key: String): MutableSet<String>
}
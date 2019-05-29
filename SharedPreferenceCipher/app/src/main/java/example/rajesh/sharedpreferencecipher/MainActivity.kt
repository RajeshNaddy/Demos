package example.rajesh.sharedpreferencecipher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import example.rajesh.sharedpreferencecipher.preferences.PREF_SOME_STRING
import example.rajesh.sharedpreferencecipher.preferences.SPreferences

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG = MainActivity::class.java.canonicalName as String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val encryptString = "Any Long String"
        Log.d(TAG,encryptString) // Any Long String
        SPreferences.setString(PREF_SOME_STRING,encryptString)

        SPreferences.getStringWithoutCipher(PREF_SOME_STRING)
        Log.d(TAG,SPreferences.getStringWithoutCipher(PREF_SOME_STRING))// vej4L2nPusS0uoklvhCfdg==

        SPreferences.getString(PREF_SOME_STRING)
        Log.d(TAG,SPreferences.getString(PREF_SOME_STRING)) // Any Long String
    }
}

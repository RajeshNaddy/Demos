package example.rajesh.sharedpreferencecipher.preferences

/**
 * Created by Rajesh Naddy on 30/5/19.
 * SPreferences as a Singleton obj for PreferencesImpl
 */

private const val PREF_FILE = "preferences"
const val PREF_SOME_STRING = "some_string"

object SPreferences : PreferencesImpl(PREF_FILE) {}

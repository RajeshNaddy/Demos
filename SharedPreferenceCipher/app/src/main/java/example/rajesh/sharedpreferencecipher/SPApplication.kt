package example.rajesh.sharedpreferencecipher

import android.app.Application
import android.content.Context

open class SPApplication : Application() {

    companion object {
        @JvmStatic
        var appContext: Context? = null
    }

    override fun onCreate() {
        appContext = this
        super.onCreate()
    }
}
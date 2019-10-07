package com.rajesh.biometricauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.biometric.BiometricPrompt
import java.util.concurrent.Executors

class FingerPrintAuth : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finger_print)

        // we need executor to handle callback events
        val executor = Executors.newSingleThreadExecutor()

        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    //there are lots of error codes, please go through all the error codes before using
                    when (errorCode) {
                        BiometricPrompt.ERROR_NEGATIVE_BUTTON -> showToast("Cancelled")

                        BiometricPrompt.ERROR_HW_UNAVAILABLE -> showToast("Biometric not present")

                        BiometricPrompt.ERROR_NO_BIOMETRICS -> showToast("No FingerPrint exist!!")

                        BiometricPrompt.ERROR_NO_DEVICE_CREDENTIAL -> showToast("No Credentials exist!!")

                        else -> showToast(errString.toString())
                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    showToast("Auth Success")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    showToast("Auth Failed")
                }
            })

        // build a BiometricPrompt to show
        val promptInfo = PromptInfo.Builder()
            .setTitle("Set the title to display.")
            .setSubtitle("Set the subtitle to display.")
            .setDescription("Set the description to display")
            .setNegativeButtonText("Cancel")
            .build()


        // on click of authenticateButton
        findViewById<Button>(R.id.authenticateButton).setOnClickListener { v -> biometricPrompt.authenticate(promptInfo) }
    }


    /*  METHODS  */
    private fun showToast(message: String) {
        runOnUiThread { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
    }
}

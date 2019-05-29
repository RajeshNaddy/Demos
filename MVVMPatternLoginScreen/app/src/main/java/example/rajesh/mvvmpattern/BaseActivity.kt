package example.rajesh.mvvmpattern.LoginModule

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.util.Progress

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var progress: Progress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        progress = Progress(this)
        progress.setCancelable(false)
    }

    internal fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    internal fun showLongToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    internal fun showProgress() {
        progress.show()
    }

    internal fun dismissProgress() {
        progress.dismiss()
    }

    fun showAlertDialog(title: String,message: String?,clickButtonText: String?,onClickListener: DialogInterface.OnClickListener?,
        isCancellable: Boolean
    ) {
        val alertDialogBuilder = AlertDialog.Builder(this@BaseActivity)
        if (!title.isEmpty()) {
            alertDialogBuilder.setTitle(title)
        }
        if (message != null)
            alertDialogBuilder.setMessage(message)
        if (clickButtonText != null && onClickListener != null)
            alertDialogBuilder.setPositiveButton(clickButtonText, onClickListener)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(isCancellable)
        alertDialog.show()
    }
}
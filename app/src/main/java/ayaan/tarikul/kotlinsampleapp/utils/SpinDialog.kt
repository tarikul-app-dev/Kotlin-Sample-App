package ayaan.tarikul.kotlinsampleapp.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import ayaan.tarikul.kotlinsampleapp.R
import com.github.ybq.android.spinkit.style.Wave

class SpinDialog (mContext: Context){
    lateinit var alertDialog: AlertDialog
    lateinit var progressBar: ProgressBar
    val  context = mContext;

    fun showDialog(cancelable: Boolean) {
        val li = LayoutInflater.from(context)
        val view = li.inflate(R.layout.custom_loading, null)

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(view)

        alertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(cancelable)
        //	alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        progressBar = view.findViewById(R.id.spin_kit)
        val wave = Wave()
        progressBar.indeterminateDrawable = wave

        try {
            alertDialog.show()

        } catch (e: Exception) {

        }
    }


    fun dismis() {
        alertDialog.cancel()

    }


}
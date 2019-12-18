package ayaan.tarikul.kotlinsampleapp.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import ayaan.tarikul.kotlinsampleapp.R

class BusyDialog (mContext:Context){
     val  context = mContext;
     var dialog : Dialog? = null

    fun showDialog( cancelable: Boolean, title: String) {

        dialog = Dialog(context, android.R.style.Theme_Translucent)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        // here we set layout of progress dialog
        dialog!!.setContentView(R.layout.custom_progress_dialog)
        dialog!!.setCancelable(cancelable)
        val title_text = dialog!!.findViewById(R.id.title_text_progress) as TextView
        title_text.setText("Please wait...")
        val progress = dialog!!.findViewById(R.id.progress) as ProgressBar
        progress.isIndeterminate = true
        progress.indeterminateDrawable = context.resources.getDrawable(R.drawable.custom_progress)
    }


    fun show() {
        try {
            dialog!!.show()
        } catch (e: Exception) {

        }

    }

    fun dismis() {
        dialog!!.cancel()
    }

    fun isShowing(): Boolean {
        return if (dialog!!.isShowing())
            true
        else
            false
    }

}
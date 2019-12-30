package ayaan.tarikul.kotlinsampleapp.utils

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import androidx.appcompat.app.AlertDialog
import ayaan.tarikul.kotlinsampleapp.R
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil

class UIContent (val mContext:Context){


    fun isValidate(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun showExitDialog(title: String, message: String) {
        val aBuilder = AlertDialog.Builder(mContext, R.style.AppCompatAlertDialogStyle)
        aBuilder.setTitle(title)
        // aBuilder.setIcon(R.drawable.ic_launcher);
        aBuilder.setMessage(message)

        aBuilder.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss()
            // activity.finish();
        }
        //        aBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        //            @Override
        //            public void onClick(DialogInterface dialogInterface, int i) {
        //                dialogInterface.dismiss();
        //            }
        //        });

        aBuilder.show()
    }

    fun isValid(bdNumberStr: String): Boolean {

        //String bdNumberStr = "8801711419556";
        val phoneUtil = PhoneNumberUtil.getInstance()

        var isValid = false
        try {
            //BD is default country code for Bangladesh (used for number without 880 at the begginning)
            val bdNumberProto = phoneUtil.parse(bdNumberStr, "BD")
            isValid = phoneUtil.isValidNumber(bdNumberProto) // returns true
        } catch (e: NumberParseException) {
            System.err.println("NumberParseException was thrown: $e")
            isValid = false
        }


        return isValid

    }

    fun isConnectingToInternet(): Boolean {

        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}
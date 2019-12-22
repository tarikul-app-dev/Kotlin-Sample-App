package ayaan.tarikul.kotlinsampleapp.activity

import android.content.Intent

import android.os.Bundle
import android.view.View

import android.view.WindowManager
import android.os.Build
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import ayaan.tarikul.kotlinsampleapp.R
import ayaan.tarikul.kotlinsampleapp.utils.PreferenceHelper
import ayaan.tarikul.kotlinsampleapp.utils.UIContent
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton
import kotlinx.android.synthetic.main.activity_register.*


class RegisterAct : AppCompatActivity() {
    var preferenceHelper : PreferenceHelper? = null;
    var uiContent : UIContent ? = null
    var edtName : EditText ? = null
    var edtEmail : EditText ? = null
    var edtMobile : EditText ? = null
    var edtPassword : EditText ? = null
    var btnReg : CircularProgressButton ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        changeStatusBarColor()
        initViews()
    }

    fun initViews(){
        preferenceHelper = PreferenceHelper(this)
        uiContent = UIContent(this)

        edtName = findViewById<View>(R.id.edt_text_name) as? EditText
        edtEmail = findViewById<View>(R.id.edt_text_email) as? EditText
        edtMobile = findViewById<View>(R.id.edt_mobile_number) as? EditText
        edtPassword = findViewById<View>(R.id.edt_password) as? EditText
        btnReg = findViewById<View>(R.id.btn_register) as? CircularProgressButton

        btnReg?.setOnClickListener(){
            var userName :String = edtName?.text.toString()
            var userEmail :String = edtEmail?.text.toString()
            var mobileNumber :String = edtMobile?.text.toString()
            var password :String = edtPassword?.text.toString()

            if(uiContent!!.isValidate(userEmail)){
                if(uiContent!!.isValid(mobileNumber)){
                    preferenceHelper?.setValueToPreference("user_name",userName)
                    preferenceHelper?.setValueToPreference("email",userEmail)
                    preferenceHelper?.setValueToPreference("mobile_number",mobileNumber)
                    preferenceHelper?.setValueToPreference("password",password)

                    showExitDialog("Warning","Data Save Success")
                }else{
                    uiContent!!.showExitDialog("Warning","Invalid Mobile Number")
                }

            }else{
                uiContent!!.showExitDialog("Warning","Invalid Email Address")
            }




        }


    }


    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //            window.setStatusBarColor(Color.TRANSPARENT);
            window.statusBarColor = resources.getColor(R.color.register_bk_color)
        }
    }

    fun onLoginClick(view: View) {
        startActivity(Intent(this, LoginAct::class.java))
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right)

    }

    fun clearText(){
        edtName?.setText("")
        edtEmail?.setText("")
        edtMobile?.setText("")
        edtPassword?.setText("")
    }



    fun showExitDialog(title: String, message: String) {
        val aBuilder = AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
        aBuilder.setTitle(title)
        // aBuilder.setIcon(R.drawable.ic_launcher);
        aBuilder.setMessage(message)

        aBuilder.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss()
            // activity.finish();
            clearText()
            startActivity(Intent(applicationContext, LoginAct::class.java))
            ActivityCompat.finishAffinity(this)
        }
        //        aBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        //            @Override
        //            public void onClick(DialogInterface dialogInterface, int i) {
        //                dialogInterface.dismiss();
        //            }
        //        });

        aBuilder.show()
    }

}

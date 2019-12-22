package ayaan.tarikul.kotlinsampleapp.activity

import android.os.Build
import android.os.Bundle
import android.view.View

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import ayaan.tarikul.kotlinsampleapp.utils.BusyDialog
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.CallbackManager
import java.util.*

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import ayaan.tarikul.kotlinsampleapp.R
import ayaan.tarikul.kotlinsampleapp.utils.PreferenceHelper
import ayaan.tarikul.kotlinsampleapp.utils.UIContent


class LoginAct : AppCompatActivity() {
     var mBusyDialog:BusyDialog?=null
    private var callbackManager: CallbackManager? = null
    var localStorage : PreferenceHelper? = null
    var uiContent : UIContent ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login)
        initViews()
    }

    fun initViews() {
        localStorage = PreferenceHelper(this)
        mBusyDialog = BusyDialog(this)
        uiContent = UIContent(this)

        var edtEmail = findViewById<View>(R.id.edt_email) as EditText
        var edtPassword = findViewById<View>(R.id.edt_password) as EditText
        var btnLogin = findViewById<View>(R.id.btn_login) as Button
        var imgvFacebook = findViewById<View>(R.id.imgv_facebook) as ImageView
        var txvResigter = findViewById<View>(R.id.txv_register) as TextView

        var saveEmail = localStorage!!.getValueFromPreference("email")
        var savePassword = localStorage!!.getValueFromPreference("password")
        // Creating CallbackManager
        callbackManager = CallbackManager.Factory.create();


        btnLogin.setOnClickListener() {
//            mBusyDialog!!.showDialog(false, "Please wait....loading")
//            mBusyDialog!!.show()
            var userEmail :String = edtEmail?.text.toString()
            var password :String = edtPassword?.text.toString()

            if(userEmail.equals(saveEmail) && password.equals(savePassword)){
                startActivity(Intent(applicationContext, MainAct::class.java))
                ActivityCompat.finishAffinity(this)

            }else{
                uiContent!!.showExitDialog("Warning","Invalid email or password")
            }



        }


        imgvFacebook.setOnClickListener() {
            // Login
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
                        startActivity(Intent(applicationContext, MainAct::class.java))
                    }

                    override fun onCancel() {
                        Log.d("MainActivity", "Facebook onCancel.")

                    }

                    override fun onError(error: FacebookException) {
                        Log.d("MainActivity", "Facebook onError.")

                    }
                })
        }



        txvResigter.setOnClickListener(){
            startActivity(Intent(applicationContext, RegisterAct::class.java))
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }



}

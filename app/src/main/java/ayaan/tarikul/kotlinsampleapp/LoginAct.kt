package ayaan.tarikul.kotlinsampleapp

import android.os.Build
import android.os.Bundle
import android.view.View

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import ayaan.tarikul.kotlinsampleapp.activity.MainAct
import ayaan.tarikul.kotlinsampleapp.utils.BusyDialog
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.CallbackManager
import java.util.*

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class LoginAct : AppCompatActivity() {
     var mBusyDialog:BusyDialog?=null
    private var callbackManager: CallbackManager? = null

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

        mBusyDialog = BusyDialog(this)
        var edtEmail = findViewById<View>(R.id.edt_email) as EditText
        var edtPassword = findViewById<View>(R.id.edt_password) as EditText
        var btnLogin = findViewById<View>(R.id.btn_login) as Button
        var imgvFacebook = findViewById<View>(R.id.imgv_facebook) as ImageView
        // Creating CallbackManager
        callbackManager = CallbackManager.Factory.create();


        btnLogin.setOnClickListener() {
            mBusyDialog!!.showDialog(false, "Please wait....loading")
            mBusyDialog!!.show()
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


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}

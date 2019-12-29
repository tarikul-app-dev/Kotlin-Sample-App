package ayaan.tarikul.kotlinsampleapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import ayaan.tarikul.kotlinsampleapp.R
import ayaan.tarikul.kotlinsampleapp.utils.PreferenceHelper

class SplashScreenAct : AppCompatActivity() {
    var preferenceHelper : PreferenceHelper? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initViews()
    }


    fun initViews(){
        preferenceHelper = PreferenceHelper(this)
        var isLogin:Boolean = preferenceHelper!!.getBoleanValueSharedPreferences("isLogin")

        val SPLASH_TIME_OUT = 2000
        val homeIntent = Intent(this, LoginAct::class.java)
        val mainIntent = Intent(this, MainAct::class.java)

        Handler().postDelayed({
            //Do some stuff here, like implement deep linking
            if(isLogin){
                startActivity(mainIntent)
                finish()
            }else{
                startActivity(homeIntent)
                finish()
            }

        }, SPLASH_TIME_OUT.toLong())
    }

}

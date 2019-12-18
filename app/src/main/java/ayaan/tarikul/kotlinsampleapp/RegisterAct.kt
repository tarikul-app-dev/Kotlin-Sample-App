package ayaan.tarikul.kotlinsampleapp

import android.content.Intent

import android.os.Bundle
import android.view.View

import android.view.WindowManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity


class RegisterAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        changeStatusBarColor()
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
}

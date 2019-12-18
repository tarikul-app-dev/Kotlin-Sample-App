package ayaan.tarikul.kotlinsampleapp.utils

class UIContent {


    fun isValidate(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }



}
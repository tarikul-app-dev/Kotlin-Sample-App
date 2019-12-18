package ayaan.tarikul.kotlinsampleapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferenceHelper (private  val mContext:Context){

    fun setValueToPreference(key: String, value: String) {
        val preferences: SharedPreferences
        val editor: SharedPreferences.Editor

        preferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        editor = preferences.edit()

        editor.putString(key, value)
        editor.commit()

    }

    fun getValueFromPreference(key: String): String? {
        val preferences: SharedPreferences
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        return preferences.getString(key, null)
    }


    fun setBooleanValueToPreference(key: String, isChecked: Boolean) {
        val preferences: SharedPreferences
        val editor: SharedPreferences.Editor

        preferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        editor = preferences.edit()
        editor.putBoolean(key, isChecked)
        editor.commit()

    }

    fun getBoleanValueSharedPreferences(key: String): Boolean {
        val preferences: SharedPreferences
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        return preferences.getBoolean(key, false)

    }

}
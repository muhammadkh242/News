package com.example.news.ui.view.settings

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.news.R
import java.util.*


class SettingsFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    /*override fun onPause() {
        super.onPause()
        val lang = defaultPref.getString("language", "en")!!
        Log.i("TAG", "onPause: $lang")
       // setLocale(lang)
//        val config = this.resources.configuration
//        val locale = Locale(lang)
//        Locale.setDefault(locale)
//        config.setLocale(locale)
//        requireActivity().createConfigurationContext(config)
//        this.resources.updateConfiguration(config, this.resources.displayMetrics)
    }*/
    private fun setAppLocale(localeCode: String) {
        val resources: Resources = resources
        val dm: DisplayMetrics = resources.getDisplayMetrics()
        val config: Configuration = resources.getConfiguration()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(Locale(localeCode.toLowerCase()))
        } else {
            config.locale = Locale(localeCode.toLowerCase())
        }
        resources.updateConfiguration(config, dm)
    }

    private fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        Locale.setDefault(myLocale)
        val res: Resources = resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.locale = myLocale
        conf.setLayoutDirection(myLocale)
        res.updateConfiguration(conf, dm)
        Log.i("TAG", "setLocale: ${Locale.getDefault().language}")
    }


}
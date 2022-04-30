package com.example.news.ui.view.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.news.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerView.id, SettingsFragment())
            .commit()
    }
}
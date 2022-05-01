package com.example.news

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.SearchEvent
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.news.ui.adapters.PagerAdapter
import com.example.news.ui.view.settings.SettingsActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var tabLayout: TabLayout

    private val pagerCallback by lazy {
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)!!.select()
            }
        } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)

        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.nav_view)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled
        toggle.drawerArrowDrawable.color = (Color.parseColor("#0098A5"))
        toggle.syncState()

        pagerAdapter = PagerAdapter(this.supportFragmentManager, this.lifecycle)
        viewPager.adapter = pagerAdapter
        viewPager.registerOnPageChangeCallback(pagerCallback)
        navigationView.setNavigationItemSelectedListener(this)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }


        })


    }



    @SuppressLint("WrongConstant")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        viewPager.apply {
            when(id){
                R.id.nav_home -> currentItem = 0
                R.id.nav_science -> currentItem = 1
                R.id.nav_sport -> currentItem = 2
                R.id.nav_entertainment -> currentItem = 3
                R.id.nav_business -> currentItem = 4
                R.id.nav_health -> currentItem = 5
            }
        }
        if(id == R.id.nav_settings){
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        drawerLayout.closeDrawer(Gravity.START)

        return true
    }

}
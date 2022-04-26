package com.example.news.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.news.ui.view.*

class PagerAdapter(manager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(manager, lifecycle){
    override fun getItemCount(): Int = 9

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> WorldFragment()
            2 -> ScienceFragment()
            3 -> SportsFragment()
            4 -> EnvironmentFragment()
            5 -> SocietyFragment()
            6 -> FashionFragment()
            7 -> BusinessFragment()
            8 -> CultureFragment()
            else -> {HomeFragment()}
        }
    }



}
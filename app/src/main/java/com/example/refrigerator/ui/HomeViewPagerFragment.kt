package com.example.refrigerator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.refrigerator.R
import com.example.refrigerator.databinding.FragmentHomeViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeViewPagerBinding.inflate(inflater,container,false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = RefrigeratorPagerAdapter(this)

        TabLayoutMediator(tabLayout,viewPager){ tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root

    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            REFRIGERATOR_PAGE_INDEX -> R.drawable.ic_star_on
            FOOD_LIST_PAGE_INDEX -> R.drawable.ic_star_off
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            REFRIGERATOR_PAGE_INDEX -> getString(R.string.my_refrigerator_title)
            FOOD_LIST_PAGE_INDEX -> getString(R.string.food_list_title)
            else -> null
        }
    }


}

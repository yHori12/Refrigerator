package com.example.refrigerator.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

const val REFRIGERATOR_PAGE_INDEX = 0
const val FOOD_LIST_PAGE_INDEX = 1

class RefrigeratorPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentCreators:Map<Int,() -> Fragment> = mapOf(
        REFRIGERATOR_PAGE_INDEX to { RefrigeratorFragment() },
        FOOD_LIST_PAGE_INDEX to { FoodListFragment() }
    )

    override fun getItemCount(): Int = tabFragmentCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}

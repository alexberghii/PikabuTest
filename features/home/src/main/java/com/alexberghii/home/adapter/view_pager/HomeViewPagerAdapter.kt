package com.alexberghii.home.adapter.view_pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class HomeViewPagerAdapter(fragment: Fragment, private val childFragments: List<Fragment>) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = childFragments.size

    override fun createFragment(position: Int) = childFragments[position]
}
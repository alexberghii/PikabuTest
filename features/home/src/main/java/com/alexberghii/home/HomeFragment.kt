package com.alexberghii.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexberghii.home.adapter.view_pager.HomeViewPagerAdapter
import com.alexberghii.home.favorites.FavoritesFragment
import com.alexberghii.home.feed.FeedFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val titles = arrayOf(R.string.feed, R.string.favorites)

    private lateinit var viewPagerAdapter: HomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = listOf(FeedFragment(), FavoritesFragment())
        viewPagerAdapter = HomeViewPagerAdapter(this, fragments)
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(titles[position])
        }.attach()
    }
}
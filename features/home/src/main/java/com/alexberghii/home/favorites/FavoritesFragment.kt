package com.alexberghii.home.favorites

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexberghii.commons.extensions.observe
import com.alexberghii.commons.ui.base.BaseFragment
import com.alexberghii.core.di.CoreComponentProvider
import com.alexberghii.home.HomeFragmentDirections
import com.alexberghii.home.R
import com.alexberghii.home.adapter.feed.FavoritesAdapter
import com.alexberghii.home.favorites.di.DaggerFavoritesComponent
import com.alexberghii.home.favorites.di.FavoritesModule
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_feed.recyclerView
import java.lang.IllegalStateException
import javax.inject.Inject


class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {

    @Inject
    lateinit var viewModel: FavoritesViewModel

    @Inject
    lateinit var adapter: FavoritesAdapter

    override fun onInitDi() {
        if (requireContext().applicationContext !is CoreComponentProvider) {
            throw IllegalStateException("Application class should implement CoreComponentProvider interface")
        }
        val coreComponent = (requireContext().applicationContext as CoreComponentProvider).provideCoreComponent()
        DaggerFavoritesComponent.factory().create(coreComponent, FavoritesModule(this)).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        observe(viewModel.data) {
            if (it.isEmpty()) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.GONE
            }
            adapter.submitList(it)
        }
    }

    internal fun onFeedItemClicked(feedItemId: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToStoryFragment(feedItemId))
    }
}
package com.alexberghii.home.feed

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexberghii.commons.extensions.observe
import com.alexberghii.commons.ui.base.BaseFragment
import com.alexberghii.core.di.CoreComponentProvider
import com.alexberghii.core.domain.model.FeedItem
import com.alexberghii.core.network.NetworkState
import com.alexberghii.home.HomeFragmentDirections
import com.alexberghii.home.R
import com.alexberghii.home.adapter.feed.FeedAdapter
import com.alexberghii.home.feed.di.DaggerFeedComponent
import com.alexberghii.home.feed.di.FeedModule
import kotlinx.android.synthetic.main.fragment_feed.*
import java.lang.IllegalStateException
import javax.inject.Inject


class FeedFragment : BaseFragment(R.layout.fragment_feed) {

    @Inject
    lateinit var viewModel: FeedViewModel

    @Inject
    lateinit var adapter: FeedAdapter

    override fun onInitDi() {
        if (requireContext().applicationContext !is CoreComponentProvider) {
            throw IllegalStateException("Application class should implement CoreComponentProvider interface")
        }
        val coreComponent = (requireContext().applicationContext as CoreComponentProvider).provideCoreComponent()
        DaggerFeedComponent.factory().create(coreComponent, FeedModule(this)).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener { viewModel.refresh() }

        observe(viewModel.data, ::showFeed)
        observe(viewModel.savedFeedItemsIds, ::updateSavedFeedItems)
        observe(viewModel.networkState, ::updateViews)
    }

    private fun showFeed(feed: PagedList<FeedItem>) {
        adapter.submitList(feed)
    }

    private fun updateSavedFeedItems(savedFeedItemsIds: List<Int>) {
        adapter.submitSavedFeedItemsIds(savedFeedItemsIds)
    }

    private fun updateViews(networkState: NetworkState) {
        swipeRefreshLayout.isRefreshing = false
        when (networkState) {
            NetworkState.Success -> {
                errorView.visibility = View.GONE
                loadingView.visibility = View.GONE
            }
            NetworkState.Loading -> {
                errorView.visibility = View.GONE
                loadingView.visibility = View.VISIBLE
            }
            NetworkState.Error -> {
                errorView.visibility = View.VISIBLE
                loadingView.visibility = View.GONE
            }
        }
    }

    internal fun onFeedItemClicked(feedItemId: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToStoryFragment(feedItemId))
    }
}
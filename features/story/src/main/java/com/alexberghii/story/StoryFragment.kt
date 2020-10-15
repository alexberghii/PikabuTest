package com.alexberghii.story

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import coil.load
import com.alexberghii.commons.extensions.observe
import com.alexberghii.commons.ui.base.BaseFragment
import com.alexberghii.core.di.CoreComponentProvider
import com.alexberghii.core.domain.model.FeedItem
import com.alexberghii.core.network.NetworkState
import com.alexberghii.story.di.DaggerStoryComponent
import com.alexberghii.story.di.StoryModule
import kotlinx.android.synthetic.main.fragment_story.*
import java.lang.IllegalStateException
import javax.inject.Inject


class StoryFragment : BaseFragment(R.layout.fragment_story) {

    @Inject
    lateinit var viewModel: StoryViewModel

    override fun onInitDi() {
        if (requireContext().applicationContext !is CoreComponentProvider) {
            throw IllegalStateException("Application class should implement CoreComponentProvider interface")
        }
        val coreComponent = (requireContext().applicationContext as CoreComponentProvider).provideCoreComponent()
        DaggerStoryComponent.factory().create(coreComponent, StoryModule(this)).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout.setOnRefreshListener { viewModel.refresh() }

        observe(viewModel.data, ::showStory)
        observe(viewModel.networkState, ::updateViews)

        viewModel.getStory()

        buttonSave.setOnClickListener { viewModel.saveOrDeleteFeedItem() }
    }

    private fun showStory(story: FeedItem) {
        storyContainer.visibility = View.VISIBLE
        textViewTitle.text = story.title
        textViewBody.text = story.body
        buttonSave.text = if (story.isSaved) getString(R.string.remove) else getString(R.string.save)

        imageContainer.removeAllViews()
        for (imageUrl in story.images) {
            val view = layoutInflater.inflate(R.layout.item_feed_image, imageContainer, false)
            view.findViewById<ImageView>(R.id.imageView).load(imageUrl) {
                error(R.drawable.ic_broken_image)
            }
            imageContainer.addView(view)
        }
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
}
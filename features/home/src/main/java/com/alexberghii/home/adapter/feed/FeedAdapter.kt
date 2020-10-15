package com.alexberghii.home.adapter.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexberghii.core.domain.model.FeedItem
import com.alexberghii.home.R
import com.alexberghii.home.feed.FeedViewModel


class FeedAdapter(
    private val viewModel: FeedViewModel,
    onItemClicked: (feedItemId: Int) -> Unit
) : BaseFeedAdapter(onItemClicked){

    private var currentSavedFeedItemsIds = listOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FeedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false))

    fun submitSavedFeedItemsIds(savedFeedItemsIds: List<Int>) {
        currentSavedFeedItemsIds = savedFeedItemsIds
        notifyDataSetChanged()
    }

    inner class FeedViewHolder(itemView: View) : BaseFeedViewHolder(itemView) {

        override fun bindView(item: FeedItem) {
            super.bindView(item)

            buttonSave.text = if (currentSavedFeedItemsIds.contains(item.id)){
                itemView.context.getString(R.string.remove)
            } else {
                itemView.context.getString(R.string.save)
            }
            buttonSave.setOnClickListener { viewModel.saveOrDeleteFeedItem(item) }
        }
    }
}
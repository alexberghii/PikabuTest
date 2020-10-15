package com.alexberghii.home.adapter.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexberghii.core.domain.model.FeedItem
import com.alexberghii.home.R
import com.alexberghii.home.favorites.FavoritesViewModel


class FavoritesAdapter(
    private val viewModel: FavoritesViewModel,
    onItemClicked: (feedItemId: Int) -> Unit
) : BaseFeedAdapter(onItemClicked){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoritesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false))

    inner class FavoritesViewHolder(itemView: View) : BaseFeedViewHolder(itemView) {

        override fun bindView(item: FeedItem) {
            super.bindView(item)

            buttonSave.text = itemView.context.getString(R.string.remove)
            buttonSave.setOnClickListener { viewModel.deleteFeedItem(item) }
        }
    }
}
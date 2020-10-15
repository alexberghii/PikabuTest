package com.alexberghii.home.adapter.feed

import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alexberghii.core.domain.model.FeedItem
import com.alexberghii.home.R


abstract class BaseFeedAdapter(
    private val onItemClicked: (feedItemId: Int) -> Unit
) : PagedListAdapter<FeedItem, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<FeedItem>() {
    override fun areItemsTheSame(oldItem: FeedItem, newItem: FeedItem) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: FeedItem, newItem: FeedItem) = oldItem == newItem
}) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BaseFeedViewHolder -> getItem(position)?.let { holder.bindView(it) }
        }
    }

    open inner class BaseFeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        private val textViewBody: TextView = itemView.findViewById(R.id.textViewBody)
        protected val buttonSave: Button = itemView.findViewById(R.id.buttonSave)
        private val imageContainer: LinearLayout = itemView.findViewById(R.id.imageContainer)

        open fun bindView(item: FeedItem) {
            textViewTitle.text = item.title
            textViewBody.text = item.body

            imageContainer.removeAllViews()
            for (imageUrl in item.images) {
                val view = LayoutInflater.from(itemView.context).inflate(R.layout.item_feed_image, imageContainer, false)
                view.findViewById<ImageView>(R.id.imageView).load(imageUrl) {
                    error(R.drawable.ic_broken_image)
                }
                imageContainer.addView(view)
            }

            itemView.setOnClickListener { onItemClicked(item.id) }
        }
    }
}
package com.example.posttypes

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_feed_youtube_card.view.*

class YoutubeViewHolder(adapter: PostAdapter, view: View) : BaseViewHolder(adapter, view) {
    init {
        with(itemView) {
            buttonLike.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    if (!item.activeLike) {
                        buttonLike.setImageResource(R.drawable.ic_baseline_favoritered_24)
                        itemLike.setTextColor(Color.RED)
                        item.itemLike = item.itemLike!! + 1
                    } else {
                        buttonLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                        itemLike.setTextColor(Color.GRAY)
                        item.itemLike = item.itemLike!! - 1
                    }
                    adapter.notifyItemChanged(adapterPosition)
                    item.activeLike = !item.activeLike
                }
            }

            buttonComment.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    if (!item.activeComm) {
                        buttonComment.setImageResource(R.drawable.ic_baseline_commentred_24)
                        itemComment.setTextColor(Color.RED)
                        item.itemComm = item.itemComm!! + 1

                    } else {
                        buttonComment.setImageResource(R.drawable.ic_baseline_comment_24)
                        itemComment.setTextColor(Color.GRAY)
                        item.itemComm = item.itemComm!! - 1
                    }
                    adapter.notifyItemChanged(adapterPosition)
                    item.activeComm = !item.activeComm
                }
            }
            plug.setOnClickListener() {
                val item = adapter.list[adapterPosition]
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                itemView.context.startActivity(intent)
            }

            buttonShare.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    if (!item.activeShare) {
                        buttonShare.setImageResource(R.drawable.ic_baseline_sharered_24r)
                        itemShare.setTextColor(Color.RED)
                        item.itemShare = item.itemShare!! + 1
                    } else {
                        buttonShare.setImageResource(R.drawable.ic_baseline_share_24)
                        itemShare.setTextColor(Color.GRAY)
                        item.itemShare = item.itemShare!! - 1
                    }
                    adapter.notifyItemChanged(adapterPosition)
                    item.activeShare = !item.activeShare
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT, """
                                ${item.author}
                                ${item.text}
                            """.trimIndent()
                        )
                        type = "text/plain"
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
    }


    override fun bind(post: Post) {
        with(itemView) {
            date.text = post.data
            name.text = post.author
            textPost.text = post.text
            itemLike.text = post.itemLike.toString()
            itemComment.text = post.itemComm.toString()
            itemShare.text = post.itemShare.toString()
            if (post.activeLike) {
                buttonLike.setImageResource(R.drawable.ic_baseline_favoritered_24)
                itemLike.setTextColor(Color.RED)
            } else {
                buttonLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                itemLike.setTextColor(Color.GRAY)
            }
            if (post.activeComm) {
                buttonComment.setImageResource(R.drawable.ic_baseline_commentred_24)
                itemComment.setTextColor(Color.RED)
            } else {
                buttonComment.setImageResource(R.drawable.ic_baseline_comment_24)
                itemComment.setTextColor(Color.GRAY)
            }
            if (post.activeShare) {
                buttonShare.setImageResource(R.drawable.ic_baseline_sharered_24r)
                itemShare.setTextColor(Color.RED)
            } else {
                buttonShare.setImageResource(R.drawable.ic_baseline_share_24)
                itemShare.setTextColor(Color.GRAY)
            }
        }

    }
}
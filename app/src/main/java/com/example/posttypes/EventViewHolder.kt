package com.example.posttypes


import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_feed_event_card.view.*
import kotlinx.android.synthetic.main.post_feed_post_card.view.*
import kotlinx.android.synthetic.main.post_feed_post_card.view.buttonComment
import kotlinx.android.synthetic.main.post_feed_post_card.view.buttonLike
import kotlinx.android.synthetic.main.post_feed_post_card.view.buttonShare
import kotlinx.android.synthetic.main.post_feed_post_card.view.date
import kotlinx.android.synthetic.main.post_feed_post_card.view.itemLike
import kotlinx.android.synthetic.main.post_feed_post_card.view.itemShare
import kotlinx.android.synthetic.main.post_feed_post_card.view.textPost

class EventViewHolder(adapter: PostAdapter, view: View): BaseViewHolder(adapter, view) {
    init {
        with(itemView) {
            buttonLike.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    item.activeLike = !item.activeLike
                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            gpsText.setOnClickListener () {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    val intent = Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse("geo:${item.gps!!.first},${item.gps!!.second}")
                    }
                    itemView.context.startActivity(intent)
                }
            }
            buttonComment.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    item.activeComm = !item.activeComm
                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            buttonShare.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
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
            address.text = post.address
            gpsText.text = post.gps.toString()

            if (post.activeLike) {
                buttonLike.setImageResource(R.drawable.ic_baseline_favoritered_24)
                itemLike.setTextColor(0xFF0000)
                post.itemLike = post.itemLike!! + 1

            } else {
                buttonLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                itemLike.setTextColor(0x999999)
                post.itemLike = post.itemLike!! - 1
            }

            if (post.activeComm) {
                buttonComment.setImageResource(R.drawable.ic_baseline_commentred_24)
                itemComment.setTextColor(0xFF0000)
                post.itemComm = post.itemComm!! + 1

            } else {
                buttonLike.setImageResource(R.drawable.ic_baseline_comment_24)
                itemComment.setTextColor(0x999999)
                post.itemComm = post.itemComm!! - 1
            }

            if (post.activeShare) {
                buttonShare.setImageResource(R.drawable.ic_baseline_sharered_24r)
                itemShare.setTextColor(0xFF0000)
                post.itemShare = post.itemShare!! + 1

            } else {
                buttonShare.setImageResource(R.drawable.ic_baseline_comment_24)
                itemShare.setTextColor(0x999999)
                post.itemShare = post.itemShare!! - 1
            }
        }
    }
}

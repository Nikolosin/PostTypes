package com.example.posttypes


import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_feed_post_card.view.*

class PostViewHolder(adapter: PostAdapter, view: View): BaseViewHolder(adapter, view) {
    init {
        with(itemView) {
            buttonLike.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    item.activeLike = !item.activeLike
                    adapter.notifyItemChanged(adapterPosition)
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
                    item.activeShare = !item.activeShare
                    adapter.notifyItemChanged(adapterPosition)
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
                post.itemLike = post.itemLike!! + 1

            } else {
                buttonLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                itemLike.setTextColor(Color.GRAY)
                post.itemLike = post.itemLike!! - 1
            }

            if (post.activeComm) {
                buttonComment.setImageResource(R.drawable.ic_baseline_commentred_24)
                itemComment.setTextColor(Color.RED)
                post.itemComm = post.itemComm!! + 1

            } else {
                buttonComment.setImageResource(R.drawable.ic_baseline_comment_24)
                itemComment.setTextColor(Color.GRAY)
                post.itemComm = post.itemComm!! - 1
            }

            if (post.activeShare) {
                buttonShare.setImageResource(R.drawable.ic_baseline_sharered_24r)
                itemShare.setTextColor(Color.RED)
                post.itemShare = post.itemShare!! - 1

            } else {
                buttonShare.setImageResource(R.drawable.ic_baseline_share_24)
                itemShare.setTextColor(Color.GRAY)
                post.itemShare = post.itemShare!! + 1
            }
        }
    }
}

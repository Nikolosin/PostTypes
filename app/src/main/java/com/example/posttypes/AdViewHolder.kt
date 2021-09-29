package com.example.posttypes

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_feed_ad_card.view.*


class AdViewHolder(adapter: PostAdapter, view: View): BaseViewHolder(adapter, view) {
    init {
        with(itemView) {
            plug.setOnClickListener(){
                val item = adapter.list[adapterPosition]
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                itemView.context.startActivity(intent)
            }
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
            name.text=post.author
            ad.text = "Реалмная запись"
            url.text = post.url
            textPost.text = post.text
            itemLike.text = post.itemLike.toString()
            itemShare.text = post.itemShare.toString()

            if (post.activeLike) {
                buttonLike.setImageResource(R.drawable.ic_baseline_favoritered_24)
                itemLike.setTextColor(Color.RED)
            } else {
                buttonLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                itemLike.setTextColor(Color.GRAY)
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
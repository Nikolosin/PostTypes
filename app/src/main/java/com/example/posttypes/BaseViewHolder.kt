package com.example.posttypes

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder(val adapter: PostAdapter, view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(post: Post)
}
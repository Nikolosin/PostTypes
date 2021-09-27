package com.example.posttypes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = listOf(
            Post(1, "15", "and","First it in our network!",
                5, 3,1, activeLike = true),
            Post(1, "15", "and","First it in our network!",
            5, 3,1, activeLike = true),
            Post(1, "15", "and","First it in our network!",
                5, 3,1, activeLike = true),
            Post(1, "15", "and","First it in our network!",
                5, 3,1, activeLike = true, postType = PostType.REPOST)

        )
        with(container) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(list)
        }

    }
}
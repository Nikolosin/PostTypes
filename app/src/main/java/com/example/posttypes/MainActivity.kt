package com.example.posttypes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_feed_ad_card.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = listOf(
            Post(
                1, "10 августа 2021 г", "Андрей", "First it in our network!",
                5, 3, 1, activeLike = true
            ),
            Post(
                2, "11 августа 2021 г", "Валера", "Second post!",
                7, 34, 1
            ),
            Post(
                3,
                "12 августа 2021 г",
                "Николай",
                "Privet",
                10,
                3,
                17,
                address = "Улица Большая 4",
                gps = Pair(55.75695, 37.61540),
                postType = PostType.EVENT,
                activeComm = true
            ),
            Post(
                4, "13 августа 2021 г", "Валера", "Second post!",
                5, 3, 1, activeLike = true, postType = PostType.REPOST
            ),
            Post(
                5,
                "14 августа 2021",
                "Нетология",
                "Нетология — все об удаленных профессиях и диджитал",
                5,
                13,
                14,
                url = "https://www.youtube.com/watch?v=ZatnpD62ai8",
                activeLike = true,
                activeShare = true,
                postType = PostType.YOUTUBE
            ),
            Post(
                6, "15 августа 2021", "Нетология", "Нетология. Сделайте шаг к новым профессии",
                51, 23, 12, url = "https://netology.ru/", activeLike = true, postType = PostType.AD
            )
        )
        with(container) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(list)
        }

    }
}
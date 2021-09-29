package com.example.posttypes

enum class PostType {
    POST, REPOST, EVENT, AD, YOUTUBE
}

data class Post(
    val id: Long,
    val data: String? = null,
    val author: String,
    val text: String? = null,
    var itemLike: Int? = null,
    var itemComm: Int? = null,
    var itemShare: Int? = null,
    var activeLike: Boolean = false,
    var activeComm: Boolean = false,
    var activeShare: Boolean = false,
    var address: String? = null,
    var gps: Pair<Double, Double>? = null,
    var url: String? = null,
    val postType: PostType = PostType.POST
) {
}


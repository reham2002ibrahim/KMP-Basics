package com.example.articles.Articles

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val status : String?,
    val totalResults : Int?,
    val articles : List<Article>?
)

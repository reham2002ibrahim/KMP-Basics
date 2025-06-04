package com.example.articles.Articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService (
    private val ktorClient: HttpClient
){
    private val country = "us"
    private val category = "business"
    private val apiKey = "2def9fc4686a4428973156121ee57b72"

    suspend fun fetchArticles(): List<Article>{
        val response : ArticleResponse = ktorClient
            .get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
            .body()

        return response.articles ?: emptyList()
    }
}
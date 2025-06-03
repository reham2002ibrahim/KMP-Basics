package com.example.articles.Articles

data class Article(
    val title : String,
    val desc : String,
    val date : String,
    val imageUrl : String
){
    companion object {
        fun create(title: String, desc: String, date: String, imageUrl: String): Article {
            return Article(title, desc, date, imageUrl)
        }
    }
}

package com.example.articles.Articles

data class ArticleStates(
    val articles :List<Article> = listOf(),
    val loading : Boolean = false,
    val error : String? = null
){
    companion object {
        fun create(): ArticleStates {
            return ArticleStates(listOf(),false,null)
        }
    }
}

package com.example.articles.Articles

import sqldelight.articles.db.ArticlesDatabase

class ArticleLocalDataSource(
    private val database: ArticlesDatabase
) {

    fun getAllArticles() : List<Article>{
        return database.articleDBQueries.selectAll(::mapToArticle).executeAsList()
    }

    fun insertAllArticles(articles : List<Article>) {
        database.articleDBQueries.transaction {
            articles.forEach { article ->
                insertArticle(article)
            }
        }
    }


    private fun insertArticle(article: Article){
        database.articleDBQueries.insertArticle(
            article.title,
            article.description,
            article.publishedAt,
            article.urlToImage
        )
    }


    private fun mapToArticle (
        title : String,
        desc : String?,
        date : String,
        imgUrl : String?
    ): Article {
        return Article(title,desc,date,imgUrl)
    }
}
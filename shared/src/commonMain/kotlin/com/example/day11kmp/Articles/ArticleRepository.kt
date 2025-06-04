package com.example.articles.Articles

import sqldelight.articles.db.ArticlesDatabase

class ArticleRepository(
    private val service: ArticleService,
    private val database: ArticleLocalDataSource
) {
    suspend fun getArticles() : List<Article>{
        val articleDb = database.getAllArticles()
        print("from db ${articleDb.count()}")
        if(articleDb.isEmpty()){
            val fetchedArticles = service.fetchArticles()
            database.insertAllArticles(fetchedArticles)
            return fetchedArticles
        }
        return articleDb

    }
}
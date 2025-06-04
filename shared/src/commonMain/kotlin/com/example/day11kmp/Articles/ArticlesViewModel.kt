package com.example.articles.Articles

import com.example.articles.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel(
    private val repo: ArticleRepository
) : BaseViewModel() {
    private var _articleStateFlow = MutableStateFlow(ArticleStates(loading = true))
    val articalState = _articleStateFlow.asStateFlow()
//    private var repo : ArticleRepository
    init {
//        val ktorClient =HttpClient{
//            install(ContentNegotiation){
//                json(Json{
//                    ignoreUnknownKeys = true
//                })
//            }
//        }
//
//        val service = ArticleService(ktorClient)
//        repo = ArticleRepository(service)
        getAllArticles()
    }

    private fun getAllArticles(){
        scope.launch {
            delay(2000)
            _articleStateFlow.emit(ArticleStates(articles = repo.getArticles()))
           // _articleStateFlow.emit(ArticleStates(articles = getFetchedArticles()))
        }
    }
}
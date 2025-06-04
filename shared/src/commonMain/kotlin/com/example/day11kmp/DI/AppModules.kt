package com.example.articles.DI

import com.example.articles.Articles.ArticleLocalDataSource
import com.example.articles.Articles.ArticleRepository
import com.example.articles.Articles.ArticleService
import com.example.articles.Articles.ArticlesViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module


val networkModule = module {
    single {
        HttpClient{
            install(ContentNegotiation){
                json(Json{
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}

val articleModule = module {
    single{ ArticleService(get()) }
    single { ArticleRepository(get(),get()) }
    single { ArticleLocalDataSource(get()) }
    single{ ArticlesViewModel(get()) }
}

val sharedModule = listOf(
    networkModule,
    articleModule
)


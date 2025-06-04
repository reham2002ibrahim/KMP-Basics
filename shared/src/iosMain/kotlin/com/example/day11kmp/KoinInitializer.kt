package com.example.day11kmp

import app.cash.sqldelight.db.SqlDriver
import com.example.articles.Articles.ArticlesViewModel
import com.example.articles.DI.sharedModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module
import sqldelight.articles.db.ArticlesDatabase

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver()}
    single<ArticlesDatabase> {ArticlesDatabase(get())}
}

fun initKoin(){
    val allModules = sharedModule + databaseModule
    startKoin {
        modules(allModules)
    }
}

class ArticleInjector : KoinComponent {
    val articleViewModel : ArticlesViewModel by inject()
}
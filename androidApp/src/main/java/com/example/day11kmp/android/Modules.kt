package com.example.day11kmp.android

import app.cash.sqldelight.db.SqlDriver
import com.example.articles.Articles.ArticlesViewModel
import com.example.articles.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sqldelight.articles.db.ArticlesDatabase

val viewModelModule = module {
    viewModel { ArticlesViewModel(get()) }
}

val databaseModule = module {
    single<SqlDriver> {
        DatabaseDriverFactory(androidContext()).createDriver()
    }
    single<ArticlesDatabase> { ArticlesDatabase(get()) }
}
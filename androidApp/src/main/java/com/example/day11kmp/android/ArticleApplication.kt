package com.example.day11kmp.android

import android.app.Application
import com.example.articles.DI.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArticleApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val allModules = sharedModule + viewModelModule + databaseModule
        startKoin {
            androidContext(this@ArticleApplication)
            modules(allModules)
        }
    }
}
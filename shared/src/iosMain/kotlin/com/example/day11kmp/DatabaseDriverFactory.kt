package com.example.day11kmp

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import sqldelight.articles.db.ArticlesDatabase

actual class DatabaseDriverFactory() {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = ArticlesDatabase.Schema,
            name = "ArticlesDatabase.database.db"
        )
}
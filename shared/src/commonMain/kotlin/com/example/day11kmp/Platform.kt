package com.example.day11kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
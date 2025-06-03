package com.example.day11kmp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

actual open class BaseViewModel  {
    actual val scope : CoroutineScope = CoroutineScope(Dispatchers.Main)

    fun clean(){
        scope.cancel()
    }
}
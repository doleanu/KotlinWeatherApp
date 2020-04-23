package com.example.kotlinweatherapp.internal

import kotlinx.coroutines.*

fun<T> lazyDeerred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}
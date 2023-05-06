package com.raxerz.wordsearch.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {

    fun io(): CoroutineDispatcher

    fun main(): CoroutineDispatcher

    fun default(): CoroutineDispatcher

    fun unconfined(): CoroutineDispatcher
}

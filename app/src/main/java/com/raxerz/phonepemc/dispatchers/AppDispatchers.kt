package com.raxerz.wordsearch.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppDispatchers : CoroutineDispatchers {

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun main(): CoroutineDispatcher = Dispatchers.Main

    override fun default(): CoroutineDispatcher = Dispatchers.Default

    override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}

package com.raxerz.phonepemc.usecase

interface UseCase<in Param, out Result> {
    suspend fun perform(params: Param): Result
}
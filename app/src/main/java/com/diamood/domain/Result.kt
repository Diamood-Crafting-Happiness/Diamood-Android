package com.diamood.domain

sealed class Result {
    object Success : Result()
    object Failure : Result()
}

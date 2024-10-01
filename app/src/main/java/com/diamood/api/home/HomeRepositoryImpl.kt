package com.diamood.api.home

import javax.inject.Inject

interface HomeRepository {
    fun getUserLoginStatus(): Boolean
}

class HomeRepositoryImpl @Inject constructor() : HomeRepository {

    override fun getUserLoginStatus(): Boolean {
        return false
    }
}

class HomeRepositoryFakeImpl : HomeRepository {

    var fakeUserLoginStatus = false

    override fun getUserLoginStatus(): Boolean {
        return fakeUserLoginStatus
    }
}
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

class HomeRepositoryFakeImpl(userLogged: Boolean) : HomeRepository {

    private var fakeUserLoginStatus = userLogged

    override fun getUserLoginStatus(): Boolean {
        return fakeUserLoginStatus
    }
}

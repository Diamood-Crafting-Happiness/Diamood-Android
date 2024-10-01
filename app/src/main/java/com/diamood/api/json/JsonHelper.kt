package com.diamood.api.json

import android.app.Application
import com.diamood.data.login.Country
import kotlinx.serialization.json.Json
import javax.inject.Inject

interface JsonHelper {
    fun getAsset(path: String): List<Country>
}

class JsonHelperIml @Inject constructor(private val context: Application) : JsonHelper {

    override fun getAsset(path: String): List<Country> {
        val asset = context.assets.open(path).bufferedReader().use { it.readText() }
        val values = Json.decodeFromString<List<Country>>(asset)
        return values
    }
}

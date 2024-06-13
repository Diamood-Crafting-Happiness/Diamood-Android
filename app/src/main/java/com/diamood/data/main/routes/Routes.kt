package com.diamood.data.main.routes

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Serializable
sealed class Routes {
    companion object {
        val typeMap = mapOf(typeOf<Routes>() to serializableType<Routes>())

        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<Routes>(typeMap)
    }

    @Serializable
    data object HomeRoute : Routes() {
        val typeMap = mapOf(typeOf<HomeRoute>() to serializableType<HomeRoute>())
    }

    @Serializable
    data object AddRoute : Routes() {
        val typeMap = mapOf(typeOf<AddRoute>() to serializableType<AddRoute>())
    }

    @Serializable
    data object ShopRoute : Routes() {
        val typeMap = mapOf(typeOf<ShopRoute>() to serializableType<AddRoute>())
    }
}

inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        bundle.getString(key)?.let<String, T>(json::decodeFromString)

    override fun parseValue(value: String): T = json.decodeFromString(value)

    override fun serializeAsValue(value: T): String = json.encodeToString(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, json.encodeToString(value))
    }
}

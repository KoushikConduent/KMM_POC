package com.kmm_poc.common.network

import com.kmm_poc.common.entity.LoginEntity
import com.kmm_poc.common.utils.Constants.LOGIN_API
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiClient {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun processLoginReq(): LoginEntity {
        return httpClient
            .get(LOGIN_API)
            .body<LoginEntity>()
    }
}
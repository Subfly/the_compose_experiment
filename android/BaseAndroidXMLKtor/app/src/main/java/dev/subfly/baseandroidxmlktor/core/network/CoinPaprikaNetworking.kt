package dev.subfly.baseandroidxmlktor.core.network

import dev.subfly.baseandroidxmlktor.core.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val CONNECTION_TIMEOUT: Int = 10_000 // 10 SECOND

object CoinPaprikaNetworking {
    val client by lazy {
        HttpClient(Android) {
            // Engine Setup
            engine {
                connectTimeout = CONNECTION_TIMEOUT
            }

            // Logger Setup
            install(Logging) {
                level = LogLevel.BODY
                logger = Logger.DEFAULT
            }

            // Content Negotiation Setup
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }

            // Default Request Setup
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = Constants.COIN_PAPRIKA_BASE_URL
                }
            }
        }
    }
}

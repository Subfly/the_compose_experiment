package dev.subfly.kmpktorcoinbase.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object CoinPaprikaNetworking {
    val client by lazy {
        HttpClient {
            // Logger Setup
            install(Logging) {
                level = LogLevel.NONE
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
        }
    }
}

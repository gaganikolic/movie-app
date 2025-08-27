package com.example.myapplication

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun setupKtorClient(): HttpClient {
    return HttpClient(CIO) {
        val token =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOWZiZjJhOWVjMTkzN2MzNzI4NTEwNzY4MTZjZjMwNiIsIm5iZiI6MTc1NTY5MjExMC40M" +
                    "zI5OTk4LCJzdWIiOiI2OGE1YmM0ZTQzYzQwYjg0ZTM4MzRiYTIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.wlbrd7NOmUl" +
                    "JBDnBbbRGR3HTLP67q66yslZpb04BZL4"
        val apiKey = "a9fbf2a9ec1937c372851076816cf306"

        install(HttpTimeout) {
            socketTimeoutMillis = 10_000
            connectTimeoutMillis = 10_000
            requestTimeoutMillis = 10_000
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }

        install(Logging) {
            logger = Logger.DEFAULT
        }

        install(DefaultRequest) {
            url("https://api.themoviedb.org/3/")
            headers.append(HttpHeaders.Authorization, "Bearer $token")
            accept(ContentType.Application.Json)
        }

    }
}



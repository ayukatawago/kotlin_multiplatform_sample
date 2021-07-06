package com.example.shared.video

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.math.min

class VideoRepository {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun fetchVideos(count: UInt): List<Video> {
        val videoList = fetchVideo() ?: return emptyList()
        return videoList.slice(0 until min(videoList.size, count.toInt()))
    }

    private suspend fun fetchVideo(): List<Video>? =
        try {
            client.get {
                url(Url(URL))
            }
        } catch (e: Exception) {
            null
        }

    companion object {
        private const val URL = "https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/"
    }
}

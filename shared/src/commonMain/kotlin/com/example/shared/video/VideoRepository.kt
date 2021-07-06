package com.example.shared.video

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
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

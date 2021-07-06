import com.example.shared.Platform
import kotlinx.browser.window
import kotlinx.coroutines.*
import react.*
import react.dom.div
import react.dom.h1
import react.dom.h3

@JsExport
class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        unwatchedVideos = listOf()
        watchedVideos = listOf()

        val mainScope = MainScope()
        mainScope.launch {
            val videos = fetchVideos()
            setState {
                unwatchedVideos = videos
            }
        }
    }

    override fun RBuilder.render() {
        val platform = Platform().platform
        h1 {
            +"Hello, $platform"
        }
        div {
            h3 {
                +"Videos to watch"
            }
            videoList {
                videos = state.unwatchedVideos
                selectedVideo = state.currentVideo
                onSelectVideo = ::setCurrentVideo
            }

            h3 {
                +"Videos watched"
            }
            videoList {
                videos = state.watchedVideos
                selectedVideo = state.currentVideo
                onSelectVideo = ::setCurrentVideo
            }
            state.currentVideo?.let {
                videoPlayer {
                    video = it
                    isUnwatchedVideo = it in state.unwatchedVideos
                    onWatchedButtonPressed = {
                        if (it in state.unwatchedVideos) {
                            setState {
                                unwatchedVideos = unwatchedVideos.minus(it)
                                watchedVideos = watchedVideos.plus(it)
                            }
                        } else {
                            setState {
                                unwatchedVideos = unwatchedVideos.plus(it)
                                watchedVideos = watchedVideos.minus(it)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setCurrentVideo(video: Video) {
        setState {
            currentVideo = video
        }
    }

    private suspend fun fetchVideos(): List<Video> = coroutineScope {
        (1..25).map { id ->
            async {
                fetchVideo(id)
            }
        }.awaitAll()
            .filterNotNull()
    }

    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    private suspend fun fetchVideo(id: Int): Video? = withContext(Dispatchers.Default) {
        val response = window
            .fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
            .await()
        response.json().await() as? Video
    }
}

external interface AppState : RState {
    var currentVideo: Video?
    var unwatchedVideos: List<Video>
    var watchedVideos: List<Video>
}

import com.example.shared.Platform
import com.example.shared.video.Video
import com.example.shared.video.VideoRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h1
import react.dom.h3
import react.setState

@JsExport
class App : RComponent<RProps, AppState>() {
    private val repository = VideoRepository()

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
        repository.fetchVideos(VIDEO_COUNT)
    }

    companion object {
        private const val VIDEO_COUNT = 30u
    }
}

external interface AppState : RState {
    var currentVideo: Video?
    var unwatchedVideos: List<Video>
    var watchedVideos: List<Video>
}

import com.example.shared.Platform
import react.*
import react.dom.div
import react.dom.h1
import react.dom.h3

@JsExport
class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        unwatchedVideos = listOf(
            KotlinVideo(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
            KotlinVideo(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
            KotlinVideo(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
        )

        watchedVideos = listOf(
            KotlinVideo(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
        )
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
}

external interface AppState : RState {
    var currentVideo: Video?
    var unwatchedVideos: List<Video>
    var watchedVideos: List<Video>
}

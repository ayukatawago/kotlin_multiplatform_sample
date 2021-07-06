import com.example.shared.Platform
import react.*
import react.dom.div
import react.dom.h1
import react.dom.h3

@JsExport
class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        unwatchedVideos = listOf(
            KotlinVideo(1, "Kotlin Coroutines 1.5", "Anton Arhipov", "https://youtu.be/EVLnWOcR0is"),
            KotlinVideo(2, "kotlinx.serialization 1.2.0", "Sebastian Aigner", "https://youtu.be/698I_AH8h6s"),
            KotlinVideo(3, "New Standard Library Features", "Sebastian Aigner", "https://youtu.be/MyTkiT2I6-8")
        )

        watchedVideos = listOf(
            KotlinVideo(4, "More About Future Support for Value Classes", "Svetlana Isakova", "https://youtu.be/33JpZZz5MNw")
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

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.p

@JsExport
class VideoList : RComponent<VideoListProps, VideoListState>() {
    override fun RBuilder.render() {
        props.videos.forEach { video ->
            p {
                key = video.id.toString()
                attrs {
                    onClickFunction = {
                        setState {
                            selectedVideo = video
                        }
                    }
                }
                if (video == state.selectedVideo) {
                    +"▶ "
                }
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}

external interface VideoListProps : RProps {
    var videos: List<Video>
}

external interface VideoListState: RState {
    var selectedVideo: Video?
}

import kotlinx.browser.window
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.p

@JsExport
class VideoList : RComponent<VideoListProps, RState>() {
    override fun RBuilder.render() {
        props.videos.forEach { video ->
            p {
                key = video.id.toString()
                attrs {
                    onClickFunction = {
                        window.alert("Clicked $video")
                    }
                }
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}

external interface VideoListProps : RProps {
    var videos: List<Video>
}

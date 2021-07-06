import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.h3
import styled.css
import styled.styledButton
import styled.styledDiv

@JsExport
class VideoPlayer : RComponent<VideoPlayerProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                position = Position.absolute
                top = 10.px
                right = 10.px
            }
            h3 {
                +"${props.video.speaker}: ${props.video.title}"
            }
        }
        styledDiv {
            css {
                position = Position.absolute
                top = 80.px
                right = 10.px
                width = 1000.px
            }
            reactPlayer {
                attrs.url = props.video.videoUrl
            }
        }
        styledButton {
            css {
                display = Display.block
                backgroundColor = if(props.isUnwatchedVideo) Color.lightGreen else Color.red
            }
            attrs {
                onClickFunction = {
                    props.onWatchedButtonPressed(props.video)
                }
            }
            if(props.isUnwatchedVideo) {
                +"Mark as watched"
            }
            else {
                +"Mark as unwatched"
            }
        }
    }
}

fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit): ReactElement {
    return child(VideoPlayer::class) {
        this.attrs(handler)
    }
}

external interface VideoPlayerProps : RProps {
    var video: Video
    var onWatchedButtonPressed: (Video) -> Unit
    var isUnwatchedVideo: Boolean
}

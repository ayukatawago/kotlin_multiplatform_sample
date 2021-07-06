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
                width = 1000.px
            }
            h3 {
                +"${props.video.speaker}: ${props.video.title}"
            }
            styledButton {
                css {
                    display = Display.block
                    backgroundColor = if (props.isUnwatchedVideo) Color.lightGreen else Color.red
                }
                attrs {
                    onClickFunction = {
                        props.onWatchedButtonPressed(props.video)
                    }
                }
                if (props.isUnwatchedVideo) {
                    +"Mark as watched"
                } else {
                    +"Mark as unwatched"
                }
            }
            styledDiv {
                css {
                    display = Display.flex
                    marginBottom = 10.px
                }
                emailShareButton {
                    attrs.url = props.video.videoUrl
                    emailIcon {
                        attrs.size = 32
                        attrs.round = true
                    }
                }

                lineShareButton {
                    attrs.url = props.video.videoUrl
                    lineIcon {
                        attrs.size = 32
                        attrs.round = true
                    }
                }

                twitterShareButton {
                    attrs.url = props.video.videoUrl
                    twitterIcon {
                        attrs.size = 32
                        attrs.round = true
                    }
                }
                facebookShareButton {
                    attrs.url = props.video.videoUrl
                    facebookIcon {
                        attrs.size = 32
                        attrs.round = true
                    }
                }
            }
            reactPlayer {
                attrs.url = props.video.videoUrl
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

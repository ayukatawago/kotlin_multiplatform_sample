import com.example.shared.Platform
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h1
import react.dom.h3

@JsExport
class App : RComponent<RProps, RState>() {
    private val unwatchedVideos = listOf(
        KotlinVideo(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
        KotlinVideo(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
        KotlinVideo(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
    )

    private val watchedVideos = listOf(
        KotlinVideo(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
    )

    override fun RBuilder.render() {
        val platform = Platform().platform
        h1 {
            +"Hello, $platform"
        }
        div {
            h3 {
                +"Videos to watch"
            }
            child(VideoList::class) {
                attrs.videos = unwatchedVideos
            }

            h3 {
                +"Videos watched"
            }
            child(VideoList::class) {
                attrs.videos = watchedVideos
            }
        }
    }
}

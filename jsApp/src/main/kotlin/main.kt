import com.example.shared.Platform
import kotlinx.browser.document
import kotlinx.css.*
import react.dom.*
import styled.css
import styled.styledDiv

fun main() {
    val platform = Platform().platform

    val videoList = listOf(
        KotlinVideo(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
        KotlinVideo(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
    )
    render(document.getElementById("root")) {
        h1 {
            +"Hello, $platform"
        }
        styledDiv {
            css {
                position = Position.absolute
                top = 60.px
                left = 20.px
            }
            h3 {
                +"Video List"
            }
            videoList.forEach { video ->
                p {
                    +"${video.speaker}: ${video.title}"
                }
            }
            img {
                attrs {
                    src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
                }
            }
        }
    }
}

external interface Video {
    val id: Int
    val title: String
    val speaker: String
    val videoUrl: String
}

data class KotlinVideo(
    override val id: Int,
    override val title: String,
    override val speaker: String,
    override val videoUrl: String
): Video

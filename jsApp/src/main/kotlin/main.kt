import com.example.shared.Platform
import kotlinx.browser.document

fun main() {
    val platform = Platform().platform
    document.write("Hello, $platform")
}

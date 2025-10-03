import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import dev.meloda.overseerr.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        ResizableWindow {
            App()
        }
    }
}

@Composable
expect fun ResizableWindow(content: @Composable () -> Unit)

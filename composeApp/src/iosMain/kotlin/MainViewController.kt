import androidx.compose.ui.window.ComposeUIViewController
import dev.meloda.overseerr.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }

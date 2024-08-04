import androidx.compose.ui.window.ComposeUIViewController
import dev.meloda.overseerr.App
import dev.meloda.overseerr.di.appModule
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    startKoin {
        modules(appModule)
    }
    App()
}

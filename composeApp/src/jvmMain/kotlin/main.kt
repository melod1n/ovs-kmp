import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.meloda.overseerr.App
import dev.meloda.overseerr.appDir
import dev.meloda.overseerr.di.appModule
import net.harawata.appdirs.AppDirsFactory
import org.koin.core.context.startKoin
import java.awt.Dimension
import java.io.File

fun main() = application {
    appDir = AppDirsFactory.getInstance()
        .getUserDataDir("Overseerr-KMP", "1.0.0", "dev.meloda")

    println("appDir: $appDir")

    File(appDir).mkdirs()

    startKoin {
        modules(appModule)
    }

    Window(
        title = "Overseerr",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication
    ) {
        window.minimumSize = Dimension(350, 600)
        App()
    }
}

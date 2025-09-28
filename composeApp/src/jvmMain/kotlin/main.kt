import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.meloda.overseerr.App
import dev.meloda.overseerr.appDir
import dev.meloda.overseerr.di.appModule
import io.github.aakira.napier.Napier
import net.harawata.appdirs.AppDirsFactory
import org.koin.compose.KoinApplication
import java.awt.Dimension
import java.io.File

fun main() = application {
    LaunchedEffect(Unit) {
        appDir = AppDirsFactory.getInstance()
            .getUserDataDir("Overseerr-KMP", "1.0.0", "dev.meloda")

        Napier.d("appDir: $appDir")
        File(appDir).mkdirs()
    }

    Window(
        title = "Overseerr",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication
    ) {
        window.minimumSize = Dimension(320, 480)

        KoinApplication(application = { modules(appModule) }) {
            App()
        }
    }
}

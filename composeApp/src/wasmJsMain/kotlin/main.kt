import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeViewport
import dev.meloda.overseerr.App
import dev.meloda.overseerr.di.appModule
import kotlinx.browser.document
import kotlinx.browser.window
import org.koin.compose.KoinApplication

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        KoinApplication(application = { modules(appModule) }) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(window.innerWidth.coerceIn(360..600).dp)
                        .height(window.innerHeight.coerceIn(minimumValue = 360, maximumValue = null).dp)
                ) {
                    App()
                }
            }
        }
    }
}

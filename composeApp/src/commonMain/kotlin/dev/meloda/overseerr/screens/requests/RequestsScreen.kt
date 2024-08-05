package dev.meloda.overseerr.screens.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class RequestsScreen : Screen {

    @OptIn(
        ExperimentalMaterial3Api::class,
        ExperimentalHazeMaterialsApi::class
    )
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var isRefreshing by remember {
            mutableStateOf(false)
        }

        val hazeState = remember { HazeState() }
        val hazeStyle = HazeMaterials.ultraThin()

        val refreshState = rememberPullToRefreshState()

        var isNeedToRefresh by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(isNeedToRefresh) {
            if (isNeedToRefresh) {
                isRefreshing = true
                delay(2.seconds)
                isRefreshing = false
                isNeedToRefresh = false
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Requests") },
                    navigationIcon = {
                        IconButton(onClick = navigator::pop) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .hazeChild(
                            state = hazeState,
                            style = hazeStyle
                        ).fillMaxWidth()
                )
            }
        ) { padding ->
            val bottomPadding = padding.calculateBottomPadding()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = padding.calculateStartPadding(LayoutDirection.Ltr))
                    .padding(end = padding.calculateEndPadding(LayoutDirection.Ltr))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .haze(
                            state = hazeState,
                            style = hazeStyle
                        )
                        .pullToRefresh(
                            isRefreshing = isRefreshing,
                            state = refreshState,
                            onRefresh = { isNeedToRefresh = true }
                        )
                ) {
                    item {
                        Spacer(modifier = Modifier.height(padding.calculateTopPadding()))
                    }
                    items(count = 1000) { index ->
                        Text(
                            text = "Text #${index + 1}",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.background(Color.Red)
                        )
                        Spacer(modifier = Modifier.height(64.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(bottomPadding))
                    }
                }

                if (bottomPadding.value > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .hazeChild(
                                state = hazeState,
                                style = hazeStyle
                            )
                            .background(Color.Transparent)
                            .height(bottomPadding)
                            .fillMaxWidth()
                    )
                }

                Indicator(
                    state = refreshState,
                    isRefreshing = isRefreshing,
                    modifier = Modifier.align(Alignment.TopCenter)
                        .padding(top = padding.calculateTopPadding())
                )
            }
        }
    }
}

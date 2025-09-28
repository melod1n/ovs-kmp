package dev.meloda.overseerr.screens.main

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import dev.meloda.overseerr.screens.home.HomeScreen
import dev.meloda.overseerr.screens.login.presentation.LoginScreen
import dev.meloda.overseerr.screens.requests.presentation.RequestsScreen
import dev.meloda.overseerr.screens.settings.presentation.SettingsScreen
import dev.meloda.overseerr.theme.LocalHazeState
import dev.meloda.overseerr.theme.LocalPadding
import dev.meloda.overseerr.theme.NavigationSettings

@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeMaterialsApi::class)
@Composable
fun MainScreen() {
    val hazeState = remember { HazeState(true) }

    val navController = rememberNavController()
    NavigationSettings(navController)

    val navigationItems = remember {
        listOf(
            NavigationItem("Home", Icons.Rounded.Home, HomeScreen),
            NavigationItem("Requests", Icons.Rounded.Timer, RequestsScreen),
            NavigationItem("Settings", Icons.Rounded.Settings, SettingsScreen),
        )
    }

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(navigationItems.indexOfFirst { it.route == HomeScreen })
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .hazeEffect(
                        state = hazeState,
                        style = HazeMaterials.regular(NavigationBarDefaults.containerColor)
                    )
                    .windowInsetsPadding(TopAppBarDefaults.windowInsets)
                    .padding(
                        horizontal = 8.dp,
                        vertical = 10.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .height(42.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(50))
                        .border(
                            width = 1.dp,
                            color = Color.DarkGray.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(50)
                        )
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )

                    Text(text = "Search Movies & TV")
                }

                AsyncImage(
                    modifier = Modifier.clip(CircleShape).size(32.dp),
                    model = ImageRequest.Builder(LocalPlatformContext.current)
                        .data("https://assets.plex.tv/avatars/f812cd60749d3776cba6c6e31cdc3bcee8c5f5d9.?1707518880")
                        .size(64)
                        .build(),
                    contentDescription = null,
                    imageLoader = ImageLoader.Builder(LocalPlatformContext.current).build(),
                )
            }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .hazeEffect(
                        state = hazeState,
                        style = HazeMaterials.regular(NavigationBarDefaults.containerColor)
                    ),
                containerColor = Color.Transparent
            ) {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedItemIndex,
                        onClick = {
                            if (selectedItemIndex != index) {
                                val currentRoute = navigationItems[selectedItemIndex].route
                                selectedItemIndex = index
                                navController.navigate(item.route) {
                                    popUpTo(route = currentRoute) {
                                        inclusive = true
                                    }
                                }
                            }
                        },
                        icon = { Icon(imageVector = item.icon, contentDescription = null) },
                        label = { Text(text = item.text) }
                    )
                }
            }
        }
    ) { padding ->
        CompositionLocalProvider(
            LocalHazeState provides hazeState,
            LocalPadding provides padding
        ) {
            NavHost(
                navController = navController,
                startDestination = HomeScreen,
                enterTransition = { fadeIn(animationSpec = tween(0)) },
                exitTransition = { fadeOut(animationSpec = tween(0)) }
            ) {
                composable<HomeScreen> {
                    HomeScreen()
                }
                composable<LoginScreen> {
                    LoginScreen()
                }

                composable<RequestsScreen> {
                    RequestsScreen()
                }

                composable<SettingsScreen> {
                    SettingsScreen()
                }
            }
        }
    }
}

data class NavigationItem(
    val text: String,
    val icon: ImageVector,
    val route: Any,
)

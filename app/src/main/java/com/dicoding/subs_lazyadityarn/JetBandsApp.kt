package com.dicoding.subs_lazyadityarn

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.subs_lazyadityarn.detail.DetailBandItems
import com.dicoding.subs_lazyadityarn.home.HomeScreen
import com.dicoding.subs_lazyadityarn.navigation.Screen
import com.dicoding.subs_lazyadityarn.profile.ProfileScreen
import com.dicoding.subs_lazyadityarn.ui.theme.Subs_LazyAdityaRNTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetBandsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),

    ) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var moveNav by remember {
        mutableStateOf(true)
    }

    moveNav = !(currentRoute == Screen.Detail.route || currentRoute == Screen.Profil.route)

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Bands")
                },
                actions = {
                    if(moveNav) {
                        IconButton(onClick = {
                            navController.navigate(Screen.Profil.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }) {
                            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "about_page")
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Penggunaan Halaman Home Screen
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { bandId ->
                        navController.navigate(Screen.Detail.createRoute(bandId))
                    }
                )
            }
            // Penggunaan Halaman Profil
            composable(Screen.Profil.route) {
                ProfileScreen()
            }
            // Penggunaan halaman Detail
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("bandId") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("bandId") ?: 1
                DetailBandItems(
                    bandId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun JetBandsPrev() {
    Subs_LazyAdityaRNTheme {
        JetBandsApp()
    }
}
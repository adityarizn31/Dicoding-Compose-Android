package com.dicoding.subs_lazyadityarn.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profil : Screen("profil")
    object Detail : Screen("home/{bandId}"){
        fun createRoute(bandId: Int) = "home/$bandId"
    }
}
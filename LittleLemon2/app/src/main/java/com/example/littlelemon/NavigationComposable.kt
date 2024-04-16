package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController){
    val context = LocalContext.current
    val preferencesManager = remember {PreferencesManager(context)}
    val firstName = remember{ mutableStateOf(preferencesManager.getData("firstName","")) }
    val lastName = remember { mutableStateOf(preferencesManager.getData("lastName","")) }
    val emailAddress = remember { mutableStateOf(preferencesManager.getData("emailAddress","")) }


    NavHost(
        navController = navController,
        startDestination = if (firstName.value.isEmpty()  && lastName.value.isEmpty() && emailAddress.value.isEmpty())
                            Home.route
                            else
                            Onboarding.route
    ) {
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(Home.route){
            Home(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }

    }
}
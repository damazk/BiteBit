package com.bulat.bitebit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bulat.bitebit.home.HOME_SCREEN_ROUTE
import com.bulat.bitebit.home.homeScreen
import com.bulat.bitebit.send_btc.presentation.compose.navigateToSendBtcScreen
import com.bulat.bitebit.send_btc.presentation.compose.sendBtcScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BiteBitNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HOME_SCREEN_ROUTE
    ) {

        homeScreen(
            navController::navigateToSendBtcScreen
        )

        sendBtcScreen(navController::navigateUp)
    }
}
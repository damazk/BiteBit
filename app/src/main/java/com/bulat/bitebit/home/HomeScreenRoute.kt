package com.bulat.bitebit.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulat.bitebit.R
import com.bulat.bitebit.core.composables.topbars.BitTopBar

const val HOME_SCREEN_ROUTE = "home_screen_route"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeScreen(
    navigateToSendBtcScreen: () -> Unit
) = composable(HOME_SCREEN_ROUTE) {
    HomeScreenRoute(navigateToSendBtcScreen)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreenRoute(
    navigateToSendBtcScreen: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val address = viewModel.address
    val balance = viewModel.balance
    val transactions = viewModel.transactions
    val showErrorDialog = viewModel.showErrorDialog

    val errorMessage = viewModel.errorMessage?.let {
        if (it.isNotEmpty()) stringResource(R.string.unknown_error)
        else it
    } ?: stringResource(R.string.unknown_error)

    LaunchedEffect(Unit) {
        viewModel.loadAddress()
    }

    Scaffold(
        topBar = {
            BitTopBar(
                title = stringResource(R.string.bitcoin_wallet),
            )
        }
    ) { paddings ->

        HomeScreen(
            modifier = Modifier.padding(paddings),
            balance = balance.toString(),
            address = address,
            transactions = transactions,
            onSendBtnClick = navigateToSendBtcScreen,
            onDismissRequest = viewModel::onDismissRequest,
            onConfirmBtnClick = viewModel::onDismissRequest,
            showErrorDialog = showErrorDialog,
            errorMessage = errorMessage
        )
    }
}
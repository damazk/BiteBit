package com.bulat.bitebit.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulat.bitebit.R

const val HOME_SCREEN_ROUTE = "home_screen_route"

fun NavGraphBuilder.homeScreen(
    navigateToHistoryScreen: () -> Unit,
    navigateToSendBtcScreen: () -> Unit
) = composable(HOME_SCREEN_ROUTE) {
    HomeScreenRoute(navigateToHistoryScreen, navigateToSendBtcScreen)
}

@Composable
fun HomeScreenRoute(
    navigateToHistoryScreen: () -> Unit,
    navigateToSendBtcScreen: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val address = viewModel.address
    val balance = viewModel.balance
    val showErrorDialog = viewModel.showErrorDialog

    val errorMessage = viewModel.errorMessage?.let {
        if (it.isNotEmpty()) stringResource(R.string.unknown_error)
        else it
    } ?: stringResource(R.string.unknown_error)

    LaunchedEffect(Unit) {
        viewModel.loadAddress()
    }

    HomeScreen(
        balance = balance.toString(),
        address = address,
        onSendBtnClick = navigateToSendBtcScreen,
        onDismissRequest = viewModel::onDismissRequest,
        onConfirmBtnClick = viewModel::onDismissRequest,
        showErrorDialog = showErrorDialog,
        errorMessage = errorMessage,
        onHistoryBtnClick = { navigateToHistoryScreen() }
    )
}
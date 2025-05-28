package com.bulat.bitebit.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulat.bitebit.R
import com.bulat.bitebit.core.composables.topbars.BitTopBar

const val HISTORY_SCREEN_ROUTE = "history_screen_route"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.historyScreen(navigateUp: () -> Unit) =
    composable(HISTORY_SCREEN_ROUTE) {
        HistoryScreenRoute(navigateUp)
    }

fun NavController.navigateToHistoryScreen() =
    navigate(
        route = HISTORY_SCREEN_ROUTE,
        builder = { launchSingleTop = true }
    )

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreenRoute(
    navigateUp: () -> Unit,
    viewModel: HistoryViewModel = hiltViewModel()
) {

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
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BitTopBar(
                title = stringResource(R.string.transactions_history),
                onNavigationIconClick = navigateUp
            )
        }
    ) { paddings ->

        HistoryScreen(
            modifier = Modifier.padding(paddings),
            transactions = transactions,
            onDismissRequest = viewModel::onDismissRequest,
            onConfirmBtnClick = viewModel::onDismissRequest,
            errorMessage = errorMessage,
            showErrorDialog = showErrorDialog
        )
    }
}
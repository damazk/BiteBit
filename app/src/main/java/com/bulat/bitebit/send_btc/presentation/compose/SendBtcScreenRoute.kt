package com.bulat.bitebit.send_btc.presentation.compose

import androidx.compose.foundation.layout.fillMaxSize
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
import com.bulat.bitebit.home.BtcWalletTopBar
import com.bulat.bitebit.send_btc.presentation.SendBtcViewModel

const val SEND_BTC_ROUTE = "send_btc_route"

fun NavGraphBuilder.sendBtcScreen(navigateUp: () -> Unit) = composable(SEND_BTC_ROUTE) {
    SendBtcScreenRoute(navigateUp)
}

@Composable
fun SendBtcScreenRoute(
    navigateUp: () -> Unit,
    viewModel: SendBtcViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getSenderAddress()
    }

    val recipientAddress = viewModel.recipientAddress
    val sum = viewModel.sum
    val txId = viewModel.txId
    val showSuccessDialog = viewModel.showSuccessDialog
    val showErrorDialog = viewModel.showErrorDialog
    val errorMessage = viewModel.errorMessage?.let {
        if (it.isNotEmpty()) stringResource(R.string.unknown_error)
        else it
    } ?: stringResource(R.string.unknown_error)
    val isSumError = viewModel.isSumError

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BtcWalletTopBar(
                title = stringResource(R.string.bitcoin_wallet),
                onNavigationIconClick = navigateUp
            )
        }
    ) { paddings ->

        SendBtcScreen(
            modifier = Modifier.padding(paddings),
            recipientAddress = recipientAddress,
            onRecipientAddressChange = { viewModel.onRecipientAddressChange(it) },
            sum = sum,
            onSumChange = { viewModel.onSumChange(it) },
            isSumError = isSumError,
            onSendBtnClick = { viewModel.onSendBtnClick() },
            showSuccessDialog = showSuccessDialog,
            txId = txId,
            showErrorDialog = showErrorDialog,
            errorMessage = errorMessage,
            navigateUp = navigateUp
        )
    }
}
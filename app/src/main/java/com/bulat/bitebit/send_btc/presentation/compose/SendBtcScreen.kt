package com.bulat.bitebit.send_btc.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bulat.bitebit.R
import com.bulat.bitebit.home.BtcFilledButton
import com.bulat.bitebit.home.BtcOutlinedTextField
import com.bulat.bitebit.home.ShowSuccessTransactionDialog
import com.bulat.bitebit.utils.compose.BtcWalletErrorDialog

@Composable
fun SendBtcScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    recipientAddress: String,
    onRecipientAddressChange: (String) -> Unit,
    sum: String,
    onSumChange: (String) -> Unit,
    isSumError: Boolean,
    onSendBtnClick: () -> Unit,
    showSuccessDialog: Boolean,
    txId: String?,
    showErrorDialog: Boolean,
    errorMessage: String,
    onDismissRequest: () -> Unit
) {

    val transactionId = txId ?: stringResource(R.string.failed_to_load_transaction_id)
    val sumSupportingText = if (isSumError) stringResource(R.string.not_enough_funds) else ""

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        if (showSuccessDialog) {
            ShowSuccessTransactionDialog(
                txId = transactionId,
                titleText = stringResource(R.string.your_funds_have_been_sent),
                text = stringResource(R.string.your_transaction_id_is),
                confirmBtnText = stringResource(R.string.send_more),
                onDismissRequest = onDismissRequest,
                onConfirmBtnClick = onDismissRequest
            )
        }

        if (showErrorDialog) {
            BtcWalletErrorDialog(
                titleText = stringResource(R.string.transaction_failed),
                message = errorMessage,
                onDismissRequest = onDismissRequest,
                onConfirmBtnClick = onDismissRequest
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {

            // Recipient Address Field
            BtcOutlinedTextField(
                value = recipientAddress,
                onValueChange = onRecipientAddressChange,
                labelText = stringResource(R.string.address_to_send)
            )

            // Sum Field
            BtcOutlinedTextField(
                value = sum,
                onValueChange = onSumChange,
                labelText = stringResource(R.string.amount_to_send),
                isError = isSumError,
                supportingText = sumSupportingText,
                keyboardType = KeyboardType.Number
            )

            Spacer(Modifier.weight(1f))

            BtcFilledButton(
                text = stringResource(R.string.send),
                enabled = recipientAddress.isNotEmpty() && sum.isNotEmpty() && !isSumError,
                onClick = { onSendBtnClick() }
            )
        }
    }
}
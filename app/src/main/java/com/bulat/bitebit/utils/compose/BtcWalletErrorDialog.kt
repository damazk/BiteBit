package com.bulat.bitebit.utils.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.bulat.bitebit.R
import com.bulat.bitebit.home.BtcFilledButton

@Composable
fun BtcWalletErrorDialog(
    titleText: String,
    message: String,
    onDismissRequest: (() -> Unit)? = null,
    onConfirmBtnClick: (() -> Unit)? = null
) {

    var showDialog by rememberSaveable { mutableStateOf(true) }

    AlertDialog(
        title = {
            Text(
                text = titleText,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        text = {
            Text(stringResource(R.string.error, message))
        },
        onDismissRequest = onDismissRequest ?: { showDialog = false },
        confirmButton = {
            BtcFilledButton(
                text = stringResource(R.string.ok),
                onClick = onConfirmBtnClick ?: { showDialog = false }
            )
        },
    )
}
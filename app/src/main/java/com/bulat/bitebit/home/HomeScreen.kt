package com.bulat.bitebit.home

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulat.bitebit.R
import com.bulat.bitebit.core.composables.buttons.BitFilledButton
import com.bulat.bitebit.core.composables.items.TransactionItem
import com.bulat.bitebit.core.utils.extensions.capitalizeEachWord
import com.bulat.bitebit.model.TransactionUiItem
import com.bulat.bitebit.ui.theme.BiteBit
import com.bulat.bitebit.utils.compose.BtcWalletErrorDialog

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    balance: String,
    address: String,
    transactions: List<TransactionUiItem>,
    onDismissRequest: () -> Unit,
    onConfirmBtnClick: () -> Unit,
    showErrorDialog: Boolean,
    errorMessage: String,
    onSendBtnClick: () -> Unit
) {

    val locale = Locale.current

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        if (showErrorDialog) {
            BtcWalletErrorDialog(
                titleText = stringResource(R.string.transaction_failed),
                message = errorMessage,
                onDismissRequest = onDismissRequest,
                onConfirmBtnClick = onConfirmBtnClick
            )
        }

        // Balance
        Text(
            text = stringResource(R.string.balance, balance),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(15.dp))

        // Address
        Text(
            text = stringResource(R.string.your_btc_address, address),
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(Modifier.height(20.dp))

        BitFilledButton(
            modifier = Modifier.width(120.dp),
            text = stringResource(R.string.send),
            trailingIcon = Icons.AutoMirrored.Rounded.Send,
            onClick = onSendBtnClick
        )

        // History
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
            text = stringResource(R.string.transactions_history).capitalizeEachWord(locale),
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn(Modifier.fillMaxSize()) {
            items(transactions) {
                TransactionItem(it)
            }
        }
    }
}

@Composable
fun ShowSuccessTransactionDialog(
    txId: String,
    titleText: String,
    text: String,
    onDismissRequest: (() -> Unit)? = null,
    onConfirmBtnClick: (() -> Unit)? = null,
    confirmBtnText: String,
) {

    val context = LocalContext.current
    var showDialog by rememberSaveable { mutableStateOf(true) }

    AlertDialog(
        title = {
            Text(
                text = titleText,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        text = {
            Column {
                Text(text)
                Text(
                    text = txId,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable {
                            val url = "https://mempool.space/signet/tx/$txId"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                )
            }
        },
        onDismissRequest = onDismissRequest ?: { showDialog = false },
        confirmButton = {
            BitFilledButton(
                text = confirmBtnText,
                onClick = onConfirmBtnClick ?: { showDialog = false }
            )
        },
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
private fun PreviewHomeScreen() = BiteBit {
    HomeScreen(
        balance = "0.004000324",
        address = "jaldfgjhveklrhvkleshjdflkg32413214feferf",
        transactions = emptyList(),
        onSendBtnClick = { },
        onDismissRequest = {},
        onConfirmBtnClick = {},
        showErrorDialog = false,
        errorMessage = "",
    )
}
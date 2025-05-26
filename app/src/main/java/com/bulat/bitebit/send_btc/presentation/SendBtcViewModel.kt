package com.bulat.bitebit.send_btc.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulat.bitebit.domain.usecase.CreateAndSendTransactionUseCase
import com.bulat.bitebit.domain.usecase.GetAddressUseCase
import com.bulat.bitebit.domain.usecase.GetBalanceUseCase
import com.bulat.bitebit.domain.usecase.GetPrivateKeyWifUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendBtcViewModel @Inject constructor(
    private val createAndSendTransactionUseCase: CreateAndSendTransactionUseCase,
    private val getPrivateKeyWifUseCase: GetPrivateKeyWifUseCase,
    private val getAddressUseCase: GetAddressUseCase,
    private val getBalanceUseCase: GetBalanceUseCase
): ViewModel() {

    var recipientAddress by mutableStateOf("")
        private set

    var senderAddress = ""

    var balance = 0.0

    var sum by mutableStateOf("")
        private set

    var isSumError by mutableStateOf(false)
        private set

    var txId by mutableStateOf<String?>(null)
        private set

    var showSuccessDialog by mutableStateOf(false)
        private set

    var showErrorDialog by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onRecipientAddressChange(value: String) { recipientAddress = value }

    fun onSumChange(value: String) {
        sum = value
        val sumDouble = sum.toDoubleOrNull() ?: 0.0
        isSumError = balance < sumDouble || sumDouble <= 0.0
    }

    fun onSendBtnClick() = viewModelScope.launch(Dispatchers.IO) {

        val key = getPrivateKeyWifUseCase()
        val formattedSum = sum.toDoubleOrNull() ?: 0.0
        val satoshi = (formattedSum * 100_000_000).toLong()

        createAndSendTransactionUseCase(key, senderAddress, recipientAddress, satoshi).onSuccess {
            txId = it
            showSuccessDialog = true
        }.onFailure {
            showErrorDialog = true
            errorMessage = it.message
        }
    }

    fun getSenderAddress() = viewModelScope.launch(Dispatchers.IO) {
        getAddressUseCase().onSuccess {
            senderAddress = it
            getBalance(it)
        }.onFailure {
            showErrorDialog = true
            errorMessage = it.message
        }
    }

    fun getBalance(address: String) = viewModelScope.launch {
        getBalanceUseCase(address).onSuccess {
            balance = it
        }.onFailure {
            showErrorDialog = true
            errorMessage = it.message
        }
    }

    fun onDismissRequest() {
        showErrorDialog = false
        showSuccessDialog = false
    }
}
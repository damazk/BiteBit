package com.bulat.bitebit.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulat.bitebit.domain.usecase.GetAddressUseCase
import com.bulat.bitebit.domain.usecase.GetBalanceUseCase
import com.bulat.bitebit.domain.usecase.GetTransactionsUseCase
import com.bulat.bitebit.model.TransactionUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAddressUseCase: GetAddressUseCase,
    private val getBalanceUseCase: GetBalanceUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase
): ViewModel() {

    var address by mutableStateOf("")
        private set

    var balance by mutableDoubleStateOf(0.0)
        private set

    var transactions by mutableStateOf<List<TransactionUiItem>>(emptyList())
        private set

    var showErrorDialog by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadBalance(address: String) = viewModelScope.launch {
        getBalanceUseCase(address).onSuccess {
            balance = it
        }.onFailure {
            showErrorDialog = true
            errorMessage = it.message
        }
    }

    fun loadAddress() = viewModelScope.launch {
        getAddressUseCase().onSuccess {
            address = it
            loadBalance(it)
            getTransactions(it)
        }.onFailure {
            showErrorDialog = true
            errorMessage = it.message
        }
    }

    private fun getTransactions(address: String) = viewModelScope.launch(Dispatchers.IO) {
        getTransactionsUseCase(address).onSuccess {
            transactions = it
        }.onFailure {
            errorMessage = it.message
            showErrorDialog = true
        }
    }

    fun onDismissRequest() {
        showErrorDialog = false
    }
}
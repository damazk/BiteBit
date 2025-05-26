package com.bulat.bitebit.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulat.bitebit.domain.usecase.GetAddressUseCase
import com.bulat.bitebit.domain.usecase.GetBalanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAddressUseCase: GetAddressUseCase,
    private val getBalanceUseCase: GetBalanceUseCase
): ViewModel() {

    var address by mutableStateOf("")
        private set

    var balance by mutableDoubleStateOf(0.0)
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
        }.onFailure {
            showErrorDialog = true
            errorMessage = it.message
        }
    }

    fun onDismissRequest() {
        showErrorDialog = false
    }
}
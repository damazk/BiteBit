package com.bulat.bitebit.presentation.model

data class TransactionUiItem(
    val txid: String,
    val timestamp: Long,
    val amount: Long,
    val isReceived: Boolean
)
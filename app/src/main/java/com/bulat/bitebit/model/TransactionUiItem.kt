package com.bulat.bitebit.model

data class TransactionUiItem(
    val txid: String,
    val timestamp: Long,
    val amount: Long,
    val isReceived: Boolean
)
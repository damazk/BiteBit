package com.bulat.bitebit.data.model

data class UtxoDto(
    val txid: String,
    val vout: Int,
    val value: Long,
    val status: Status
)

data class Status(
    val confirmed: Boolean,
    val block_height: Int?
)
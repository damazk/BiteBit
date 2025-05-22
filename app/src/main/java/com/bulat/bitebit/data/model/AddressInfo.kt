package com.bulat.bitebit.data.model

data class AddressInfo(
    val chain_stats: ChainStats
)

data class ChainStats(
    val funded_txo_sum: Long,
    val spent_txo_sum: Long
)
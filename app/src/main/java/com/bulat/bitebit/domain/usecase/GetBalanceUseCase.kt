package com.bulat.bitebit.domain.usecase

import com.bulat.bitebit.data.model.AddressInfo
import com.bulat.bitebit.domain.repository.BtcApiServiceRepository
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor(
    private val btcApiServiceRepository: BtcApiServiceRepository
) {

    suspend operator fun invoke(address: String): Result<Double> {

        val addressInfo = btcApiServiceRepository.getAddressInfo(address).getOrNull() ?: return Result.failure(
            Exception("Failed to load address info of $address ")
        )

        val balance = calculateBalance(addressInfo)

        return Result.success(balance)
    }

    private fun calculateBalance(addressInfo: AddressInfo) =
        with(addressInfo) {
            val distinction = chain_stats.funded_txo_sum - chain_stats.spent_txo_sum
            (distinction / 100_000_000.0)
        }
}
package com.bulat.bitebit.domain.repository

import com.bulat.bitebit.data.model.AddressInfo
import com.bulat.bitebit.data.model.TransactionDto
import com.bulat.bitebit.data.model.UtxoDto

interface BtcApiServiceRepository {

    suspend fun getAddressInfo(address: String): Result<AddressInfo?>

    suspend fun getUtxos(address: String): Result<List<UtxoDto>?>

    suspend fun broadcastTransaction(transactionHex: String): Result<String?>

    suspend fun getTransactions(address: String): Result<List<TransactionDto>?>
}
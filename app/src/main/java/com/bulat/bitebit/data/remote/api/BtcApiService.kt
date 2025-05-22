package com.bulat.bitebit.data.remote.api

import com.bulat.bitebit.data.model.AddressInfo
import com.bulat.bitebit.data.model.TransactionDto
import com.bulat.bitebit.data.model.UtxoDto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface BtcApiService {

    @GET("api/address/{address}")
    suspend fun getAddressInfo(@Path("address") address: String): Response<AddressInfo>

    @GET("api/address/{address}/utxo")
    suspend fun getUtxos(@Path("address") address: String): Response<List<UtxoDto>>

    @POST("api/tx")
    @Headers("Content-Type: text/plain")
    suspend fun broadcastTransaction(@Body txHex: RequestBody): Response<String>

    @GET("api/address/{address}/txs")
    suspend fun getTransactions(@Path("address") address: String): Response<List<TransactionDto>>
}
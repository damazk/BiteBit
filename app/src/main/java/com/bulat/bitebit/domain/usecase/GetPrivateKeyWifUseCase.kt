package com.bulat.bitebit.domain.usecase

import com.bulat.bitebit.domain.repository.SharedPrefsRepository
import org.bitcoinj.crypto.DumpedPrivateKey
import org.bitcoinj.crypto.ECKey
import org.bitcoinj.params.TestNet3Params
import javax.inject.Inject

class GetPrivateKeyWifUseCase @Inject constructor(
    private val sharedPrefs: SharedPrefsRepository
) {
    operator fun invoke(): ECKey {
        val wif = sharedPrefs.getPrivateKeyWif(null)

        val key = if (wif != null) {
            DumpedPrivateKey.fromBase58(TestNet3Params.get(), wif).key
        } else {
            ECKey().also {
                sharedPrefs.putPrivateKeyWif(it.getPrivateKeyEncoded(TestNet3Params.get()).toString())
            }
        }

        return key
    }
}
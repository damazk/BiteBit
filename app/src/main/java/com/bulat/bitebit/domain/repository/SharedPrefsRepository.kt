package com.bulat.bitebit.domain.repository

interface SharedPrefsRepository {

    fun getString(key: String, defValue: String?): String?

    fun putString(key: String, value: String)

    fun getAddress(defValue: String?): String?

    fun putAddress(address: String)

    fun getPrivateKeyWif(defValue: String?): String?

    fun putPrivateKeyWif(key: String)

    fun clear()
}
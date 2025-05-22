package com.bulat.bitebit.di

import com.bulat.bitebit.data.remote.repository.BtcApiServiceRepositoryImpl
import com.bulat.bitebit.domain.repository.BtcApiServiceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BtcApiServiceRepositoryBinder {

    @Binds
    abstract fun bindBtcApiServiceRepository(
        repository: BtcApiServiceRepositoryImpl
    ): BtcApiServiceRepository
}
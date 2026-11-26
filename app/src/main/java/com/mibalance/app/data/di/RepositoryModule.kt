package com.mibalance.app.data.di

import com.mibalance.app.data.repository.MiBalanceRepositoryImpl
import com.mibalance.app.domain.repository.MiBalanceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMiBalanceRepository(impl: MiBalanceRepositoryImpl): MiBalanceRepository
}

package com.mibalance.app.data.di

import android.content.Context
import androidx.room.Room
import com.mibalance.app.data.database.MiBalanceDatabase
import com.mibalance.app.data.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MiBalanceDatabase =
        Room.databaseBuilder(context, MiBalanceDatabase::class.java, "mibalance_database")
            .addMigrations(MiBalanceDatabase.MIGRATION_1_2)
            .build()

    @Provides fun provideCategoriaDao(db: MiBalanceDatabase): CategoriaDao = db.categoriaDao()
    @Provides fun provideMovimientoDao(db: MiBalanceDatabase): MovimientoDao = db.movimientoDao()
    @Provides fun providePresupuestoDao(db: MiBalanceDatabase): PresupuestoDao = db.presupuestoDao()
    @Provides fun provideMetaAhorroDao(db: MiBalanceDatabase): MetaAhorroDao = db.metaAhorroDao()
}

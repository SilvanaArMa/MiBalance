package com.mibalance.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mibalance.app.data.database.dao.*
import com.mibalance.app.data.database.entity.*

@Database(
    entities = [
        CategoriaEntity::class,
        MovimientoEntity::class,
        PresupuestoEntity::class,
        MetaAhorroEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class MiBalanceDatabase : RoomDatabase() {
    abstract fun categoriaDao(): CategoriaDao
    abstract fun movimientoDao(): MovimientoDao
    abstract fun presupuestoDao(): PresupuestoDao
    abstract fun metaAhorroDao(): MetaAhorroDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""CREATE TABLE IF NOT EXISTS presupuestos (
                    idPresupuesto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    categoria TEXT NOT NULL,
                    montoLimite REAL NOT NULL,
                    periodo TEXT NOT NULL,
                    gastoActual REAL NOT NULL
                )""")
                database.execSQL("""CREATE TABLE IF NOT EXISTS metas_ahorro (
                    idMeta INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    nombre TEXT NOT NULL,
                    montoObjetivo REAL NOT NULL,
                    montoAhorrado REAL NOT NULL,
                    fechaLimite INTEGER NOT NULL
                )""")
            }
        }

        @Volatile private var INSTANCE: MiBalanceDatabase? = null

        fun obtenerBaseDatos(context: Context): MiBalanceDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MiBalanceDatabase::class.java,
                    "mibalance_database"
                ).addMigrations(MIGRATION_1_2).build().also { INSTANCE = it }
            }
    }
}

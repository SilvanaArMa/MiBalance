package com.mibalance.app.data.database.dao
import androidx.room.*
import com.mibalance.app.data.database.entity.MetaAhorroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MetaAhorroDao {
    @Insert suspend fun insertar(meta: MetaAhorroEntity)
    @Update suspend fun actualizar(meta: MetaAhorroEntity)
    @Delete suspend fun eliminar(meta: MetaAhorroEntity)
    @Query("SELECT * FROM metas_ahorro ORDER BY fechaLimite ASC")
    fun obtenerTodas(): Flow<List<MetaAhorroEntity>>
}

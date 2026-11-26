
package com.mibalance.app.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mibalance.app.data.database.entity.MovimientoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovimientoDao {

    @Insert
    suspend fun insertar(movimiento: MovimientoEntity)

    @Query("SELECT * FROM movimientos ORDER BY fecha DESC")
    fun obtenerTodos(): Flow<List<MovimientoEntity>>

    @Query("""
        SELECT * FROM movimientos
        WHERE idCategoria = :idCategoria
        ORDER BY fecha DESC
    """)
    fun obtenerPorCategoria(idCategoria: Int): Flow<List<MovimientoEntity>>

    @Update
    suspend fun actualizar(movimiento: MovimientoEntity)

    @Delete
    suspend fun eliminar(movimiento: MovimientoEntity)
}

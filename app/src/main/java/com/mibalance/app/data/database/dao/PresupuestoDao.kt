package com.mibalance.app.data.database.dao
import androidx.room.*
import com.mibalance.app.data.database.entity.PresupuestoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PresupuestoDao {
    @Insert suspend fun insertar(presupuesto: PresupuestoEntity)
    @Update suspend fun actualizar(presupuesto: PresupuestoEntity)
    @Delete suspend fun eliminar(presupuesto: PresupuestoEntity)
    @Query("SELECT * FROM presupuestos ORDER BY categoria ASC")
    fun obtenerTodos(): Flow<List<PresupuestoEntity>>
}

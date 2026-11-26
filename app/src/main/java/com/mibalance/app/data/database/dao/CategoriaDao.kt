
package com.mibalance.app.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mibalance.app.data.database.entity.CategoriaEntity
import kotlinx.coroutines.flow.Flow
import androidx.room.Transaction
import com.mibalance.app.data.database.entity.CategoriaConMovimientos
@Dao
interface CategoriaDao {

    @Insert
    suspend fun insertar(categoria: CategoriaEntity)

    @Query("SELECT * FROM categorias ORDER BY nombre ASC")
    fun obtenerTodas(): Flow<List<CategoriaEntity>>

    @Query("SELECT * FROM categorias WHERE idCategoria = :id")
    suspend fun obtenerPorId(id: Int): CategoriaEntity?

    @Update
    suspend fun actualizar(categoria: CategoriaEntity)

    @Delete
    suspend fun eliminar(categoria: CategoriaEntity)

    @Transaction
    @Query("SELECT * FROM categorias")
    fun obtenerCategoriasConMovimientos(): Flow<List<CategoriaConMovimientos>>
}

package com.mibalance.app.domain.repository

import com.mibalance.app.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MiBalanceRepository {
    fun obtenerCategorias(): Flow<List<Categoria>>
    fun obtenerMovimientos(): Flow<List<Movimiento>>
    fun obtenerPresupuestos(): Flow<List<Presupuesto>>
    fun obtenerMetas(): Flow<List<MetaAhorro>>

    suspend fun insertarCategoria(categoria: Categoria)
    suspend fun actualizarCategoria(categoria: Categoria)
    suspend fun eliminarCategoria(categoria: Categoria)
    suspend fun insertarMovimiento(movimiento: Movimiento)
    suspend fun actualizarMovimiento(movimiento: Movimiento)
    suspend fun eliminarMovimiento(movimiento: Movimiento)
    suspend fun insertarPresupuesto(presupuesto: Presupuesto)
    suspend fun actualizarPresupuesto(presupuesto: Presupuesto)
    suspend fun eliminarPresupuesto(presupuesto: Presupuesto)
    suspend fun insertarMeta(meta: MetaAhorro)
    suspend fun actualizarMeta(meta: MetaAhorro)
    suspend fun eliminarMeta(meta: MetaAhorro)
}

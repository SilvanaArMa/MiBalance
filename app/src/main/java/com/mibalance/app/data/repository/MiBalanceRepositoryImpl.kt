package com.mibalance.app.data.repository

import com.mibalance.app.data.database.dao.*
import com.mibalance.app.data.mapper.*
import com.mibalance.app.domain.model.*
import com.mibalance.app.domain.repository.MiBalanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MiBalanceRepositoryImpl @Inject constructor(
    private val categoriaDao: CategoriaDao,
    private val movimientoDao: MovimientoDao,
    private val presupuestoDao: PresupuestoDao,
    private val metaAhorroDao: MetaAhorroDao
) : MiBalanceRepository {
    override fun obtenerCategorias(): Flow<List<Categoria>> =
        categoriaDao.obtenerTodas().map { it.map { entity -> entity.toDomain() } }

    override fun obtenerMovimientos(): Flow<List<Movimiento>> =
        movimientoDao.obtenerTodos().map { it.map { entity -> entity.toDomain() } }

    override fun obtenerPresupuestos(): Flow<List<Presupuesto>> =
        presupuestoDao.obtenerTodos().map { it.map { entity -> entity.toDomain() } }

    override fun obtenerMetas(): Flow<List<MetaAhorro>> =
        metaAhorroDao.obtenerTodas().map { it.map { entity -> entity.toDomain() } }

    override suspend fun insertarCategoria(categoria: Categoria) = categoriaDao.insertar(categoria.toEntity())
    override suspend fun actualizarCategoria(categoria: Categoria) = categoriaDao.actualizar(categoria.toEntity())
    override suspend fun eliminarCategoria(categoria: Categoria) = categoriaDao.eliminar(categoria.toEntity())
    override suspend fun insertarMovimiento(movimiento: Movimiento) = movimientoDao.insertar(movimiento.toEntity())
    override suspend fun actualizarMovimiento(movimiento: Movimiento) = movimientoDao.actualizar(movimiento.toEntity())
    override suspend fun eliminarMovimiento(movimiento: Movimiento) = movimientoDao.eliminar(movimiento.toEntity())
    override suspend fun insertarPresupuesto(presupuesto: Presupuesto) = presupuestoDao.insertar(presupuesto.toEntity())
    override suspend fun actualizarPresupuesto(presupuesto: Presupuesto) = presupuestoDao.actualizar(presupuesto.toEntity())
    override suspend fun eliminarPresupuesto(presupuesto: Presupuesto) = presupuestoDao.eliminar(presupuesto.toEntity())
    override suspend fun insertarMeta(meta: MetaAhorro) = metaAhorroDao.insertar(meta.toEntity())
    override suspend fun actualizarMeta(meta: MetaAhorro) = metaAhorroDao.actualizar(meta.toEntity())
    override suspend fun eliminarMeta(meta: MetaAhorro) = metaAhorroDao.eliminar(meta.toEntity())
}

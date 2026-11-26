package com.mibalance.app.data.mapper
import com.mibalance.app.data.database.entity.PresupuestoEntity
import com.mibalance.app.domain.model.Presupuesto

fun PresupuestoEntity.toDomain() = Presupuesto(idPresupuesto, categoria, montoLimite, periodo, gastoActual)
fun Presupuesto.toEntity() = PresupuestoEntity(idPresupuesto, categoria, montoLimite, periodo, gastoActual)

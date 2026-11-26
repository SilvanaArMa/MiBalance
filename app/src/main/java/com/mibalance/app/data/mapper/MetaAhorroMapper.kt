package com.mibalance.app.data.mapper
import com.mibalance.app.data.database.entity.MetaAhorroEntity
import com.mibalance.app.domain.model.MetaAhorro

fun MetaAhorroEntity.toDomain() = MetaAhorro(idMeta, nombre, montoObjetivo, montoAhorrado, fechaLimite)
fun MetaAhorro.toEntity() = MetaAhorroEntity(idMeta, nombre, montoObjetivo, montoAhorrado, fechaLimite)

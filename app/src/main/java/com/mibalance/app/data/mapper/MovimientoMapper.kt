
package com.mibalance.app.data.mapper

import com.mibalance.app.data.database.entity.MovimientoEntity
import com.mibalance.app.domain.model.Movimiento

fun MovimientoEntity.toDomain(): Movimiento {
    return Movimiento(
        idMovimiento = idMovimiento,
        idCategoria = idCategoria,
        monto = monto,
        tipo = tipo,
        fecha = fecha,
        descripcion = descripcion
    )
}

fun Movimiento.toEntity(): MovimientoEntity {
    return MovimientoEntity(
        idMovimiento = idMovimiento,
        idCategoria = idCategoria,
        monto = monto,
        tipo = tipo,
        fecha = fecha,
        descripcion = descripcion
    )
}


package com.mibalance.app.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoriaConMovimientos(

    @Embedded
    val categoria: CategoriaEntity,

    @Relation(
        parentColumn = "idCategoria",
        entityColumn = "idCategoria"
    )
    val movimientos: List<MovimientoEntity>
)

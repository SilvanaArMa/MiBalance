
package com.mibalance.app.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "movimientos",
    foreignKeys = [
        ForeignKey(
            entity = CategoriaEntity::class,
            parentColumns = ["idCategoria"],
            childColumns = ["idCategoria"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index(value = ["idCategoria"])]
)
data class MovimientoEntity(
    @PrimaryKey(autoGenerate = true)
    val idMovimiento: Int = 0,
    val idCategoria: Int,
    val monto: Double,
    val tipo: String,
    val fecha: Long,
    val descripcion: String
)

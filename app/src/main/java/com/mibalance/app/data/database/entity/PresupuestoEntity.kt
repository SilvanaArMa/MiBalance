package com.mibalance.app.data.database.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "presupuestos")
data class PresupuestoEntity(
    @PrimaryKey(autoGenerate = true) val idPresupuesto: Int = 0,
    val categoria: String,
    val montoLimite: Double,
    val periodo: String = "Mensual",
    val gastoActual: Double = 0.0
)

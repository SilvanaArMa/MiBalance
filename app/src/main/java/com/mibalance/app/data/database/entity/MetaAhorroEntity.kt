package com.mibalance.app.data.database.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "metas_ahorro")
data class MetaAhorroEntity(
    @PrimaryKey(autoGenerate = true) val idMeta: Int = 0,
    val nombre: String,
    val montoObjetivo: Double,
    val montoAhorrado: Double = 0.0,
    val fechaLimite: Long
)

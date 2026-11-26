package com.mibalance.app.domain.model
data class MetaAhorro(
    val idMeta: Int = 0,
    val nombre: String,
    val montoObjetivo: Double,
    val montoAhorrado: Double = 0.0,
    val fechaLimite: Long
)

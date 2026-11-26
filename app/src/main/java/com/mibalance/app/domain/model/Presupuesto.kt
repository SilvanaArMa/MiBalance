package com.mibalance.app.domain.model
data class Presupuesto(
    val idPresupuesto: Int = 0,
    val categoria: String,
    val montoLimite: Double,
    val periodo: String = "Mensual",
    val gastoActual: Double = 0.0
)

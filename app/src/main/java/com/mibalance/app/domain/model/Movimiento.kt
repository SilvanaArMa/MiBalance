
package com.mibalance.app.domain.model

data class Movimiento(
    val idMovimiento: Int = 0,
    val idCategoria: Int,
    val monto: Double,
    val tipo: String,
    val fecha: Long,
    val descripcion: String
)

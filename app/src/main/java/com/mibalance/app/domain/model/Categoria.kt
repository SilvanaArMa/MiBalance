
package com.mibalance.app.domain.model

data class Categoria(
    val idCategoria: Int = 0,
    val nombre: String,
    val tipo: String,
    val descripcion: String = ""
)

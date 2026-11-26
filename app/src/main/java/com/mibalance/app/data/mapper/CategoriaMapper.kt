
package com.mibalance.app.data.mapper

import com.mibalance.app.data.database.entity.CategoriaEntity
import com.mibalance.app.domain.model.Categoria

fun CategoriaEntity.toDomain(): Categoria {
    return Categoria(
        idCategoria = idCategoria,
        nombre = nombre,
        tipo = tipo,
        descripcion = descripcion
    )
}

fun Categoria.toEntity(): CategoriaEntity {
    return CategoriaEntity(
        idCategoria = idCategoria,
        nombre = nombre,
        tipo = tipo,
        descripcion = descripcion
    )
}

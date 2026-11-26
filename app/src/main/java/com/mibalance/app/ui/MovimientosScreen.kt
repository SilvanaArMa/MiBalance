
package com.mibalance.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mibalance.app.domain.model.Categoria
import com.mibalance.app.domain.model.Movimiento
import com.mibalance.app.ui.viewmodel.MovimientosViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovimientosScreen(
    viewModel: MovimientosViewModel,
    onRegresar: () -> Unit
) {

    // Consultar categorías y movimientos desde el ViewModel
    val categorias by viewModel.categorias.collectAsStateWithLifecycle()
    val movimientos by viewModel.movimientos.collectAsStateWithLifecycle()

    var nombreCategoria by remember { mutableStateOf("") }
    var descripcionCategoria by remember { mutableStateOf("") }
    var tipoCategoria by remember { mutableStateOf("Gasto") }

    var descripcionMovimiento by remember { mutableStateOf("") }
    var montoMovimiento by remember { mutableStateOf("") }
    var tipoMovimiento by remember { mutableStateOf("Gasto") }
    var categoriaSeleccionada by remember { mutableStateOf<Int?>(null) }

    var movimientoEditando by remember {
        mutableStateOf<Movimiento?>(null)
    }

    var mensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movimientos") },
                navigationIcon = {
                    TextButton(onClick = onRegresar) {
                        Text("Volver")
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // REGISTRAR CATEGORÍAS
            item {
                Text(
                    "Administrar categorías",
                    style = MaterialTheme.typography.titleLarge
                )

                OutlinedTextField(
                    value = nombreCategoria,
                    onValueChange = { nombreCategoria = it },
                    label = { Text("Nombre de categoría") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = descripcionCategoria,
                    onValueChange = { descripcionCategoria = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row {
                    FilterChip(
                        selected = tipoCategoria == "Gasto",
                        onClick = { tipoCategoria = "Gasto" },
                        label = { Text("Gasto") }
                    )

                    Spacer(Modifier.width(8.dp))

                    FilterChip(
                        selected = tipoCategoria == "Ingreso",
                        onClick = { tipoCategoria = "Ingreso" },
                        label = { Text("Ingreso") }
                    )
                }

                Button(
                    onClick = {
                        if (nombreCategoria.isNotBlank()) {
                            viewModel.insertarCategoria(
                                Categoria(
                                    nombre = nombreCategoria.trim(),
                                    tipo = tipoCategoria,
                                    descripcion = descripcionCategoria.trim()
                                )
                            )

                            nombreCategoria = ""
                            descripcionCategoria = ""
                            mensaje = "Categoría registrada"
                        } else {
                            mensaje = "Escribe el nombre de la categoría"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar categoría")
                }
            }

            // LISTA DE CATEGORÍAS
            item {
                HorizontalDivider()

                Text(
                    "Categorías registradas",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            items(categorias, key = { it.idCategoria }) { categoria ->

                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(categoria.nombre)

                            Text(
                                categoria.tipo,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        TextButton(
                            onClick = {
                                viewModel.eliminarCategoria(categoria) { resultado ->
                                    mensaje = if (resultado) {
                                        "Categoría eliminada"
                                    } else {
                                        "No se puede eliminar: tiene movimientos"
                                    }
                                }
                            }
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }

            // FORMULARIO DE MOVIMIENTOS
            item {
                HorizontalDivider()

                Text(
                    if (movimientoEditando == null)
                        "Registrar movimiento"
                    else
                        "Editar movimiento",
                    style = MaterialTheme.typography.titleLarge
                )

                OutlinedTextField(
                    value = descripcionMovimiento,
                    onValueChange = { descripcionMovimiento = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = montoMovimiento,
                    onValueChange = { montoMovimiento = it },
                    label = { Text("Monto") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row {
                    FilterChip(
                        selected = tipoMovimiento == "Gasto",
                        onClick = { tipoMovimiento = "Gasto" },
                        label = { Text("Gasto") }
                    )

                    Spacer(Modifier.width(8.dp))

                    FilterChip(
                        selected = tipoMovimiento == "Ingreso",
                        onClick = { tipoMovimiento = "Ingreso" },
                        label = { Text("Ingreso") }
                    )
                }

                Text("Selecciona una categoría")

                categorias.forEach { categoria ->
                    FilterChip(
                        selected = categoriaSeleccionada == categoria.idCategoria,
                        onClick = {
                            categoriaSeleccionada = categoria.idCategoria
                        },
                        label = { Text(categoria.nombre) }
                    )
                }

                Button(
                    onClick = {
                        val monto = montoMovimiento.toDoubleOrNull()
                        val idCategoria = categoriaSeleccionada

                        if (
                            descripcionMovimiento.isNotBlank() &&
                            monto != null &&
                            monto > 0 &&
                            idCategoria != null
                        ) {

                            if (movimientoEditando == null) {

                                viewModel.insertarMovimiento(
                                    Movimiento(
                                        idCategoria = idCategoria,
                                        monto = monto,
                                        tipo = tipoMovimiento,
                                        fecha = System.currentTimeMillis(),
                                        descripcion = descripcionMovimiento.trim()
                                    )
                                )

                                mensaje = "Movimiento registrado"

                            } else {

                                viewModel.actualizarMovimiento(
                                    movimientoEditando!!.copy(
                                        idCategoria = idCategoria,
                                        monto = monto,
                                        tipo = tipoMovimiento,
                                        descripcion = descripcionMovimiento.trim()
                                    )
                                )

                                mensaje = "Movimiento actualizado"
                            }

                            descripcionMovimiento = ""
                            montoMovimiento = ""
                            movimientoEditando = null
                            categoriaSeleccionada = null

                        } else {
                            mensaje = "Completa los datos correctamente"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (movimientoEditando == null)
                            "Guardar movimiento"
                        else
                            "Actualizar movimiento"
                    )
                }

                if (movimientoEditando != null) {
                    TextButton(
                        onClick = {
                            movimientoEditando = null
                            descripcionMovimiento = ""
                            montoMovimiento = ""
                            categoriaSeleccionada = null
                        }
                    ) {
                        Text("Cancelar edición")
                    }
                }
            }

            // LISTA DE MOVIMIENTOS
            item {
                HorizontalDivider()

                Text(
                    "Movimientos registrados",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            items(movimientos, key = { it.idMovimiento }) { movimiento ->

                val categoria = categorias.find {
                    it.idCategoria == movimiento.idCategoria
                }

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            movimiento.descripcion,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text("Categoría: ${categoria?.nombre ?: "No disponible"}")
                        Text("Tipo: ${movimiento.tipo}")
                        Text("Monto: $${movimiento.monto}")

                        Text(
                            SimpleDateFormat(
                                "dd/MM/yyyy HH:mm",
                                Locale.getDefault()
                            ).format(Date(movimiento.fecha))
                        )

                        Row {
                            TextButton(
                                onClick = {
                                    movimientoEditando = movimiento
                                    descripcionMovimiento = movimiento.descripcion
                                    montoMovimiento = movimiento.monto.toString()
                                    tipoMovimiento = movimiento.tipo
                                    categoriaSeleccionada = movimiento.idCategoria
                                }
                            ) {
                                Text("Editar")
                            }

                            TextButton(
                                onClick = {
                                    viewModel.eliminarMovimiento(movimiento)
                                    mensaje = "Movimiento eliminado"
                                }
                            ) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }

            item {
                if (mensaje.isNotBlank()) {
                    Text(
                        mensaje,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

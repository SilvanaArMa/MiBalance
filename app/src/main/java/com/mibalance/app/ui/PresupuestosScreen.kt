package com.mibalance.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mibalance.app.domain.model.Presupuesto
import com.mibalance.app.ui.viewmodel.MovimientosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresupuestosScreen(vm: MovimientosViewModel, onBack: () -> Unit) {
    val items by vm.presupuestos.collectAsStateWithLifecycle()
    var categoria by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var periodo by remember { mutableStateOf("Mensual") }
    var editing by remember { mutableStateOf<Presupuesto?>(null) }
    var mensaje by remember { mutableStateOf("") }

    Scaffold(topBar = { TopAppBar(title = { Text("Presupuestos") }, navigationIcon = {
        TextButton(onClick = onBack) { Text("Volver") }
    }) }) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("Controla tus gastos por categoría", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(categoria, { categoria = it }, label = { Text("Categoría") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(monto, { monto = it }, label = { Text("Presupuesto (MXN)") }, modifier = Modifier.fillMaxWidth())
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(periodo == "Mensual", { periodo = "Mensual" }, label = { Text("Mensual") })
                    FilterChip(periodo == "Semanal", { periodo = "Semanal" }, label = { Text("Semanal") })
                }
                Button(onClick = {
                    val value = monto.toDoubleOrNull()
                    if (categoria.isBlank() || value == null || value <= 0) {
                        mensaje = "Escribe una categoría y un monto válido."
                    } else {
                        val old = editing
                        if (old == null) vm.insertarPresupuesto(Presupuesto(categoria = categoria.trim(), montoLimite = value, periodo = periodo))
                        else vm.actualizarPresupuesto(old.copy(categoria = categoria.trim(), montoLimite = value, periodo = periodo))
                        categoria = ""; monto = ""; editing = null; mensaje = "Presupuesto guardado."
                    }
                }, modifier = Modifier.fillMaxWidth()) { Text(if (editing == null) "Guardar presupuesto" else "Actualizar presupuesto") }
                if (mensaje.isNotBlank()) Text(mensaje, color = MaterialTheme.colorScheme.primary)
                HorizontalDivider()
                Text("Mis presupuestos", style = MaterialTheme.typography.titleLarge)
            }
            items(items, key = { it.idPresupuesto }) { item ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(14.dp)) {
                        Text(item.categoria, style = MaterialTheme.typography.titleMedium)
                        Text("$${"%.2f".format(item.montoLimite)} MXN · ${item.periodo}")
                        LinearProgressIndicator(
                            progress = { (item.gastoActual / item.montoLimite).toFloat().coerceIn(0f, 1f) },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row {
                            TextButton(onClick = { editing = item; categoria = item.categoria; monto = item.montoLimite.toString(); periodo = item.periodo }) { Text("Editar") }
                            TextButton(onClick = { vm.eliminarPresupuesto(item); mensaje = "Presupuesto eliminado." }) { Text("Eliminar") }
                        }
                    }
                }
            }
            if (items.isEmpty()) item { Text("Aún no tienes presupuestos registrados.") }
        }
    }
}

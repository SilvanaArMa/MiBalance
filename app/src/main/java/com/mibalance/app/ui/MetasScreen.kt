package com.mibalance.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mibalance.app.domain.model.MetaAhorro
import com.mibalance.app.ui.viewmodel.MovimientosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetasScreen(vm: MovimientosViewModel, onBack: () -> Unit) {
    val metas by vm.metas.collectAsStateWithLifecycle()
    var nombre by remember { mutableStateOf("") }
    var objetivo by remember { mutableStateOf("") }
    var ahorrado by remember { mutableStateOf("0") }
    var editing by remember { mutableStateOf<MetaAhorro?>(null) }
    var mensaje by remember { mutableStateOf("") }

    Scaffold(topBar = { TopAppBar(title = { Text("Metas de ahorro") }, navigationIcon = {
        TextButton(onClick = onBack) { Text("Volver") }
    }) }) { padding ->
        LazyColumn(Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                Text("Planea lo que quieres lograr", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(nombre, { nombre = it }, label = { Text("Nombre de la meta") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(objetivo, { objetivo = it }, label = { Text("Monto objetivo (MXN)") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(ahorrado, { ahorrado = it }, label = { Text("Cantidad ahorrada (MXN)") }, modifier = Modifier.fillMaxWidth())
                Button(onClick = {
                    val target = objetivo.toDoubleOrNull()
                    val saved = ahorrado.toDoubleOrNull()
                    if (nombre.isBlank() || target == null || target <= 0 || saved == null || saved < 0) {
                        mensaje = "Completa los datos con cantidades válidas."
                    } else {
                        val old = editing
                        if (old == null) vm.insertarMeta(MetaAhorro(nombre = nombre.trim(), montoObjetivo = target, montoAhorrado = saved, fechaLimite = System.currentTimeMillis()))
                        else vm.actualizarMeta(old.copy(nombre = nombre.trim(), montoObjetivo = target, montoAhorrado = saved))
                        nombre = ""; objetivo = ""; ahorrado = "0"; editing = null; mensaje = "Meta guardada."
                    }
                }, modifier = Modifier.fillMaxWidth()) { Text(if (editing == null) "Agregar meta" else "Actualizar meta") }
                if (mensaje.isNotBlank()) Text(mensaje, color = MaterialTheme.colorScheme.primary)
                HorizontalDivider()
                Text("Mis metas", style = MaterialTheme.typography.titleLarge)
            }
            items(metas, key = { it.idMeta }) { meta ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(14.dp)) {
                        Text(meta.nombre, style = MaterialTheme.typography.titleMedium)
                        Text("$${"%.2f".format(meta.montoAhorrado)} de $${"%.2f".format(meta.montoObjetivo)} MXN")
                        LinearProgressIndicator(
                            progress = { (meta.montoAhorrado / meta.montoObjetivo).toFloat().coerceIn(0f, 1f) },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row {
                            TextButton(onClick = { editing = meta; nombre = meta.nombre; objetivo = meta.montoObjetivo.toString(); ahorrado = meta.montoAhorrado.toString() }) { Text("Editar") }
                            TextButton(onClick = { vm.eliminarMeta(meta); mensaje = "Meta eliminada." }) { Text("Eliminar") }
                        }
                    }
                }
            }
            if (metas.isEmpty()) item { Text("Aún no tienes metas de ahorro.") }
        }
    }
}

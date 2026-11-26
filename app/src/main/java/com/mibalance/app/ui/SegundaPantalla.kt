package com.mibalance.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mibalance.app.ui.viewmodel.MovimientosViewModel

@Composable
fun Inicio(
    viewModel: MovimientosViewModel,
    onAbrirMovimientos: () -> Unit,
    onAbrirPresupuestos: () -> Unit,
    onAbrirMetas: () -> Unit,
    onAbrirReportes: () -> Unit,
    onAbrirConfiguracion: () -> Unit
) {
    val movimientos by viewModel.movimientos.collectAsStateWithLifecycle()
    val ingresos = movimientos.filter { it.tipo.equals("Ingreso", true) }.sumOf { it.monto }
    val gastos = movimientos.filter { it.tipo.equals("Gasto", true) }.sumOf { it.monto }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAbrirMovimientos) { Text("+") }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = true, onClick = {}, icon = { Text("I") }, label = { Text("Inicio") })
                NavigationBarItem(selected = false, onClick = onAbrirMovimientos, icon = { Text("M") }, label = { Text("Movimientos") })
                NavigationBarItem(selected = false, onClick = onAbrirPresupuestos, icon = { Text("P") }, label = { Text("Presupuesto") })
                NavigationBarItem(selected = false, onClick = onAbrirMetas, icon = { Text("A") }, label = { Text("Metas") })
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier.fillMaxSize().padding(padding).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("Hola", style = MaterialTheme.typography.headlineSmall)
                        Text("Resumen de tus finanzas", style = MaterialTheme.typography.bodyMedium)
                    }
                    TextButton(onClick = onAbrirConfiguracion) { Text("⚙") }
                }
            }
            item {
                Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)) {
                    Column(Modifier.padding(20.dp)) {
                        Text("Saldo disponible", color = MaterialTheme.colorScheme.onPrimary)
                        Text("$${"%.2f".format(ingresos - gastos)}", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
                        HorizontalDivider(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = .5f))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Ingresos: $${"%.2f".format(ingresos)}", color = MaterialTheme.colorScheme.onPrimary)
                            Text("Gastos: $${"%.2f".format(gastos)}", color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                }
            }
            item { Text("Actividad: ${movimientos.size} movimientos registrados", style = MaterialTheme.typography.titleMedium) }
            item { Button(onClick = onAbrirMovimientos, Modifier.fillMaxWidth()) { Text("Administrar movimientos") } }
            item { OutlinedButton(onClick = onAbrirPresupuestos, Modifier.fillMaxWidth()) { Text("Presupuestos") } }
            item { OutlinedButton(onClick = onAbrirMetas, Modifier.fillMaxWidth()) { Text("Metas de ahorro") } }
            item { OutlinedButton(onClick = onAbrirReportes, Modifier.fillMaxWidth()) { Text("Reportes") } }
            item { OutlinedButton(onClick = onAbrirConfiguracion, Modifier.fillMaxWidth()) { Text("Configuración") } }
            if (movimientos.isEmpty()) item { Text("Aún no hay movimientos. Usa + para registrar el primero.") }
        }
    }
}

package com.mibalance.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mibalance.app.ui.viewmodel.MovimientosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportesScreen(vm: MovimientosViewModel, onBack: () -> Unit) {
    val movimientos by vm.movimientos.collectAsStateWithLifecycle()
    val ingresos = movimientos.filter { it.tipo.equals("Ingreso", true) }.sumOf { it.monto }
    val gastos = movimientos.filter { it.tipo.equals("Gasto", true) }.sumOf { it.monto }
    Scaffold(topBar = { TopAppBar(title = { Text("Reportes") }, navigationIcon = {
        TextButton(onClick = onBack) { Text("Volver") }
    }) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Resumen de tus finanzas", style = MaterialTheme.typography.titleLarge)
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp)) {
                    Text("Ingresos registrados")
                    Text("$${"%.2f".format(ingresos)} MXN", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp)) {
                    Text("Gastos registrados")
                    Text("$${"%.2f".format(gastos)} MXN", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.error)
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp)) {
                    Text("Saldo calculado")
                    Text("$${"%.2f".format(ingresos - gastos)} MXN", style = MaterialTheme.typography.headlineMedium)
                    Text("Total de movimientos: ${movimientos.size}")
                }
            }
            if (movimientos.isEmpty()) Text("Registra movimientos para generar tu reporte.")
        }
    }
}

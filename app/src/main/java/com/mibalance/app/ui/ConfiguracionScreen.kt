package com.mibalance.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracionScreen(onBack: () -> Unit) {
    var moneda by remember { mutableStateOf("MXN - Peso mexicano") }
    var tema by remember { mutableStateOf("Claro") }
    Scaffold(topBar = { TopAppBar(title = { Text("Configuración") }, navigationIcon = {
        TextButton(onClick = onBack) { Text("Volver") }
    }) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Preferencias", style = MaterialTheme.typography.titleLarge)
            Text("Moneda")
            listOf("MXN - Peso mexicano", "USD - Dólar").forEach {
                FilterChip(selected = moneda == it, onClick = { moneda = it }, label = { Text(it) })
            }
            HorizontalDivider()
            Text("Tema")
            listOf("Claro", "Oscuro").forEach {
                FilterChip(selected = tema == it, onClick = { tema = it }, label = { Text(it) })
            }
            Text("MiBalance · Prototipo académico", style = MaterialTheme.typography.bodySmall)
            Text("Las preferencias se muestran en esta sesión.")
        }
    }
}

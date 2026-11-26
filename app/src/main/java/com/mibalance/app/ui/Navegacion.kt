package com.mibalance.app.ui

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.mibalance.app.ui.viewmodel.MovimientosViewModel

@Composable
fun NavegacionMiBalance() {
    val viewModel: MovimientosViewModel = hiltViewModel()
    var pantalla by remember { mutableIntStateOf(1) }

    when (pantalla) {
        1 -> Bienvenida(onComenzar = { pantalla = 2 })
        2 -> Inicio(
            viewModel = viewModel,
            onAbrirMovimientos = { pantalla = 3 },
            onAbrirPresupuestos = { pantalla = 4 },
            onAbrirMetas = { pantalla = 5 },
            onAbrirReportes = { pantalla = 6 },
            onAbrirConfiguracion = { pantalla = 7 }
        )
        3 -> MovimientosScreen(viewModel = viewModel, onRegresar = { pantalla = 2 })
        4 -> PresupuestosScreen(vm = viewModel, onBack = { pantalla = 2 })
        5 -> MetasScreen(vm = viewModel, onBack = { pantalla = 2 })
        6 -> ReportesScreen(vm = viewModel, onBack = { pantalla = 2 })
        7 -> ConfiguracionScreen(onBack = { pantalla = 2 })
        else -> ConfiguracionScreen(onBack = { pantalla = 2 })
    }
}

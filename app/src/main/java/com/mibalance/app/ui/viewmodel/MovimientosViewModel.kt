package com.mibalance.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mibalance.app.domain.model.*
import com.mibalance.app.domain.repository.MiBalanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovimientosViewModel @Inject constructor(
    private val repository: MiBalanceRepository
) : ViewModel() {
    val categorias = repository.obtenerCategorias().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val movimientos = repository.obtenerMovimientos().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val presupuestos = repository.obtenerPresupuestos().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val metas = repository.obtenerMetas().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun insertarCategoria(item: Categoria) = viewModelScope.launch { repository.insertarCategoria(item) }
    fun actualizarCategoria(item: Categoria) = viewModelScope.launch { repository.actualizarCategoria(item) }
    fun eliminarCategoria(item: Categoria, result: (Boolean) -> Unit = {}) = viewModelScope.launch {
        runCatching { repository.eliminarCategoria(item) }.onSuccess { result(true) }.onFailure { result(false) }
    }
    fun insertarMovimiento(item: Movimiento) = viewModelScope.launch { repository.insertarMovimiento(item) }
    fun actualizarMovimiento(item: Movimiento) = viewModelScope.launch { repository.actualizarMovimiento(item) }
    fun eliminarMovimiento(item: Movimiento) = viewModelScope.launch { repository.eliminarMovimiento(item) }
    fun insertarPresupuesto(item: Presupuesto) = viewModelScope.launch { repository.insertarPresupuesto(item) }
    fun actualizarPresupuesto(item: Presupuesto) = viewModelScope.launch { repository.actualizarPresupuesto(item) }
    fun eliminarPresupuesto(item: Presupuesto) = viewModelScope.launch { repository.eliminarPresupuesto(item) }
    fun insertarMeta(item: MetaAhorro) = viewModelScope.launch { repository.insertarMeta(item) }
    fun actualizarMeta(item: MetaAhorro) = viewModelScope.launch { repository.actualizarMeta(item) }
    fun eliminarMeta(item: MetaAhorro) = viewModelScope.launch { repository.eliminarMeta(item) }
}

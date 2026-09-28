package com.bissaulife.app.data

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NegociosViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = NegocioRepository()

    var aprovados by mutableStateOf<List<Negocio>>(emptyList())
        private set

    var carregando by mutableStateOf(false)
        private set

    fun recarregar() {
        viewModelScope.launch {
            carregando = true
            val r = repo.listarTodos()
            if (r.isSuccess) {
                aprovados = r.getOrDefault(emptyList())
                    .filter { it.status == "aprovado" }
            }
            carregando = false
        }
    }

    fun porCategoria(categoria: String): List<Negocio> {
        return aprovados.filter { it.categoria.equals(categoria, ignoreCase = true) }
    }
}

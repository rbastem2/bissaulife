package com.bissaulife.app.data

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class FavoritosViewModel(app: Application) : AndroidViewModel(app) {

    private val manager = FavoritosManager(app)

    var versao by mutableIntStateOf(0)
        private set

    // RESTAURANTES
    fun isRestauranteFav(id: Int): Boolean {
        versao
        return manager.isRestauranteFavorito(id)
    }

    fun toggleRestaurante(id: Int) {
        manager.toggleRestaurante(id)
        versao++
    }

    // MODA
    fun isModaFav(id: Int): Boolean {
        versao
        return manager.isModaFavorito(id)
    }

    fun toggleModa(id: Int) {
        manager.toggleModa(id)
        versao++
    }

    // VIAGENS
    fun isViagemFav(id: Int): Boolean {
        versao
        return manager.isViagemFavorito(id)
    }

    fun toggleViagem(id: Int) {
        manager.toggleViagem(id)
        versao++
    }

    // BELEZA
    fun isBelezaFav(id: Int): Boolean {
        versao
        return manager.isBelezaFavorito(id)
    }

    fun toggleBeleza(id: Int) {
        manager.toggleBeleza(id)
        versao++
    }

    // Listas completas para a tela de Favoritos
    fun idsRestaurantes() = manager.getRestaurantes()
    fun idsModa() = manager.getModa()
    fun idsViagens() = manager.getViagens()
    fun idsBeleza() = manager.getBeleza()
}

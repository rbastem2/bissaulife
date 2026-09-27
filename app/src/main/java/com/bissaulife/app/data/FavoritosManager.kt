package com.bissaulife.app.data

import android.content.Context
import android.content.SharedPreferences

class FavoritosManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("bissaulife_favoritos", Context.MODE_PRIVATE)

    // Chaves separadas por tipo
    private val chaveRestaurantes = "restaurantes"
    private val chaveModa = "moda"
    private val chaveViagens = "viagens"
    private val chaveBeleza = "beleza"

    // ===== RESTAURANTES =====
    fun getRestaurantes(): Set<Int> =
        prefs.getStringSet(chaveRestaurantes, emptySet())
            ?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()

    fun toggleRestaurante(id: Int) {
        val atual = getRestaurantes().toMutableSet()
        if (atual.contains(id)) atual.remove(id) else atual.add(id)
        prefs.edit().putStringSet(chaveRestaurantes, atual.map { it.toString() }.toSet()).apply()
    }

    fun isRestauranteFavorito(id: Int): Boolean = getRestaurantes().contains(id)

    // ===== MODA =====
    fun getModa(): Set<Int> =
        prefs.getStringSet(chaveModa, emptySet())
            ?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()

    fun toggleModa(id: Int) {
        val atual = getModa().toMutableSet()
        if (atual.contains(id)) atual.remove(id) else atual.add(id)
        prefs.edit().putStringSet(chaveModa, atual.map { it.toString() }.toSet()).apply()
    }

    fun isModaFavorito(id: Int): Boolean = getModa().contains(id)

    // ===== VIAGENS =====
    fun getViagens(): Set<Int> =
        prefs.getStringSet(chaveViagens, emptySet())
            ?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()

    fun toggleViagem(id: Int) {
        val atual = getViagens().toMutableSet()
        if (atual.contains(id)) atual.remove(id) else atual.add(id)
        prefs.edit().putStringSet(chaveViagens, atual.map { it.toString() }.toSet()).apply()
    }

    fun isViagemFavorito(id: Int): Boolean = getViagens().contains(id)

    // ===== BELEZA =====
    fun getBeleza(): Set<Int> =
        prefs.getStringSet(chaveBeleza, emptySet())
            ?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()

    fun toggleBeleza(id: Int) {
        val atual = getBeleza().toMutableSet()
        if (atual.contains(id)) atual.remove(id) else atual.add(id)
        prefs.edit().putStringSet(chaveBeleza, atual.map { it.toString() }.toSet()).apply()
    }

    fun isBelezaFavorito(id: Int): Boolean = getBeleza().contains(id)
}

package com.bissaulife.app.data

data class Item(
    val id: Int,
    val nome: String,
    val categoria: String,
    val preco: String,
    val imagemUrl: String,
    val nota: Double,
    val avaliacoes: Int,
    val descricao: String,
    val telefone: String,
    val endereco: String
)

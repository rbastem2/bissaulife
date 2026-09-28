package com.bissaulife.app.data

data class Negocio(
    val id: String = "",
    val nome: String = "",
    val categoria: String = "",       // Gastronomia, Moda, Viagens, Beleza
    val subcategoria: String = "",    // Restaurante, Hotel, Salao, etc
    val descricao: String = "",
    val telefone: String = "",
    val whatsapp: String = "",
    val endereco: String = "",
    val preco: String = "",
    val imagemUrl: String = "",
    val donoNome: String = "",
    val donoEmail: String = "",
    val status: String = "pendente",  // pendente, aprovado, rejeitado
    val destaque: Boolean = false,
    val planoDestaque: String = "",   // "", "semestral", "anual"
    val dataCadastro: Long = 0L,
    val dataAprovacao: Long = 0L
)

package com.bissaulife.app.data

data class Restaurante(
    val id: Int,
    val nome: String,
    val categoria: String,
    val nota: Double,
    val avaliacoes: Int,
    val distancia: String,
    val preco: String,
    val imagemUrl: String,
    val descricao: String,
    val whatsapp: String,
    val endereco: String
)

object Restaurantes {
    val lista = listOf(
        Restaurante(
            id = 1,
            nome = "Restaurante Sabor de Bissau",
            categoria = "Português • Mariscos",
            nota = 4.8,
            avaliacoes = 124,
            distancia = "2.2 km",
            preco = "5.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1414235077428-338989a2e8c0?w=600&q=80",
            descricao = "Cozinha portuguesa e frutos do mar frescos no coração de Bissau. Ambiente acolhedor com vista para o porto.",
            whatsapp = "+245955123456",
            endereco = "Avenida Amílcar Cabral, Bissau"
        ),
        Restaurante(
            id = 2,
            nome = "Tchana - Comida Tradicional",
            categoria = "Tradicional • Africana",
            nota = 4.6,
            avaliacoes = 98,
            distancia = "3.1 km",
            preco = "4.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=600&q=80",
            descricao = "Autêntica comida guineense preparada com ingredientes locais. Especialidade em caldo de mancarra e arroz de coco.",
            whatsapp = "+245955234567",
            endereco = "Praça Che Guevara, Bissau"
        ),
        Restaurante(
            id = 3,
            nome = "Doce Mel",
            categoria = "Doces • Sobremesas",
            nota = 4.7,
            avaliacoes = 75,
            distancia = "4.8 km",
            preco = "2.500 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1551024601-bec78aea704b?w=600&q=80",
            descricao = "Padaria e confeitaria artesanal. Bolos, pastéis e doces típicos feitos diariamente.",
            whatsapp = "+245955345678",
            endereco = "Rua 15 de Março, Bissau"
        ),
        Restaurante(
            id = 4,
            nome = "Cantinho do Caldo",
            categoria = "Tradicional",
            nota = 4.5,
            avaliacoes = 62,
            distancia = "1.5 km",
            preco = "3.500 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1547592180-85f173990554?w=600&q=80",
            descricao = "Especializado em caldos e sopas tradicionais da Guiné-Bissau. Receitas de família há 3 gerações.",
            whatsapp = "+245955456789",
            endereco = "Bairro de Belém, Bissau"
        ),
        Restaurante(
            id = 5,
            nome = "Marisol",
            categoria = "Mariscos",
            nota = 4.9,
            avaliacoes = 156,
            distancia = "5.2 km",
            preco = "7.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1559339352-11d035aa65de?w=600&q=80",
            descricao = "O melhor peixe grelhado e mariscos do país. Vista privilegiada para o arquipélago dos Bijagós.",
            whatsapp = "+245955567890",
            endereco = "Ilha de Bolama, Bissau"
        )
    )
}

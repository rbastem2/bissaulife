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
    val telefone: String,
    val endereco: String
)

object Restaurantes {
    val lista = listOf(
        Restaurante(
            id = 1,
            nome = "Restaurante Dona Fernanda",
            categoria = "Guineense • Peixe grelhado",
            nota = 4.0,
            avaliacoes = 121,
            distancia = "Santa Luzia",
            preco = "Sob consulta",
            imagemUrl = "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?w=600&q=80",
            descricao = "Restaurante familiar localizado no bairro de Santa Luzia. Ambiente acolhedor com um belo jardim tropical. Especialidade da casa: Bica Grelhada. Conhecido pela melhor salada de Bissau.",
            telefone = "+245966604942",
            endereco = "Bairro de Santa Luzia, Bissau"
        ),
        Restaurante(
            id = 2,
            nome = "Rasoi Restaurant",
            categoria = "Indiano • Curry",
            nota = 4.0,
            avaliacoes = 66,
            distancia = "Granja do Pessube",
            preco = "Sob consulta",
            imagemUrl = "https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=600&q=80",
            descricao = "Autentica culinaria indiana em Bissau. Pratos caseiros com temperos marcantes, opcoes de curry e vegetarianas. Ambiente simples e acolhedor.",
            telefone = "+245955904476",
            endereco = "Estrada da Granja do Pessube, Bissau"
        ),
        Restaurante(
            id = 3,
            nome = "Restaurante Sabor de Bissau",
            categoria = "Portugues • Mariscos",
            nota = 4.8,
            avaliacoes = 124,
            distancia = "Centro",
            preco = "5.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1414235077428-338989a2e8c0?w=600&q=80",
            descricao = "Cozinha portuguesa e frutos do mar frescos no coracao de Bissau. Ambiente acolhedor com vista para o porto.",
            telefone = "+245955123456",
            endereco = "Avenida Amilcar Cabral, Bissau"
        ),
        Restaurante(
            id = 4,
            nome = "Tchana - Comida Tradicional",
            categoria = "Tradicional • Africana",
            nota = 4.6,
            avaliacoes = 98,
            distancia = "Centro",
            preco = "4.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=600&q=80",
            descricao = "Autentica comida guineense preparada com ingredientes locais. Especialidade em caldo de mancarra e arroz de coco.",
            telefone = "+245955234567",
            endereco = "Praca Che Guevara, Bissau"
        ),
        Restaurante(
            id = 5,
            nome = "Doce Mel",
            categoria = "Doces • Sobremesas",
            nota = 4.7,
            avaliacoes = 75,
            distancia = "Centro",
            preco = "2.500 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1551024601-bec78aea704b?w=600&q=80",
            descricao = "Padaria e confeitaria artesanal. Bolos, pasteis e doces tipicos feitos diariamente.",
            telefone = "+245955345678",
            endereco = "Rua 15 de Marco, Bissau"
        ),
        Restaurante(
            id = 6,
            nome = "Marisol",
            categoria = "Mariscos",
            nota = 4.9,
            avaliacoes = 156,
            distancia = "Bijagos",
            preco = "7.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1559339352-11d035aa65de?w=600&q=80",
            descricao = "O melhor peixe grelhado e mariscos do pais. Vista privilegiada para o arquipelago dos Bijagos.",
            telefone = "+245955567890",
            endereco = "Ilha de Bolama, Bissau"
        )
    )
}

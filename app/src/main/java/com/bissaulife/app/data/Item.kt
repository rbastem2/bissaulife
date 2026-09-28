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

object Moda {
    val lista = listOf(
        Item(
            id = 1,
            nome = "Vestido tradicional",
            categoria = "Roupas",
            preco = "25.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=600&q=80",
            nota = 4.7,
            avaliacoes = 58,
            descricao = "Vestido com estampa africana, feito a mao por artesaos locais. Perfeito para ocasioes especiais.",
            telefone = "+245955100001",
            endereco = "Mercado de Bandim, Bissau"
        ),
        Item(
            id = 2,
            nome = "Tenis unissex",
            categoria = "Calcados",
            preco = "16.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=600&q=80",
            nota = 4.6,
            avaliacoes = 42,
            descricao = "Tenis confortavel e resistente, ideal para o dia a dia. Disponivel em varias cores.",
            telefone = "+245955100002",
            endereco = "Rua 15 de Marco, Bissau"
        ),
        Item(
            id = 3,
            nome = "Bolsa de couro",
            categoria = "Bolsas",
            preco = "18.500 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=600&q=80",
            nota = 4.8,
            avaliacoes = 33,
            descricao = "Bolsa artesanal em couro legitimo. Espacosa e elegante para o trabalho ou lazer.",
            telefone = "+245955100003",
            endereco = "Avenida Amilcar Cabral, Bissau"
        ),
        Item(
            id = 4,
            nome = "Oculos de sol",
            categoria = "Acessorios",
            preco = "8.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1511499767150-a48a237f0083?w=600&q=80",
            nota = 4.5,
            avaliacoes = 21,
            descricao = "Oculos de sol com protecao UV400. Design moderno e leve.",
            telefone = "+245955100004",
            endereco = "Praca Che Guevara, Bissau"
        ),
        Item(
            id = 5,
            nome = "Chapeu de palha",
            categoria = "Acessorios",
            preco = "5.500 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1521369909029-2afed882baee?w=600&q=80",
            nota = 4.6,
            avaliacoes = 45,
            descricao = "Chapeu de palha trancada a mao. Ideal para proteger do sol nas praias.",
            telefone = "+245955100005",
            endereco = "Ilha de Bolama, Bissau"
        ),
        Item(
            id = 6,
            nome = "Relogio classico",
            categoria = "Acessorios",
            preco = "32.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1524592094714-0f0654e20314?w=600&q=80",
            nota = 4.9,
            avaliacoes = 67,
            descricao = "Relogio classico com pulseira de aco inox. Resistente a agua.",
            telefone = "+245955100006",
            endereco = "Bairro de Belem, Bissau"
        )
    )
}

object Viagens {
    val lista = listOf(
        Item(
            id = 1,
            nome = "Hala Hotel & Aquapark",
            categoria = "Hoteis",
            preco = "Sob consulta",
            imagemUrl = "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=600&q=80",
            nota = 4.0,
            avaliacoes = 88,
            descricao = "Hotel 5 estrelas com piscina, parque aquatico, Wi-Fi gratuito, ar-condicionado e terraco. Opcoes de quartos e suites executivas. Ambiente familiar e bem localizado, a 5 minutos de carro do centro.",
            telefone = "+245956602160",
            endereco = "Av. Combatentes da Liberdade da Patria, 107, Bissau"
        ),
        Item(
            id = 2,
            nome = "Ilha de Orango",
            categoria = "Passeios",
            preco = "25.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1559827260-dc66d52bef19?w=600&q=80",
            nota = 4.9,
            avaliacoes = 56,
            descricao = "Passeio ao arquipelago dos Bijagos, um dos lugares mais bonitos da Guine-Bissau. Inclui transporte e guia.",
            telefone = "+245955200001",
            endereco = "Arquipelago dos Bijagos, Bissau"
        ),
        Item(
            id = 3,
            nome = "Hotel Bissau",
            categoria = "Hoteis",
            preco = "45.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=600&q=80",
            nota = 4.6,
            avaliacoes = 89,
            descricao = "Hotel confortavel no centro da cidade. Wi-Fi, ar-condicionado e cafe da manha incluido.",
            telefone = "+245955200002",
            endereco = "Avenida Amilcar Cabral, Bissau"
        ),
        Item(
            id = 4,
            nome = "Praia de Varela",
            categoria = "Passeios",
            preco = "18.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=600&q=80",
            nota = 4.8,
            avaliacoes = 102,
            descricao = "Uma das praias mais bonitas do pais. Areia branca, aguas calmas e restaurantes a beira-mar.",
            telefone = "+245955200003",
            endereco = "Varela, Regiao de Cacheu"
        ),
        Item(
            id = 5,
            nome = "Casa de hospedes",
            categoria = "Casas",
            preco = "22.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1564013799919-ab600027ffc6?w=600&q=80",
            nota = 4.5,
            avaliacoes = 37,
            descricao = "Casa acolhedora com 3 quartos, cozinha equipada e quintal. Perfeita para familias.",
            telefone = "+245955200004",
            endereco = "Bairro de Antula, Bissau"
        ),
        Item(
            id = 6,
            nome = "Passeio de barco",
            categoria = "Transporte",
            preco = "35.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=600&q=80",
            nota = 4.7,
            avaliacoes = 74,
            descricao = "Passeio de barco pelos rios e ilhas da Guine-Bissau. Almoco tipico incluido a bordo.",
            telefone = "+245955200005",
            endereco = "Porto de Bissau, Bissau"
        ),
        Item(
            id = 7,
            nome = "Safari na natureza",
            categoria = "Passeios",
            preco = "40.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1533105079780-92b9be482077?w=600&q=80",
            nota = 4.8,
            avaliacoes = 48,
            descricao = "Safari fotografico na Reserva de Cantanhez. Observe macacos, aves e paisagens incriveis.",
            telefone = "+245955200006",
            endereco = "Reserva de Cantanhez, Tombali"
        )
    )
}

object Beleza {
    val lista = listOf(
        Item(
            id = 1,
            nome = "Salao Glamour",
            categoria = "Cabelo • Maquiagem",
            preco = "8.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1560066984-138dadb4c035?w=600&q=80",
            nota = 4.8,
            avaliacoes = 145,
            descricao = "Salao completo com servicos de cabelo, maquiagem e unhas. Profissionais experientes.",
            telefone = "+245955300001",
            endereco = "Rua 15 de Marco, Bissau"
        ),
        Item(
            id = 2,
            nome = "Spa Relax",
            categoria = "Spa • Massagem",
            preco = "15.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1540555700478-4be289fbecef?w=600&q=80",
            nota = 4.9,
            avaliacoes = 98,
            descricao = "Massagens relaxantes, tratamentos faciais e corporais. Ambiente tranquilo e acolhedor.",
            telefone = "+245955300002",
            endereco = "Bairro de Belem, Bissau"
        ),
        Item(
            id = 3,
            nome = "Nail Studio",
            categoria = "Unhas",
            preco = "5.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1604654894610-df63bc536371?w=600&q=80",
            nota = 4.7,
            avaliacoes = 82,
            descricao = "Manicure e pedicure com design moderno. Unhas de gel, acrilico e decoracao artistica.",
            telefone = "+245955300003",
            endereco = "Praca Che Guevara, Bissau"
        ),
        Item(
            id = 4,
            nome = "Make-up Art",
            categoria = "Maquiagem",
            preco = "12.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1522335789203-aabd1fc54bc9?w=600&q=80",
            nota = 4.6,
            avaliacoes = 64,
            descricao = "Maquiagem profissional para noivas, eventos e formaturas. Atendimento a domicilio disponivel.",
            telefone = "+245955300004",
            endereco = "Avenida Amilcar Cabral, Bissau"
        ),
        Item(
            id = 5,
            nome = "Cabelo Afro",
            categoria = "Cabelo",
            preco = "10.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1560869713-7d0a29430803?w=600&q=80",
            nota = 4.8,
            avaliacoes = 127,
            descricao = "Especialistas em cabelos afro e cacheados. Trancas, penteados e tratamentos capilares.",
            telefone = "+245955300005",
            endereco = "Mercado de Bandim, Bissau"
        ),
        Item(
            id = 6,
            nome = "Studio Beleza Natural",
            categoria = "Estetica",
            preco = "9.000 FCFA",
            imagemUrl = "https://images.unsplash.com/photo-1600334089648-b0d9d3028eb2?w=600&q=80",
            nota = 4.7,
            avaliacoes = 73,
            descricao = "Tratamentos esteticos faciais e corporais. Limpeza de pele, peeling e design de sobrancelha.",
            telefone = "+245955300006",
            endereco = "Bairro de Antula, Bissau"
        )
    )
}

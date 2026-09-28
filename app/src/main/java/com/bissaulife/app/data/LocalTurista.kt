package com.bissaulife.app.data

data class LocalTurista(
    val id: Int,
    val nome: String,
    val categoria: String,
    val telefone: String,
    val endereco: String,
    val descricao: String,
    val horario: String = "",
    val imagemUrl: String = ""
)

object LocaisTurista {

    val categorias = listOf(
        "Hospitais",
        "Embaixadas",
        "Ministerios",
        "Futebol",
        "Farmacias",
        "Discotecas",
        "Supermercados",
        "Feiras",
        "Combustivel"
    )

    val lista = listOf(

        // ============ HOSPITAIS ============
        LocalTurista(
            id = 1,
            nome = "Hospital Nacional Simao Mendes",
            categoria = "Hospitais",
            telefone = "+245 966 000 000",
            endereco = "Av. Combatentes da Liberdade, Bissau",
            descricao = "Principal hospital publico da Guine-Bissau. Atendimento de urgencia 24 horas. Recomendado para emergencias graves.",
            horario = "24 horas"
        ),
        LocalTurista(
            id = 2,
            nome = "Hospital Militar Principal",
            categoria = "Hospitais",
            telefone = "+245 966 000 001",
            endereco = "Bairro de Santa Luzia, Bissau",
            descricao = "Hospital militar com atendimento de urgencia. Aceita civis em casos de emergencia.",
            horario = "24 horas"
        ),
        LocalTurista(
            id = 3,
            nome = "Clinica Bissau",
            categoria = "Hospitais",
            telefone = "+245 966 000 002",
            endereco = "Bairro do Centro, Bissau",
            descricao = "Clinica privada com atendimento geral, exames e pequenas cirurgias.",
            horario = "Seg-Sab 8h as 20h"
        ),

        // ============ EMBAIXADAS ============
        LocalTurista(
            id = 4,
            nome = "Embaixada de Portugal",
            categoria = "Embaixadas",
            telefone = "+245 966 100 001",
            endereco = "Av. Cidade de Lisboa, Bissau",
            descricao = "Consulado e embaixada portuguesa. Apoio consular a cidadaos portugueses e lusofonos.",
            horario = "Seg-Sex 9h as 16h"
        ),
        LocalTurista(
            id = 5,
            nome = "Embaixada do Brasil",
            categoria = "Embaixadas",
            telefone = "+245 966 100 002",
            endereco = "Av. Pais Vasco da Gama, Bissau",
            descricao = "Embaixada brasileira na Guine-Bissau. Servicos consulares e apoio a brasileiros.",
            horario = "Seg-Sex 9h as 17h"
        ),
        LocalTurista(
            id = 6,
            nome = "Embaixada da Franca",
            categoria = "Embaixadas",
            telefone = "+245 966 100 003",
            endereco = "Bairro de Santa Luzia, Bissau",
            descricao = "Embaixada francesa. Servicos consulares e vistos.",
            horario = "Seg-Sex 9h as 13h"
        ),

        // ============ MINISTERIOS ============
        LocalTurista(
            id = 7,
            nome = "Ministerio dos Negocios Estrangeiros",
            categoria = "Ministerios",
            telefone = "+245 966 200 001",
            endereco = "Praca dos Herois Nacionais, Bissau",
            descricao = "Ministerio responsavel pela politica externa e vistos diplomaticos.",
            horario = "Seg-Sex 8h as 15h"
        ),
        LocalTurista(
            id = 8,
            nome = "Ministerio da Saude",
            categoria = "Ministerios",
            telefone = "+245 966 200 002",
            endereco = "Av. Unidade Africana, Bissau",
            descricao = "Ministerio responsavel pela saude publica na Guine-Bissau.",
            horario = "Seg-Sex 8h as 15h"
        ),

        // ============ FUTEBOL ============
        LocalTurista(
            id = 9,
            nome = "Estadio Nacional 24 de Setembro",
            categoria = "Futebol",
            telefone = "",
            endereco = "Bairro do Estadio, Bissau",
            descricao = "Maior estadio da Guine-Bissau. Recebe jogos da selecao nacional e campeonatos locais.",
            horario = "Dias de jogo"
        ),
        LocalTurista(
            id = 10,
            nome = "Estadio Lino Correia",
            categoria = "Futebol",
            telefone = "",
            endereco = "Bairro de Belem, Bissau",
            descricao = "Estadio historico de Bissau. Sede de jogos amadores e treinos.",
            horario = "Dias de jogo"
        ),

        // ============ FARMACIAS ============
        LocalTurista(
            id = 11,
            nome = "Farmacia Central",
            categoria = "Farmacias",
            telefone = "+245 966 300 001",
            endereco = "Av. Amilcar Cabral, Bissau",
            descricao = "Farmacia central com medicamentos variados. Funcionarios falam portugues.",
            horario = "Seg-Sab 8h as 20h"
        ),
        LocalTurista(
            id = 12,
            nome = "Farmacia Popular",
            categoria = "Farmacias",
            telefone = "+245 966 300 002",
            endereco = "Bairro de Bandim, Bissau",
            descricao = "Farmacia com bons precos e medicamentos genericos.",
            horario = "Seg-Sab 8h as 19h"
        ),

        // ============ DISCOTECAS ============
        LocalTurista(
            id = 13,
            nome = "Discoteca N'Kassa",
            categoria = "Discotecas",
            telefone = "+245 966 400 001",
            endereco = "Bairro de Santa Luzia, Bissau",
            descricao = "Discoteca popular com musica africana e internacional. Ambiente animado nos fins de semana.",
            horario = "Sex-Sab 22h as 5h"
        ),

        // ============ SUPERMERCADOS ============
        LocalTurista(
            id = 14,
            nome = "Supermercado Central",
            categoria = "Supermercados",
            telefone = "+245 966 500 001",
            endereco = "Av. Amilcar Cabral, Bissau",
            descricao = "Supermercado com produtos nacionais e importados. Aceita cartao.",
            horario = "Seg-Sab 8h as 21h"
        ),
        LocalTurista(
            id = 15,
            nome = "Supermercado Nossa Senhora",
            categoria = "Supermercados",
            telefone = "+245 966 500 002",
            endereco = "Bairro de Belem, Bissau",
            descricao = "Supermercado com boa variedade de produtos frescos.",
            horario = "Seg-Sab 8h as 20h"
        ),

        // ============ FEIRAS ============
        LocalTurista(
            id = 16,
            nome = "Mercado de Bandim",
            categoria = "Feiras",
            telefone = "",
            endereco = "Bairro de Bandim, Bissau",
            descricao = "Maior mercado da Guine-Bissau. Artesanato, tecidos, frutas, especiarias e muito mais. Negocie sempre!",
            horario = "Seg-Sab 7h as 19h"
        ),
        LocalTurista(
            id = 17,
            nome = "Mercado de Bissau Novo",
            categoria = "Feiras",
            telefone = "",
            endereco = "Bairro de Bissau Novo, Bissau",
            descricao = "Mercado tradicional com produtos locais e artesanato.",
            horario = "Seg-Sab 7h as 19h"
        ),

        // ============ COMBUSTIVEL ============
        LocalTurista(
            id = 18,
            nome = "Posto Total",
            categoria = "Combustivel",
            telefone = "+245 966 600 001",
            endereco = "Av. Combatentes da Liberdade, Bissau",
            descricao = "Posto de combustivel 24 horas. Gasolina, gasoleo e servicos de conveniencia.",
            horario = "24 horas"
        ),
        LocalTurista(
            id = 19,
            nome = "Posto Petromar",
            categoria = "Combustivel",
            telefone = "+245 966 600 002",
            endereco = "Estrada de Bissau-Bissau, Bissau",
            descricao = "Posto de combustivel com gasolina e gasoleo. Bom para abastecer antes de viagens.",
            horario = "Seg-Dom 6h as 22h"
        )
    )

    fun porCategoria(categoria: String): List<LocalTurista> {
        return lista.filter { it.categoria == categoria }
    }
}

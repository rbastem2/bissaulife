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
        "Combustivel",
        "Bancos e Cambio"
    )

    val lista = listOf(

        // ============ HOSPITAIS E CLINICAS (DADOS REAIS) ============
        LocalTurista(
            id = 1,
            nome = "Hospital Nacional Simao Mendes",
            categoria = "Hospitais",
            telefone = "+245955348876",
            endereco = "Avenida Francisco Mendes, Bissau",
            descricao = "Principal hospital publico da Guine-Bissau. Referencia nacional em atendimento de urgencia, cirurgias e internamento.",
            horario = "Aberto 24 horas"
        ),
        LocalTurista(
            id = 2,
            nome = "Hospital Militar",
            categoria = "Hospitais",
            telefone = "",
            endereco = "Avenida dos Combatentes da Liberdade da Patria, Bissau",
            descricao = "Hospital militar com atendimento de urgencia. Aceita civis em casos de emergencia. Estrutura ampla e organizada.",
            horario = "Aberto 24 horas"
        ),
        LocalTurista(
            id = 3,
            nome = "Hospital 3 de Agosto",
            categoria = "Hospitais",
            telefone = "+245957482277",
            endereco = "Bissau, Guine-Bissau",
            descricao = "Hospital publico com atendimento geral e urgencia. Ponto de referencia para moradores da regiao.",
            horario = "Seg-Dom horario comercial"
        ),
        LocalTurista(
            id = 4,
            nome = "Hospital Pediatrico de Bor",
            categoria = "Hospitais",
            telefone = "+245966528890",
            endereco = "Bissau, Guine-Bissau",
            descricao = "Hospital pediatrico especializado no atendimento a criancas. Referencia em pediatria na Guine-Bissau.",
            horario = "Aberto 24 horas"
        ),
        LocalTurista(
            id = 5,
            nome = "Clinica Madrugada",
            categoria = "Hospitais",
            telefone = "+245955120537",
            endereco = "Bissau, Guine-Bissau",
            descricao = "Clinica com atendimento 24 horas. Uma das mais bem avaliadas da cidade. Equipe medica experiente.",
            horario = "Aberto 24 horas"
        ),
        LocalTurista(
            id = 6,
            nome = "Clinica Sao Jose de Bor",
            categoria = "Hospitais",
            telefone = "+245955561538",
            endereco = "Bissau, Guine-Bissau",
            descricao = "Clinica e hospital particular com atendimento geral. Boa infraestrutura e equipe atenciosa.",
            horario = "Seg-Dom horario comercial"
        ),
        LocalTurista(
            id = 7,
            nome = "Clinica Geral Dra Vicky Cabral",
            categoria = "Hospitais",
            telefone = "+245957185726",
            endereco = "Estrada de Bor, perto da rotunda de Clele, Bissau",
            descricao = "Clinica geral com servico 24 horas, 7 dias por semana. Equipe medica capacitada.",
            horario = "Aberto 24 horas"
        ),
        LocalTurista(
            id = 8,
            nome = "Raoul Follereau",
            categoria = "Hospitais",
            telefone = "+245957181823",
            endereco = "Bissau, Guine-Bissau",
            descricao = "Centro de saude com atendimento geral. Estrutura tradicional com boa reputacao na comunidade.",
            horario = "Seg-Sab horario comercial"
        ),
        LocalTurista(
            id = 9,
            nome = "CEMI-E Clinica Medica e Dentaria",
            categoria = "Hospitais",
            telefone = "+245957203440",
            endereco = "1160, Rua Djassi, Bissau",
            descricao = "Clinica medica e dentaria especializada. Consultas, exames e tratamentos odontologicos.",
            horario = "Seg-Sab 9h as 18h"
        ),
        LocalTurista(
            id = 10,
            nome = "Renato Grande Bissau",
            categoria = "Hospitais",
            telefone = "",
            endereco = "Bissau, Guine-Bissau",
            descricao = "Clinica especializada com atendimento medico diversificado. Profissionais qualificados.",
            horario = "Seg-Sab horario comercial"
        ),
        LocalTurista(
            id = 11,
            nome = "Centro de Saude de Bandim",
            categoria = "Hospitais",
            telefone = "+245957626075",
            endereco = "Bairro de Bandim, Bissau",
            descricao = "Posto de saude comunitario no coracao de Bandim. Atendimento primario e vacinacao.",
            horario = "Seg-Sab horario comercial"
        ),
        LocalTurista(
            id = 12,
            nome = "Centro de Saude do Bairro Militar",
            categoria = "Hospitais",
            telefone = "+245955731523",
            endereco = "Bairro Militar, em frente ao Mercado, Bissau",
            descricao = "Centro de saude tipo B com atendimento 24 horas. Posto de vacinacao disponivel.",
            horario = "Aberto 24 horas"
        ),
        LocalTurista(
            id = 13,
            nome = "Centro de Saude de Bairro de Ajuda",
            categoria = "Hospitais",
            telefone = "",
            endereco = "Bairro de Ajuda, Bissau",
            descricao = "Posto de saude comunitario com atendimento 24 horas. Referencia no bairro.",
            horario = "Aberto 24 horas"
        ),
        LocalTurista(
            id = 14,
            nome = "Centro de Saude de Antula",
            categoria = "Hospitais",
            telefone = "",
            endereco = "Bairro de Antula, Bissau",
            descricao = "Posto de saude comunitario com atendimento primario. Proximo a comunidade de Antula.",
            horario = "Seg-Sab horario comercial"
        ),
        LocalTurista(
            id = 15,
            nome = "Centro Materno Infantil",
            categoria = "Hospitais",
            telefone = "",
            endereco = "Bissau, Guine-Bissau",
            descricao = "Centro medico especializado em saude materna e infantil. Acompanhamento de gestantes e criancas.",
            horario = "Seg-Sab 8h as 17h"
        ),
        LocalTurista(
            id = 16,
            nome = "Centro de Saude Mental Osvaldo Maximo Vieira",
            categoria = "Hospitais",
            telefone = "",
            endereco = "Faculdade de Medicina Raul Diaz Arguelles, Bissau",
            descricao = "Servico especializado em saude mental. Apoio psicologico e psiquiatrico.",
            horario = "Seg-Sex 8h as 15h"
        ),
        LocalTurista(
            id = 17,
            nome = "Centro de Reducao Motora Dr. Ernesto Lopes",
            categoria = "Hospitais",
            telefone = "+245966096557",
            endereco = "Bissau, Guine-Bissau",
            descricao = "Centro especializado em fisioterapia e reabilitacao motora. Atendimento para recuperacao de movimentos.",
            horario = "Seg-Sex horario comercial"
        ),

        // ============ EMBAIXADAS ============
        LocalTurista(
            id = 18,
            nome = "Embaixada de Portugal",
            categoria = "Embaixadas",
            telefone = "+245 966 100 001",
            endereco = "Av. Cidade de Lisboa, Bissau",
            descricao = "Consulado e embaixada portuguesa. Apoio consular a cidadaos portugueses e lusofonos.",
            horario = "Seg-Sex 9h as 16h"
        ),
        LocalTurista(
            id = 19,
            nome = "Embaixada do Brasil",
            categoria = "Embaixadas",
            telefone = "+245 966 100 002",
            endereco = "Av. Pais Vasco da Gama, Bissau",
            descricao = "Embaixada brasileira na Guine-Bissau. Servicos consulares e apoio a brasileiros.",
            horario = "Seg-Sex 9h as 17h"
        ),
        LocalTurista(
            id = 20,
            nome = "Embaixada da Franca",
            categoria = "Embaixadas",
            telefone = "+245 966 100 003",
            endereco = "Bairro de Santa Luzia, Bissau",
            descricao = "Embaixada francesa. Servicos consulares e vistos.",
            horario = "Seg-Sex 9h as 13h"
        ),

        // ============ MINISTERIOS ============
        LocalTurista(
            id = 21,
            nome = "Ministerio dos Negocios Estrangeiros",
            categoria = "Ministerios",
            telefone = "+245 966 200 001",
            endereco = "Praca dos Herois Nacionais, Bissau",
            descricao = "Ministerio responsavel pela politica externa e vistos diplomaticos.",
            horario = "Seg-Sex 8h as 15h"
        ),
        LocalTurista(
            id = 22,
            nome = "Ministerio da Saude",
            categoria = "Ministerios",
            telefone = "+245 966 200 002",
            endereco = "Av. Unidade Africana, Bissau",
            descricao = "Ministerio responsavel pela saude publica na Guine-Bissau.",
            horario = "Seg-Sex 8h as 15h"
        ),

        // ============ FUTEBOL ============
        LocalTurista(
            id = 23,
            nome = "Estadio Nacional 24 de Setembro",
            categoria = "Futebol",
            telefone = "",
            endereco = "Bairro do Estadio, Bissau",
            descricao = "Maior estadio da Guine-Bissau. Recebe jogos da selecao nacional e campeonatos locais.",
            horario = "Dias de jogo"
        ),
        LocalTurista(
            id = 24,
            nome = "Estadio Lino Correia",
            categoria = "Futebol",
            telefone = "",
            endereco = "Bairro de Belem, Bissau",
            descricao = "Estadio historico de Bissau. Sede de jogos amadores e treinos.",
            horario = "Dias de jogo"
        ),

        // ============ FARMACIAS ============
        LocalTurista(
            id = 25,
            nome = "Farmacia Central",
            categoria = "Farmacias",
            telefone = "+245 966 300 001",
            endereco = "Av. Amilcar Cabral, Bissau",
            descricao = "Farmacia central com medicamentos variados. Funcionarios falam portugues.",
            horario = "Seg-Sab 8h as 20h"
        ),
        LocalTurista(
            id = 26,
            nome = "Farmacia Popular",
            categoria = "Farmacias",
            telefone = "+245 966 300 002",
            endereco = "Bairro de Bandim, Bissau",
            descricao = "Farmacia com bons precos e medicamentos genericos.",
            horario = "Seg-Sab 8h as 19h"
        ),

        // ============ DISCOTECAS ============
        LocalTurista(
            id = 27,
            nome = "Discoteca N'Kassa",
            categoria = "Discotecas",
            telefone = "+245 966 400 001",
            endereco = "Bairro de Santa Luzia, Bissau",
            descricao = "Discoteca popular com musica africana e internacional. Ambiente animado nos fins de semana.",
            horario = "Sex-Sab 22h as 5h"
        ),

        // ============ SUPERMERCADOS ============
        LocalTurista(
            id = 28,
            nome = "Supermercado Central",
            categoria = "Supermercados",
            telefone = "+245 966 500 001",
            endereco = "Av. Amilcar Cabral, Bissau",
            descricao = "Supermercado com produtos nacionais e importados. Aceita cartao.",
            horario = "Seg-Sab 8h as 21h"
        ),
        LocalTurista(
            id = 29,
            nome = "Supermercado Nossa Senhora",
            categoria = "Supermercados",
            telefone = "+245 966 500 002",
            endereco = "Bairro de Belem, Bissau",
            descricao = "Supermercado com boa variedade de produtos frescos.",
            horario = "Seg-Sab 8h as 20h"
        ),

        // ============ FEIRAS ============
        LocalTurista(
            id = 30,
            nome = "Mercado de Bandim",
            categoria = "Feiras",
            telefone = "",
            endereco = "Bairro de Bandim, Bissau",
            descricao = "Maior mercado da Guine-Bissau. Artesanato, tecidos, frutas, especiarias e muito mais. Negocie sempre!",
            horario = "Seg-Sab 7h as 19h"
        ),
        LocalTurista(
            id = 31,
            nome = "Mercado de Bissau Novo",
            categoria = "Feiras",
            telefone = "",
            endereco = "Bairro de Bissau Novo, Bissau",
            descricao = "Mercado tradicional com produtos locais e artesanato.",
            horario = "Seg-Sab 7h as 19h"
        ),

        // ============ COMBUSTIVEL ============
        LocalTurista(
            id = 32,
            nome = "Posto Total",
            categoria = "Combustivel",
            telefone = "+245 966 600 001",
            endereco = "Av. Combatentes da Liberdade, Bissau",
            descricao = "Posto de combustivel 24 horas. Gasolina, gasoleo e servicos de conveniencia.",
            horario = "24 horas"
        ),
        LocalTurista(
            id = 33,
            nome = "Posto Petromar",
            categoria = "Combustivel",
            telefone = "+245 966 600 002",
            endereco = "Estrada de Bissau-Bissau, Bissau",
            descricao = "Posto de combustivel com gasolina e gasoleo. Bom para abastecer antes de viagens.",
            horario = "Seg-Dom 6h as 22h"
        ),

        // ============ BANCOS E CAMBIO ============
        LocalTurista(
            id = 34,
            nome = "BCEAO - Banco Central",
            categoria = "Bancos e Cambio",
            telefone = "+245 966 700 001",
            endereco = "Av. Amilcar Cabral, Bissau",
            descricao = "Banco Central dos Estados da Africa Ocidental. Referencia para cambio oficial.",
            horario = "Seg-Sex 8h as 15h"
        ),
        LocalTurista(
            id = 35,
            nome = "BAO - Banco da Africa Ocidental",
            categoria = "Bancos e Cambio",
            telefone = "+245 966 700 002",
            endereco = "Av. Amilcar Cabral, Bissau",
            descricao = "Um dos maiores bancos comerciais da Guine-Bissau. Caixas multibanco e cambio.",
            horario = "Seg-Sex 8h as 16h"
        ),
        LocalTurista(
            id = 36,
            nome = "BIGB - Banco Internacional",
            categoria = "Bancos e Cambio",
            telefone = "+245 966 700 003",
            endereco = "Bairro do Centro, Bissau",
            descricao = "Banco Internacional da Guine-Bissau. Servicos de cambio e transferencias.",
            horario = "Seg-Sex 8h as 16h"
        ),
        LocalTurista(
            id = 37,
            nome = "Orabank Guine-Bissau",
            categoria = "Bancos e Cambio",
            telefone = "+245 966 700 004",
            endereco = "Av. Cidade de Lisboa, Bissau",
            descricao = "Banco comercial com servicos de cambio e transferencias internacionais.",
            horario = "Seg-Sex 8h as 16h"
        ),
        LocalTurista(
            id = 38,
            nome = "Ecobank Guine-Bissau",
            categoria = "Bancos e Cambio",
            telefone = "+245 966 700 005",
            endereco = "Bairro de Santa Luzia, Bissau",
            descricao = "Banco pan-africano com servicos de cambio e multicaixa.",
            horario = "Seg-Sex 8h as 16h"
        ),
        LocalTurista(
            id = 39,
            nome = "Casa de Cambio Central",
            categoria = "Bancos e Cambio",
            telefone = "+245 966 700 006",
            endereco = "Praca Che Guevara, Bissau",
            descricao = "Casa de cambio com melhores taxas. Aceita euros, dolares e FCFA.",
            horario = "Seg-Sab 8h as 18h"
        )
    )

    fun porCategoria(categoria: String): List<LocalTurista> {
        return lista.filter { it.categoria == categoria }
    }
}

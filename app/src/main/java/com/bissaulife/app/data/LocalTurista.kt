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

        // ============ HOSPITAIS (17 REAIS) ============
        LocalTurista(1, "Hospital Nacional Simao Mendes", "Hospitais", "+245955348876", "Avenida Francisco Mendes, Bissau", "Principal hospital publico da Guine-Bissau. Referencia nacional em atendimento de urgencia, cirurgias e internamento.", "Aberto 24 horas"),
        LocalTurista(2, "Hospital Militar", "Hospitais", "", "Avenida dos Combatentes da Liberdade da Patria, Bissau", "Hospital militar com atendimento de urgencia. Aceita civis em casos de emergencia. Estrutura ampla e organizada.", "Aberto 24 horas"),
        LocalTurista(3, "Hospital 3 de Agosto", "Hospitais", "+245957482277", "Bissau, Guine-Bissau", "Hospital publico com atendimento geral e urgencia. Ponto de referencia para moradores da regiao.", "Horario comercial"),
        LocalTurista(4, "Hospital Pediatrico de Bor", "Hospitais", "+245966528890", "Bissau, Guine-Bissau", "Hospital pediatrico especializado no atendimento a criancas. Referencia em pediatria na Guine-Bissau.", "Aberto 24 horas"),
        LocalTurista(5, "Clinica Madrugada", "Hospitais", "+245955120537", "Bissau, Guine-Bissau", "Clinica com atendimento 24 horas. Uma das mais bem avaliadas da cidade. Equipe medica experiente.", "Aberto 24 horas"),
        LocalTurista(6, "Clinica Sao Jose de Bor", "Hospitais", "+245955561538", "Bissau, Guine-Bissau", "Clinica e hospital particular com atendimento geral. Boa infraestrutura e equipe atenciosa.", "Horario comercial"),
        LocalTurista(7, "Clinica Geral Dra Vicky Cabral", "Hospitais", "+245957185726", "Estrada de Bor, perto da rotunda de Clele, Bissau", "Clinica geral com servico 24 horas, 7 dias por semana. Equipe medica capacitada.", "Aberto 24 horas"),
        LocalTurista(8, "Raoul Follereau", "Hospitais", "+245957181823", "Bissau, Guine-Bissau", "Centro de saude com atendimento geral. Estrutura tradicional com boa reputacao na comunidade.", "Horario comercial"),
        LocalTurista(9, "CEMI-E Clinica Medica e Dentaria", "Hospitais", "+245957203440", "1160, Rua Djassi, Bissau", "Clinica medica e dentaria especializada. Consultas, exames e tratamentos odontologicos.", "9h as 18h"),
        LocalTurista(10, "Renato Grande Bissau", "Hospitais", "", "Bissau, Guine-Bissau", "Clinica especializada com atendimento medico diversificado. Profissionais qualificados.", "Horario comercial"),
        LocalTurista(11, "Centro de Saude de Bandim", "Hospitais", "+245957626075", "Bairro de Bandim, Bissau", "Posto de saude comunitario no coracao de Bandim. Atendimento primario e vacinacao.", "Horario comercial"),
        LocalTurista(12, "Centro de Saude do Bairro Militar", "Hospitais", "+245955731523", "Bairro Militar, em frente ao Mercado, Bissau", "Centro de saude tipo B com atendimento 24 horas. Posto de vacinacao disponivel.", "Aberto 24 horas"),
        LocalTurista(13, "Centro de Saude de Bairro de Ajuda", "Hospitais", "", "Bairro de Ajuda, Bissau", "Posto de saude comunitario com atendimento 24 horas. Referencia no bairro.", "Aberto 24 horas"),
        LocalTurista(14, "Centro de Saude de Antula", "Hospitais", "", "Bairro de Antula, Bissau", "Posto de saude comunitario com atendimento primario. Proximo a comunidade de Antula.", "Horario comercial"),
        LocalTurista(15, "Centro Materno Infantil", "Hospitais", "", "Bissau, Guine-Bissau", "Centro medico especializado em saude materna e infantil. Acompanhamento de gestantes e criancas.", "8h as 17h"),
        LocalTurista(16, "Centro de Saude Mental Osvaldo Maximo Vieira", "Hospitais", "", "Faculdade de Medicina Raul Diaz Arguelles, Bissau", "Servico especializado em saude mental. Apoio psicologico e psiquiatrico.", "8h as 15h"),
        LocalTurista(17, "Centro de Reducao Motora Dr. Ernesto Lopes", "Hospitais", "+245966096557", "Bissau, Guine-Bissau", "Centro especializado em fisioterapia e reabilitacao motora. Atendimento para recuperacao de movimentos.", "Horario comercial"),

        // ============ FARMACIAS (12 REAIS) ============
        LocalTurista(18, "Alliance Pharma Sarl", "Farmacias", "", "Bissau, Guine-Bissau", "Distribuidora de produtos farmaceuticos. Fornecimento para farmacias e clinicas da regiao.", "8h30 as 18h"),
        LocalTurista(19, "Farmacia Rama", "Farmacias", "", "VC69+MF5, Avenida Pansau Na Isna, Bissau", "Farmacia tradicional com medicamentos variados. Atendimento atencioso.", "8h as 20h"),
        LocalTurista(20, "Farmacia Dahaba Group", "Farmacias", "+245955824603", "Escola de Djaal, Entrada de, Bissau", "Farmacia moderna com amplo estoque. Servicos farmaceuticos e aconselhamento.", "8h as 23h"),
        LocalTurista(21, "Farmacia Amilcar Cabral", "Farmacias", "", "VC53+923, Av. Combatente da Liberdade da Patria, Bissau", "Farmacia bem localizada com boa variedade de medicamentos.", "8h as 21h"),
        LocalTurista(22, "Farmacia Paris.GB", "Farmacias", "+245955131332", "V95Q+934, Bairro de Ajuda, Bissau", "Farmacia completa com medicamentos e produtos de saude. Aberta ate tarde.", "8h as 23h"),
        LocalTurista(23, "Farmacia Municipal", "Farmacias", "+245955850085", "VC69+V79, Avenida Pansau Na Isna, Bissau", "Farmacia municipal com bom atendimento e precos acessiveis.", "8h as 20h"),
        LocalTurista(24, "Farmacia Nur-Din", "Farmacias", "+245955924145", "Praca Praca, Bissau", "Farmacia com medicamentos essenciais. Boa reputacao na comunidade.", "8h as 21h"),
        LocalTurista(25, "Farmacias de Caracol", "Farmacias", "+245956330015", "Bissau, Guine-Bissau", "Farmacia bem avaliada com estoque variado. Aberta ate tarde.", "8h as 23h"),
        LocalTurista(26, "Farmacia Portuguesa", "Farmacias", "+245955942994", "VC68+WJ9, Avenida Amilcar Cabral, Bissau", "Farmacia tradicional com atendimento em portugues. Medicamentos importados.", "8h as 20h"),
        LocalTurista(27, "Farmacia Central", "Farmacias", "+245955115551", "V95X+6QX, Avenida dos Combatentes da Liberdade da Patria, Bissau", "Farmacia central com amplo estoque e atendimento profissional.", "8h as 22h"),
        LocalTurista(28, "Farmacia Maimuna", "Farmacias", "+245955731717", "Rua 13, Bissau", "Farmacia aberta 24 horas. Medicamentos, perfumaria e servicos de saude.", "Aberto 24 horas"),
        LocalTurista(29, "Farmacia Mocambique", "Farmacias", "+245955131313", "VC56+5Q9, Rua Mocambique X Angola, Bissau", "Farmacia com medicamentos e produtos de saude. Bem avaliada pelos clientes.", "8h as 21h"),

        // ============ EMBAIXADAS (9 REAIS) ============
        LocalTurista(30, "Gabinete de Representacao dos Estados Unidos", "Embaixadas", "+2453256382", "Edificio SITEC, 245 R. Jose Carlos Schwarz, Bissau", "Representacao diplomatica dos Estados Unidos na Guine-Bissau. Servicos consulares para cidadaos americanos.", "8h as 17h"),
        LocalTurista(31, "Embaixada da Guine-Conakry", "Embaixadas", "", "RCP2+PJW, Bissau", "Embaixada da Republica da Guine (Conakry). Servicos consulares e vistos.", "8h as 16h"),
        LocalTurista(32, "Embaixada de Angola", "Embaixadas", "", "V9F8+47R, Bissau", "Embaixada angolana. Apoio consular a cidadaos angolanos e lusofonos.", "9h as 16h"),
        LocalTurista(33, "Embaixada da Russia", "Embaixadas", "", "V97C+5WH, Bissau", "Embaixada da Federacao Russa. Servicos consulares e vistos.", "9h as 17h"),
        LocalTurista(34, "Embaixada do Brasil", "Embaixadas", "", "Embaixada do Brasil, Bissau", "Embaixada brasileira na Guine-Bissau. Servicos consulares e apoio a brasileiros.", "9h as 17h"),
        LocalTurista(35, "Embaixada da China", "Embaixadas", "+245956027372", "VC78+77M, Bissau", "Embaixada da Republica Popular da China. Servicos consulares e vistos.", "9h as 16h"),
        LocalTurista(36, "Embaixada de Cabo Verde em Bissau", "Embaixadas", "+245955650268", "VC56+VGW, Bissau", "Embaixada cabo-verdiana. Apoio consular a cidadaos cabo-verdianos.", "8h as 16h"),
        LocalTurista(37, "Embaixada da Turkiye", "Embaixadas", "+245957064646", "Rua 17 Casa no:9 Praca, Bissau", "Embaixada da Turkiye. Servicos consulares e vistos. Atendimento via WhatsApp disponivel.", "10h as 17h"),
        LocalTurista(38, "Embaixada de Portugal", "Embaixadas", "+2453203379", "VC67+R5P, Avenida Cidade de Lisboa, Bissau", "Embaixada portuguesa com centro cultural. Apoio consular a cidadaos portugueses.", "8h as 16h"),

        // ============ MINISTERIOS (8 REAIS) ============
        LocalTurista(39, "Ministerio da Mulher, Familia e Solidariedade Social", "Ministerios", "+245955595009", "VC78+77M, Bissau", "Ministerio responsavel por politicas de genero, familia e assistencia social.", "8h as 16h"),
        LocalTurista(40, "Palacio do Governo", "Ministerios", "+245955371785", "V9F8+Q67, Avenida dos Combatentes da Liberdade da Patria, Bissau", "Sede do Governo da Republica da Guine-Bissau. Edificio historico e simbolo nacional.", "Horario comercial"),
        LocalTurista(41, "Ministerio da Economia e Financas", "Ministerios", "", "VC59+CG8, Bissau", "Ministerio responsavel pela politica economica e financas publicas.", "8h as 15h"),
        LocalTurista(42, "Ministerio da Saude Publica", "Ministerios", "", "VC87+65F, Bissau", "Ministerio responsavel pela saude publica e sistema nacional de saude.", "8h as 15h"),
        LocalTurista(43, "Ministerio dos Negocios Estrangeiros", "Ministerios", "", "VC78+2HP, Bissau", "Ministerio responsavel pela politica externa, diplomacia e vistos.", "8h as 15h"),
        LocalTurista(44, "Ministerio da Funcao Publica", "Ministerios", "", "VC67+WXF, Bissau", "Ministerio da Funcao Publica, Trabalho e Modernizacao do Estado.", "8h as 16h30"),
        LocalTurista(45, "Ministerio da Justica", "Ministerios", "", "VC59+HQW, Bissau", "Ministerio responsavel pelo sistema judicial e administracao da justica.", "8h as 15h"),
        LocalTurista(46, "Ministerio das Pescas", "Ministerios", "", "VC68+CV5, Bissau", "Ministerio responsavel pela politica de pescas e recursos marinhos.", "8h as 16h"),

        // ============ ESTADIOS (7 REAIS) ============
        LocalTurista(47, "Campo Pedrada", "Futebol", "+245956040105", "R9PM+W7, Bissau", "Campo de futebol de terra. Utilizado para jogos amadores e treinos locais.", "Dias de jogo"),
        LocalTurista(48, "Campo de Futebol - Madrugada", "Futebol", "", "VCW6+8CR, Bissau", "Campo de futebol proximo a Clinica Madrugada. Uso comunitario.", "Dias de jogo"),
        LocalTurista(49, "Estadio CIFAP", "Futebol", "", "RCP2+74M, Bissau", "Estadio de futebol com estrutura para jogos e treinos. Centro de formacao de atletas.", "Dias de jogo"),
        LocalTurista(50, "Estadio 24 de Setembro", "Futebol", "", "RCV5+9M3, Av. do 3 do Agosto, Bissau", "Maior estadio da Guine-Bissau. Recebe jogos da selecao nacional e campeonatos nacionais. Capacidade para milhares de espectadores.", "Dias de jogo"),
        LocalTurista(51, "Campo de Platine", "Futebol", "+245956040434", "R9RM+FP5, Bissau", "Campo de futebol localizado em area historica. Sede do P.A.I.G.C.", "Dias de jogo"),
        LocalTurista(52, "Campo de California", "Futebol", "+245955545672", "V97P+2X4, Bissau", "Campo de futebol comunitario no bairro California. Bom para jogos amadores.", "Dias de jogo"),
        LocalTurista(53, "Estadio Lino Correia", "Futebol", "+245955762720", "VC67+4HW, Bissau", "Estadio historico de Bissau. Local para praticas esportivas e jogos de futebol. Boas condicoes para treinar atletismo.", "Dias de jogo"),

        // ============ DISCOTECAS ============
        LocalTurista(54, "Discoteca N'Kassa", "Discotecas", "+245 966 400 001", "Bairro de Santa Luzia, Bissau", "Discoteca popular com musica africana e internacional. Ambiente animado nos fins de semana.", "Sex-Sab 22h as 5h"),

        // ============ SUPERMERCADOS ============
        LocalTurista(55, "Supermercado Central", "Supermercados", "+245 966 500 001", "Av. Amilcar Cabral, Bissau", "Supermercado com produtos nacionais e importados. Aceita cartao.", "Seg-Sab 8h as 21h"),
        LocalTurista(56, "Supermercado Nossa Senhora", "Supermercados", "+245 966 500 002", "Bairro de Belem, Bissau", "Supermercado com boa variedade de produtos frescos.", "Seg-Sab 8h as 20h"),

        // ============ FEIRAS ============
        LocalTurista(57, "Mercado de Bandim", "Feiras", "", "Bairro de Bandim, Bissau", "Maior mercado da Guine-Bissau. Artesanato, tecidos, frutas, especiarias e muito mais. Negocie sempre!", "Seg-Sab 7h as 19h"),
        LocalTurista(58, "Mercado de Bissau Novo", "Feiras", "", "Bairro de Bissau Novo, Bissau", "Mercado tradicional com produtos locais e artesanato.", "Seg-Sab 7h as 19h"),

        // ============ COMBUSTIVEL ============
        LocalTurista(59, "Posto Total", "Combustivel", "+245 966 600 001", "Av. Combatentes da Liberdade, Bissau", "Posto de combustivel 24 horas. Gasolina, gasoleo e servicos de conveniencia.", "24 horas"),
        LocalTurista(60, "Posto Petromar", "Combustivel", "+245 966 600 002", "Estrada de Bissau-Bissau, Bissau", "Posto de combustivel com gasolina e gasoleo. Bom para abastecer antes de viagens.", "6h as 22h"),

        // ============ BANCOS E CAMBIO ============
        LocalTurista(61, "BCEAO - Banco Central", "Bancos e Cambio", "+245 966 700 001", "Av. Amilcar Cabral, Bissau", "Banco Central dos Estados da Africa Ocidental. Referencia para cambio oficial.", "8h as 15h"),
        LocalTurista(62, "BAO - Banco da Africa Ocidental", "Bancos e Cambio", "+245 966 700 002", "Av. Amilcar Cabral, Bissau", "Um dos maiores bancos comerciais da Guine-Bissau. Caixas multibanco e cambio.", "8h as 16h"),
        LocalTurista(63, "BIGB - Banco Internacional", "Bancos e Cambio", "+245 966 700 003", "Bairro do Centro, Bissau", "Banco Internacional da Guine-Bissau. Servicos de cambio e transferencias.", "8h as 16h"),
        LocalTurista(64, "Orabank Guine-Bissau", "Bancos e Cambio", "+245 966 700 004", "Av. Cidade de Lisboa, Bissau", "Banco comercial com servicos de cambio e transferencias internacionais.", "8h as 16h"),
        LocalTurista(65, "Ecobank Guine-Bissau", "Bancos e Cambio", "+245 966 700 005", "Bairro de Santa Luzia, Bissau", "Banco pan-africano com servicos de cambio e multicaixa.", "8h as 16h"),
        LocalTurista(66, "Casa de Cambio Central", "Bancos e Cambio", "+245 966 700 006", "Praca Che Guevara, Bissau", "Casa de cambio com melhores taxas. Aceita euros, dolares e FCFA.", "8h as 18h")
    )

    fun porCategoria(categoria: String): List<LocalTurista> {
        return lista.filter { it.categoria == categoria }
    }
}

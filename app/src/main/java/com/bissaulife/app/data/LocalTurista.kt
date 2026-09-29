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
        "Bancos e Cambio",
        "Pontos Turisticos"
    )

    val lista = listOf(

        // HOSPITAIS (17)
        LocalTurista(1, "Hospital Nacional Simao Mendes", "Hospitais", "+245955348876", "Avenida Francisco Mendes, Bissau", "Principal hospital publico da Guine-Bissau. Referencia nacional em atendimento de urgencia, cirurgias e internamento.", "Aberto 24 horas"),
        LocalTurista(2, "Hospital Militar", "Hospitais", "", "Avenida dos Combatentes da Liberdade da Patria, Bissau", "Hospital militar com atendimento de urgencia. Aceita civis em casos de emergencia.", "Aberto 24 horas"),
        LocalTurista(3, "Hospital 3 de Agosto", "Hospitais", "+245957482277", "Bissau, Guine-Bissau", "Hospital publico com atendimento geral e urgencia.", "Horario comercial"),
        LocalTurista(4, "Hospital Pediatrico de Bor", "Hospitais", "+245966528890", "Bissau, Guine-Bissau", "Hospital pediatrico especializado no atendimento a criancas.", "Aberto 24 horas"),
        LocalTurista(5, "Clinica Madrugada", "Hospitais", "+245955120537", "Bissau, Guine-Bissau", "Clinica com atendimento 24 horas. Uma das mais bem avaliadas da cidade.", "Aberto 24 horas"),
        LocalTurista(6, "Clinica Sao Jose de Bor", "Hospitais", "+245955561538", "Bissau, Guine-Bissau", "Clinica e hospital particular com atendimento geral.", "Horario comercial"),
        LocalTurista(7, "Clinica Geral Dra Vicky Cabral", "Hospitais", "+245957185726", "Estrada de Bor, perto da rotunda de Clele, Bissau", "Clinica geral com servico 24 horas, 7 dias por semana.", "Aberto 24 horas"),
        LocalTurista(8, "Raoul Follereau", "Hospitais", "+245957181823", "Bissau, Guine-Bissau", "Centro de saude com atendimento geral.", "Horario comercial"),
        LocalTurista(9, "CEMI-E Clinica Medica e Dentaria", "Hospitais", "+245957203440", "1160, Rua Djassi, Bissau", "Clinica medica e dentaria especializada.", "9h as 18h"),
        LocalTurista(10, "Renato Grande Bissau", "Hospitais", "", "Bissau, Guine-Bissau", "Clinica especializada com atendimento medico diversificado.", "Horario comercial"),
        LocalTurista(11, "Centro de Saude de Bandim", "Hospitais", "+245957626075", "Bairro de Bandim, Bissau", "Posto de saude comunitario no coracao de Bandim.", "Horario comercial"),
        LocalTurista(12, "Centro de Saude do Bairro Militar", "Hospitais", "+245955731523", "Bairro Militar, em frente ao Mercado, Bissau", "Centro de saude tipo B com atendimento 24 horas.", "Aberto 24 horas"),
        LocalTurista(13, "Centro de Saude de Bairro de Ajuda", "Hospitais", "", "Bairro de Ajuda, Bissau", "Posto de saude comunitario com atendimento 24 horas.", "Aberto 24 horas"),
        LocalTurista(14, "Centro de Saude de Antula", "Hospitais", "", "Bairro de Antula, Bissau", "Posto de saude comunitario com atendimento primario.", "Horario comercial"),
        LocalTurista(15, "Centro Materno Infantil", "Hospitais", "", "Bissau, Guine-Bissau", "Centro medico especializado em saude materna e infantil.", "8h as 17h"),
        LocalTurista(16, "Centro de Saude Mental Osvaldo Maximo Vieira", "Hospitais", "", "Faculdade de Medicina Raul Diaz Arguelles, Bissau", "Servico especializado em saude mental.", "8h as 15h"),
        LocalTurista(17, "Centro de Reducao Motora Dr. Ernesto Lopes", "Hospitais", "+245966096557", "Bissau, Guine-Bissau", "Centro especializado em fisioterapia e reabilitacao motora.", "Horario comercial"),

        // FARMACIAS (12)
        LocalTurista(18, "Alliance Pharma Sarl", "Farmacias", "", "Bissau, Guine-Bissau", "Distribuidora de produtos farmaceuticos.", "8h30 as 18h"),
        LocalTurista(19, "Farmacia Rama", "Farmacias", "", "VC69+MF5, Avenida Pansau Na Isna, Bissau", "Farmacia tradicional com medicamentos variados.", "8h as 20h"),
        LocalTurista(20, "Farmacia Dahaba Group", "Farmacias", "+245955824603", "Escola de Djaal, Entrada de, Bissau", "Farmacia moderna com amplo estoque.", "8h as 23h"),
        LocalTurista(21, "Farmacia Amilcar Cabral", "Farmacias", "", "VC53+923, Av. Combatente da Liberdade da Patria, Bissau", "Farmacia bem localizada com boa variedade.", "8h as 21h"),
        LocalTurista(22, "Farmacia Paris.GB", "Farmacias", "+245955131332", "V95Q+934, Bairro de Ajuda, Bissau", "Farmacia completa com medicamentos e produtos de saude.", "8h as 23h"),
        LocalTurista(23, "Farmacia Municipal", "Farmacias", "+245955850085", "VC69+V79, Avenida Pansau Na Isna, Bissau", "Farmacia municipal com bom atendimento.", "8h as 20h"),
        LocalTurista(24, "Farmacia Nur-Din", "Farmacias", "+245955924145", "Praca Praca, Bissau", "Farmacia com medicamentos essenciais.", "8h as 21h"),
        LocalTurista(25, "Farmacias de Caracol", "Farmacias", "+245956330015", "Bissau, Guine-Bissau", "Farmacia bem avaliada com estoque variado.", "8h as 23h"),
        LocalTurista(26, "Farmacia Portuguesa", "Farmacias", "+245955942994", "VC68+WJ9, Avenida Amilcar Cabral, Bissau", "Farmacia tradicional com atendimento em portugues.", "8h as 20h"),
        LocalTurista(27, "Farmacia Central", "Farmacias", "+245955115551", "V95X+6QX, Avenida dos Combatentes da Liberdade da Patria, Bissau", "Farmacia central com amplo estoque.", "8h as 22h"),
        LocalTurista(28, "Farmacia Maimuna", "Farmacias", "+245955731717", "Rua 13, Bissau", "Farmacia aberta 24 horas. Medicamentos, perfumaria e servicos de saude.", "Aberto 24 horas"),
        LocalTurista(29, "Farmacia Mocambique", "Farmacias", "+245955131313", "VC56+5Q9, Rua Mocambique X Angola, Bissau", "Farmacia com medicamentos e produtos de saude.", "8h as 21h"),

        // EMBAIXADAS (9)
        LocalTurista(30, "Gabinete de Representacao dos Estados Unidos", "Embaixadas", "+2453256382", "Edificio SITEC, 245 R. Jose Carlos Schwarz, Bissau", "Representacao diplomatica dos Estados Unidos.", "8h as 17h"),
        LocalTurista(31, "Embaixada da Guine-Conakry", "Embaixadas", "", "RCP2+PJW, Bissau", "Embaixada da Republica da Guine (Conakry).", "8h as 16h"),
        LocalTurista(32, "Embaixada de Angola", "Embaixadas", "", "V9F8+47R, Bissau", "Embaixada angolana. Apoio consular.", "9h as 16h"),
        LocalTurista(33, "Embaixada da Russia", "Embaixadas", "", "V97C+5WH, Bissau", "Embaixada da Federacao Russa.", "9h as 17h"),
        LocalTurista(34, "Embaixada do Brasil", "Embaixadas", "", "Embaixada do Brasil, Bissau", "Embaixada brasileira. Servicos consulares.", "9h as 17h"),
        LocalTurista(35, "Embaixada da China", "Embaixadas", "+245956027372", "VC78+77M, Bissau", "Embaixada da Republica Popular da China.", "9h as 16h"),
        LocalTurista(36, "Embaixada de Cabo Verde em Bissau", "Embaixadas", "+245955650268", "VC56+VGW, Bissau", "Embaixada cabo-verdiana.", "8h as 16h"),
        LocalTurista(37, "Embaixada da Turkiye", "Embaixadas", "+245957064646", "Rua 17 Casa no:9 Praca, Bissau", "Embaixada da Turkiye. Atendimento via WhatsApp disponivel.", "10h as 17h"),
        LocalTurista(38, "Embaixada de Portugal", "Embaixadas", "+2453203379", "VC67+R5P, Avenida Cidade de Lisboa, Bissau", "Embaixada portuguesa com centro cultural.", "8h as 16h"),

        // MINISTERIOS (8)
        LocalTurista(39, "Ministerio da Mulher, Familia e Solidariedade Social", "Ministerios", "+245955595009", "VC78+77M, Bissau", "Ministerio responsavel por politicas de genero, familia e assistencia social.", "8h as 16h"),
        LocalTurista(40, "Palacio do Governo", "Ministerios", "+245955371785", "V9F8+Q67, Avenida dos Combatentes da Liberdade da Patria, Bissau", "Sede do Governo da Republica da Guine-Bissau.", "Horario comercial"),
        LocalTurista(41, "Ministerio da Economia e Financas", "Ministerios", "", "VC59+CG8, Bissau", "Ministerio responsavel pela politica economica e financas publicas.", "8h as 15h"),
        LocalTurista(42, "Ministerio da Saude Publica", "Ministerios", "", "VC87+65F, Bissau", "Ministerio responsavel pela saude publica.", "8h as 15h"),
        LocalTurista(43, "Ministerio dos Negocios Estrangeiros", "Ministerios", "", "VC78+2HP, Bissau", "Ministerio responsavel pela politica externa e vistos.", "8h as 15h"),
        LocalTurista(44, "Ministerio da Funcao Publica", "Ministerios", "", "VC67+WXF, Bissau", "Ministerio da Funcao Publica, Trabalho e Modernizacao do Estado.", "8h as 16h30"),
        LocalTurista(45, "Ministerio da Justica", "Ministerios", "", "VC59+HQW, Bissau", "Ministerio responsavel pelo sistema judicial.", "8h as 15h"),
        LocalTurista(46, "Ministerio das Pescas", "Ministerios", "", "VC68+CV5, Bissau", "Ministerio responsavel pela politica de pescas.", "8h as 16h"),

        // ESTADIOS (7)
        LocalTurista(47, "Campo Pedrada", "Futebol", "+245956040105", "R9PM+W7, Bissau", "Campo de futebol de terra. Utilizado para jogos amadores e treinos locais.", "Dias de jogo"),
        LocalTurista(48, "Campo de Futebol - Madrugada", "Futebol", "", "VCW6+8CR, Bissau", "Campo de futebol proximo a Clinica Madrugada.", "Dias de jogo"),
        LocalTurista(49, "Estadio CIFAP", "Futebol", "", "RCP2+74M, Bissau", "Estadio de futebol com estrutura para jogos e treinos.", "Dias de jogo"),
        LocalTurista(50, "Estadio 24 de Setembro", "Futebol", "", "RCV5+9M3, Av. do 3 do Agosto, Bissau", "Maior estadio da Guine-Bissau. Recebe jogos da selecao nacional.", "Dias de jogo"),
        LocalTurista(51, "Campo de Platine", "Futebol", "+245956040434", "R9RM+FP5, Bissau", "Campo de futebol em area historica. Sede do P.A.I.G.C.", "Dias de jogo"),
        LocalTurista(52, "Campo de California", "Futebol", "+245955545672", "V97P+2X4, Bissau", "Campo de futebol comunitario no bairro California.", "Dias de jogo"),
        LocalTurista(53, "Estadio Lino Correia", "Futebol", "+245955762720", "VC67+4HW, Bissau", "Estadio historico de Bissau. Local para praticas esportivas.", "Dias de jogo"),

        // DISCOTECAS (6)
        LocalTurista(54, "Antika Bissau", "Discotecas", "+245969111111", "Av. Amilcar Cabral Bissau GW, 1000, Bissau", "Lounge bar com nightclub interno. Ambiente sofisticado com musica ao vivo e DJs.", "Fecha as 01:00"),
        LocalTurista(55, "Discoteca Tropicana", "Discotecas", "+245956040434", "VC69+GX6, Bissau", "Casa noturna tradicional de Bissau. Musica africana e internacional.", "Noite"),
        LocalTurista(56, "Discoteca TABANKA", "Discotecas", "", "VC67+CX2, Bissau", "Casa noturna com pista de danca e musica ao vivo.", "Noite"),
        LocalTurista(57, "Pub Balafon", "Discotecas", "", "VC58+WJ9, Baiana, Bissau", "Casa noturna com ambiente animado. Bebidas e petiscos.", "Abre as 19:00"),
        LocalTurista(58, "Vereda Tropical", "Discotecas", "+2455705078", "VCC7+34F, Avenida Pansau Na Isna, Bissau", "Casa noturna com ambiente descontraido. Bom para cerveja e conversa.", "Fecha as 23:00"),
        LocalTurista(59, "Skybar Lounge Bissau Royal Hotel", "Discotecas", "+245955888888", "Bissau, Guine-Bissau", "Lounge bar no Bissau Royal Hotel. Vista panoramica e cocktails.", "Abre as 17:00"),

        // SUPERMERCADOS (8)
        LocalTurista(60, "Supermercado Darling Central (Praca)", "Supermercados", "+245957903939", "VC58+GX6, Bissau", "Supermercado no coracao da cidade. Produtos nacionais e importados.", "8h as 19h"),
        LocalTurista(61, "Supermercado Ghada", "Supermercados", "+245956777477", "Bissau Baiana, Bissau", "Supermercado com produtos variados no coracao da capital.", "Fecha 13h, reabre 15h"),
        LocalTurista(62, "Bodem", "Supermercados", "+245955929912", "VC58+7VV, Vitorino da Costa, Bissau", "Supermercado com boa variedade de produtos domesticos e alimentares.", "8h as 18h30"),
        LocalTurista(63, "Mini Mercado Alvalade", "Supermercados", "", "VC68+F5W, Bissau", "Mini mercado com boa escolha de vinhos e queijos portugueses.", "Fecha 13h, reabre 15h"),
        LocalTurista(64, "Spar Bissau", "Supermercados", "+245956602158", "Rua Osvaldo Vieira, Bissau", "Supermercado da rede internacional SPAR. Padaria e congelados.", "Fecha 14h, reabre 16h"),
        LocalTurista(65, "Supermercado Ikuma", "Supermercados", "+245956525252", "V9G8+C3X, Bissau", "Supermercado moderno com produtos frescos e importados.", "8h as 18h30"),
        LocalTurista(66, "Good Market", "Supermercados", "+245955930455", "VC55+5CC, Avenida do Brasil, Bissau", "Supermercado com produtos alimentares variados.", "8h as 19h30"),
        LocalTurista(67, "Ponto Fresco", "Supermercados", "+245955400160", "Loja 1 - Rua 7, n 22. Loja 2 - Rua Eduardo Mondelane, n 30, Bissau", "O melhor lugar para se comprar em Bissau. Duas lojas disponiveis.", "Fecha 13h30, reabre 15h30"),

        // FEIRAS (5)
        LocalTurista(68, "Mercado de Bande", "Feiras", "+245955427116", "VC54+3PX, Bissau", "Mercado tradicional com produtos variados. Roupas, calcados e utensilios.", "7h as 18h30"),
        LocalTurista(69, "Feira de Antula", "Feiras", "+245955546786", "Bissau, Guine-Bissau", "Feira livre com produtos frescos, roupas e utensilios domesticos.", "7h as 17h30"),
        LocalTurista(70, "Mercado de Caracol", "Feiras", "", "VC32+98X, Bissau", "Mercado tradicional no bairro de Caracol. Produtos locais e artesanato.", "7h as 19h"),
        LocalTurista(71, "Feira de Agua", "Feiras", "+245956281071", "VCJ5+F58, Bissau", "Centro comercial com produtos variados.", "8h as 18h"),
        LocalTurista(72, "Mercado Central", "Feiras", "+245955172483", "VC58+4GM, Bissau", "Mercado Central de Bissau. Frutas, legumes e produtos frescos.", "7h as 19h"),

        // COMBUSTIVEL (6)
        LocalTurista(73, "SPGC BOLOA", "Combustivel", "", "VC83+HV2, Bolola, Guine-Bissau", "Posto de combustivel em Bolola. Gasolina e gasoleo.", "Horario comercial"),
        LocalTurista(74, "Petrodis - Guine Bissau", "Combustivel", "", "VC67+VC2, Bissau", "Posto de combustivel com servicos de conveniencia.", "Fecha 13h, reabre 15h"),
        LocalTurista(75, "Posto de Combustivel GALP", "Combustivel", "", "VC59+7V4, Bissau", "Posto GALP com gasolina e gasoleo.", "6h as 22h"),
        LocalTurista(76, "Posto de Combustivel PETROMAR-HAFIA", "Combustivel", "", "V9H6+HVV, Bissau", "Posto PETROMAR com gasolina, gasoleo e lubrificantes.", "Aberto 24 horas"),
        LocalTurista(77, "Estacao de Servico Jolif Antula", "Combustivel", "+245955388837", "Avenida dos Combatentes da Liberdade da Patria, Bissau", "Posto Jolif com loja, lavagem e manutencao.", "Aberto 24 horas"),
        LocalTurista(78, "SCD Sarl-Bissau", "Combustivel", "", "Unnamed Road, Bissau", "Posto de combustivel SCD-LDA.", "Horario comercial"),

        // BANCOS E CAMBIO (9)
        LocalTurista(79, "Banco da Africa Ocidental - BAO", "Bancos e Cambio", "+2453203418", "18B Rua 19 Setembro, Bissau", "Um dos maiores bancos comerciais da Guine-Bissau.", "8h as 16h"),
        LocalTurista(80, "Banque Atlantique Guinee Bissau", "Bancos e Cambio", "+245956000108", "VC69+FGC, Bissau", "Banco Atlantique. Servicos bancarios e financeiros.", "8h as 16h"),
        LocalTurista(81, "Orabank", "Bancos e Cambio", "+245966672907", "VC69+GHF, Avenida Pansau Na Isna, Bissau", "Banco comercial com servicos de cambio e transferencias.", "8h as 16h"),
        LocalTurista(82, "Coris Bank", "Bancos e Cambio", "", "VC78+23P, Avenida Francisco Mendes, Bissau", "Banco Coris. Servicos bancarios completos.", "Horario comercial"),
        LocalTurista(83, "Banco da Uniao - S.A.", "Bancos e Cambio", "+245955152037", "VC58+QPF, Avenida Domingos Ramos, Bissau", "Banco da Uniao. Servicos bancarios e cambio.", "8h as 16h30"),
        LocalTurista(84, "ECOBANK Guine-Bissau", "Bancos e Cambio", "+245965296800", "VC68+CX8, Avenida Amilcar Cabral, Bissau", "Banco pan-africano com servicos de cambio e multicaixa.", "8h as 18h"),
        LocalTurista(85, "BCAO - Banco Central da Africa Ocidental", "Bancos e Cambio", "", "V98C+5GP, Avenida dos Combatentes da Liberdade da Patria, Bissau", "Banco Central dos Estados da Africa Ocidental.", "8h as 18h"),
        LocalTurista(86, "Cambio Guine", "Bancos e Cambio", "+2455803638", "VC58+HVW, Avenida Domingos Ramos, Bissau", "Agencia de cambio com boas taxas. Aceita euros, dolares e FCFA.", "8h as 18h"),
        LocalTurista(87, "Agencia de Cambio Groupbaol", "Bancos e Cambio", "+245955901008", "VC59+74G, Avenida Domingos Ramos, Bissau", "Casa de cambio nacional. Melhores taxas da cidade.", "8h as 18h"),

        // PONTOS TURISTICOS (9)
        LocalTurista(88, "Fort Sao Jose da Amura", "Pontos Turisticos", "", "VC6C+5FJ, Bissau", "Fortaleza historica de Bissau. Construcao colonial do seculo XVII. Ponto turistico com vista para o mar.", "Aberto 24 horas"),
        LocalTurista(89, "Tres Polons", "Pontos Turisticos", "", "VCM8+WJ8, Unnamed Road, Bissau", "Parque com tres torres de comunicacao. Ponto de referencia na cidade.", "Aberto 24 horas"),
        LocalTurista(90, "IBAP - Instituto da Biodiversidade", "Pontos Turisticos", "+245957656701", "VCF4+53V, Bissau", "Instituto de pesquisa em biodiversidade e areas protegidas. Visitas guiadas disponiveis. Melhor epoca: novembro a maio.", "9h as 17h"),
        LocalTurista(91, "Parque Lagoa N'Batonha", "Pontos Turisticos", "", "VC48+PC8, Avenida do 3 do Agosto, Bissau", "Parque natural com lagoa, vegetacao e areas de caminhada. Ideal para relaxar e observar aves.", "Aberto 24 horas"),
        LocalTurista(92, "Mao de Timba", "Pontos Turisticos", "", "VC49+VP3, Avenida do 3 de Agosto, Bissau", "Monumento historico e simbolo nacional. Marco da resistencia guineense.", "Aberto 24 horas"),
        LocalTurista(93, "Galeria Art's Santos", "Pontos Turisticos", "+245956971590", "VCH2+XP, Bissau", "Galeria de arte com obras de artistas guineenses. Pinturas e esculturas.", "9h as 17h"),
        LocalTurista(94, "Praca dos Herois Nacionais", "Pontos Turisticos", "+245956304608", "VC78+77F, Bissau", "Praca memorial com monumento aos herois nacionais. Perto do palacio presidencial. Bom para caminhadas.", "Aberto 24 horas"),
        LocalTurista(95, "Rotunda de Alto Bandim", "Pontos Turisticos", "", "RCQ3+QWW, Avenida 3 de Agosto, Bissau", "Atracao turistica com rotunda principal. Ponto de encontro da cidade.", "Aberto 24 horas"),
        LocalTurista(96, "Praca Ernesto Guevara 'Che'", "Pontos Turisticos", "", "VC68+2JR, Che, Bissau", "Praca historica com monumento a Ernesto Che Guevara. Simbolo da solidariedade internacional.", "Aberto 24 horas")
    )

    fun porCategoria(categoria: String): List<LocalTurista> {
        return lista.filter { it.categoria == categoria }
    }
}

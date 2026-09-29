package com.bissaulife.app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.theme.BissauGreen

private const val WHATSAPP_COMERCIAL = "245955572393"

data class Plano(
    val id: String,
    val nome: String,
    val precoSemestral: String,
    val precoAnual: String,
    val corInicio: Color,
    val corFim: Color,
    val emoji: String,
    val recomendado: Boolean,
    val beneficios: List<BeneficioItem>
)

data class BeneficioItem(val texto: String, val incluido: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanosScreen(onVoltar: () -> Unit) {
    val context = LocalContext.current
    var periodoSelecionado by remember { mutableStateOf("semestral") }

    val planos = listOf(
        Plano(
            id = "basico",
            nome = "Básico",
            precoSemestral = "Grátis",
            precoAnual = "Grátis",
            corInicio = Color(0xFF607D8B),
            corFim = Color(0xFF455A64),
            emoji = "🟢",
            recomendado = false,
            beneficios = listOf(
                BeneficioItem("Cadastro no app", true),
                BeneficioItem("1 foto do negocio", true),
                BeneficioItem("Botao Ligar + Como chegar", true),
                BeneficioItem("Aparece na categoria", true),
                BeneficioItem("Selo DESTAQUE", false),
                BeneficioItem("Topo da busca", false),
                BeneficioItem("Banner na Home", false),
                BeneficioItem("Suporte prioritario", false)
            )
        ),
        Plano(
            id = "destaque",
            nome = "Destaque",
            precoSemestral = "8.000 FCFA",
            precoAnual = "10.000 FCFA",
            corInicio = Color(0xFFFFC107),
            corFim = Color(0xFFFF9800),
            emoji = "⭐",
            recomendado = true,
            beneficios = listOf(
                BeneficioItem("Cadastro no app", true),
                BeneficioItem("Ate 5 fotos", true),
                BeneficioItem("Botao Ligar + Como chegar", true),
                BeneficioItem("Aparece na categoria", true),
                BeneficioItem("Selo DESTAQUE", true),
                BeneficioItem("Topo da busca", true),
                BeneficioItem("Banner na Home", false),
                BeneficioItem("Suporte prioritario", false)
            )
        ),
        Plano(
            id = "premium",
            nome = "Premium",
            precoSemestral = "25.000 FCFA",
            precoAnual = "30.000 FCFA",
            corInicio = Color(0xFF9C27B0),
            corFim = Color(0xFF6A1B9A),
            emoji = "💎",
            recomendado = false,
            beneficios = listOf(
                BeneficioItem("Cadastro no app", true),
                BeneficioItem("Fotos ilimitadas", true),
                BeneficioItem("Botao Ligar + Como chegar", true),
                BeneficioItem("Aparece na categoria", true),
                BeneficioItem("Selo DESTAQUE", true),
                BeneficioItem("Topo da busca", true),
                BeneficioItem("Banner na Home", true),
                BeneficioItem("Suporte prioritario", true)
            )
        )
    )

    fun contratar(plano: Plano) {
        val valor = if (periodoSelecionado == "semestral") plano.precoSemestral else plano.precoAnual
        val periodo = if (periodoSelecionado == "semestral") "Semestral" else "Anual"
        val mensagem = "Ola! Quero contratar o Plano ${plano.nome} $periodo ($valor) no BissauLife."
        val url = "https://wa.me/$WHATSAPP_COMERCIAL?text=${Uri.encode(mensagem)}"
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Planos", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F5F5))
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // CABECALHO
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFFFFC107), Color(0xFFFF9800))
                        )
                    )
                    .padding(24.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Star, null, tint = Color.White, modifier = Modifier.size(36.dp))
                        Spacer(Modifier.width(10.dp))
                        Text(
                            "Escolha seu plano",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Escolha o melhor plano para seu negocio. Cancele quando quiser.",
                        color = Color.White.copy(alpha = 0.95f),
                        fontSize = 13.sp,
                        lineHeight = 19.sp
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // SELETOR DE PERIODO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                listOf("semestral", "anual").forEach { periodo ->
                    val selecionado = periodo == periodoSelecionado
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (selecionado) BissauGreen else Color.Transparent)
                            .clickable { periodoSelecionado = periodo }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            if (periodo == "semestral") "Semestral" else "Anual",
                            color = if (selecionado) Color.White else Color.Black,
                            fontSize = 14.sp,
                            fontWeight = if (selecionado) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // LISTA DE PLANOS
            planos.forEach { plano ->
                CardPlano(
                    plano = plano,
                    periodo = periodoSelecionado,
                    onClick = { contratar(plano) }
                )
                Spacer(Modifier.height(16.dp))
            }

            // FORMAS DE PAGAMENTO
            Text(
                "Formas de pagamento aceitas",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp, bottom = 10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFF6600))
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Orange Money", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFCC00))
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("MTN Money", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }

            Spacer(Modifier.height(12.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text("💡", fontSize = 20.sp)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        "O pagamento e combinado diretamente pelo WhatsApp comercial. Voce recebera as instrucoes apos contratar.",
                        fontSize = 12.sp,
                        color = Color(0xFF795548),
                        lineHeight = 17.sp
                    )
                }
            }

            Spacer(Modifier.height(30.dp))
        }
    }
}

@Composable
fun CardPlano(
    plano: Plano,
    periodo: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            // Cabecalho do plano
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(plano.corInicio, plano.corFim)
                        )
                    )
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(plano.emoji, fontSize = 40.sp)
                    Spacer(Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "Plano ${plano.nome}",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (plano.recomendado) {
                                Spacer(Modifier.width(8.dp))
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = Color.White
                                ) {
                                    Text(
                                        "POPULAR",
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                        fontSize = 9.sp,
                                        color = plano.corFim,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        Spacer(Modifier.height(6.dp))
                        val preco = if (periodo == "semestral") plano.precoSemestral else plano.precoAnual
                        Text(
                            preco,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            if (periodo == "semestral") "a cada 6 meses" else "a cada 12 meses",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // Lista de beneficios
            Column(modifier = Modifier.padding(16.dp)) {
                plano.beneficios.forEach { beneficio ->
                    Row(
                        modifier = Modifier.padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(
                                    if (beneficio.incluido) BissauGreen.copy(alpha = 0.15f)
                                    else Color(0xFFEEEEEE)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                if (beneficio.incluido) Icons.Filled.Check else Icons.Filled.Close,
                                contentDescription = null,
                                tint = if (beneficio.incluido) BissauGreen else Color(0xFF999999),
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        Text(
                            beneficio.texto,
                            fontSize = 13.sp,
                            color = if (beneficio.incluido) Color(0xFF333333) else Color(0xFF999999)
                        )
                    }
                }

                Spacer(Modifier.height(14.dp))

                // Botao
                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (plano.id == "basico") Color(0xFF607D8B) else BissauGreen
                    ),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text(
                        if (plano.id == "basico") "Comecar gratis" else "Contratar via WhatsApp",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

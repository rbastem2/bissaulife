package com.bissaulife.app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.theme.BissauGreen

private const val WHATSAPP_COMERCIAL = "245955572393"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanosScreen(onVoltar: () -> Unit) {
    val context = LocalContext.current
    var planoSelecionado by remember { mutableStateOf("semestral") }

    fun contratarPlano(plano: String, valor: String) {
        val mensagem = "Ola! Quero contratar o Plano Destaque $plano ($valor) no BissauLife."
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
                    Text("Torne-se Destaque", fontWeight = FontWeight.Bold, fontSize = 19.sp)
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
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            "Apareca primeiro",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Seu negocio em destaque na Home e no topo da busca. Mais visibilidade, mais clientes.",
                        color = Color.White.copy(alpha = 0.95f),
                        fontSize = 13.sp,
                        lineHeight = 19.sp
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "Beneficios",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(Modifier.height(10.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Beneficio("Selo DESTAQUE no seu card")
                    Beneficio("Aparece no topo da busca")
                    Beneficio("Banner na Home do app")
                    Beneficio("Botao Ligar + Como chegar")
                    Beneficio("Estatisticas de visualizacao")
                    Beneficio("Suporte prioritario via WhatsApp")
                }
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "Escolha o plano",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(Modifier.height(10.dp))

            // PLANO SEMESTRAL
            CardPlano(
                titulo = "Semestral",
                valor = "8.000 FCFA",
                periodo = "a cada 6 meses",
                recomendado = true,
                selecionado = planoSelecionado == "semestral",
                onClick = { planoSelecionado = "semestral" }
            )

            Spacer(Modifier.height(12.dp))

            // PLANO ANUAL
            CardPlano(
                titulo = "Anual",
                valor = "10.000 FCFA",
                periodo = "a cada 12 meses",
                recomendado = false,
                selecionado = planoSelecionado == "anual",
                onClick = { planoSelecionado = "anual" }
            )

            Spacer(Modifier.height(24.dp))

            Text(
                "Formas de pagamento",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(Modifier.height(10.dp))

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
                    Text(
                        "Orange Money",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFCC00))
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "MTN Money",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Text(
                "O pagamento e combinado diretamente pelo WhatsApp comercial. Voce recebera as instrucoes apos contratar.",
                fontSize = 12.sp,
                color = Color.Gray,
                lineHeight = 17.sp
            )

            Spacer(Modifier.height(24.dp))

            // BOTAO CONTRATAR
            Button(
                onClick = {
                    val valor = if (planoSelecionado == "semestral") "8.000 FCFA" else "10.000 FCFA"
                    val nome = if (planoSelecionado == "semestral") "Semestral" else "Anual"
                    contratarPlano(nome, valor)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    "Contratar via WhatsApp",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(8.dp))
            Text(
                "Ao contratar, voce sera redirecionado para nosso WhatsApp comercial.",
                fontSize = 11.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun Beneficio(texto: String) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(50))
                .background(BissauGreen.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.Check,
                contentDescription = null,
                tint = BissauGreen,
                modifier = Modifier.size(14.dp)
            )
        }
        Spacer(Modifier.width(10.dp))
        Text(texto, fontSize = 13.sp, color = Color(0xFF333333))
    }
}

@Composable
fun CardPlano(
    titulo: String,
    valor: String,
    periodo: String,
    recomendado: Boolean,
    selecionado: Boolean,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selecionado) Color(0xFFF0FBF5) else Color.White
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = if (selecionado) 2.dp else 1.dp,
            color = if (selecionado) BissauGreen else Color(0xFFE0E0E0)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selecionado,
                    onClick = onClick,
                    colors = RadioButtonDefaults.colors(selectedColor = BissauGreen)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.weight(1f))
                if (recomendado) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFFFC107)
                    ) {
                        Text(
                            "MAIS POPULAR",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                valor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = BissauGreen
            )
            Text(periodo, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

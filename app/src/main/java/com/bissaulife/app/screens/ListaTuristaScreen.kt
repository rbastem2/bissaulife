package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.data.LocalTurista
import com.bissaulife.app.data.LocaisTurista
import com.bissaulife.app.theme.BissauGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTuristaScreen(
    categoria: String,
    onVoltar: () -> Unit,
    onVerDetalhes: (LocalTurista) -> Unit
) {
    val locais = LocaisTurista.porCategoria(categoria)
    val iconeCategoria = iconePara(categoria)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "$iconeCategoria $categoria",
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF003366),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F5F5))
        ) {
            if (locais.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("📭", fontSize = 60.sp)
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "Nenhum local cadastrado ainda",
                            fontSize = 15.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "Estamos adicionando locais a cada dia!",
                            fontSize = 12.sp,
                            color = Color.LightGray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                Text(
                    "${locais.size} ${if (locais.size == 1) "local encontrado" else "locais encontrados"}",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(locais) { local ->
                        CardLocalTurista(
                            local = local,
                            icone = iconeCategoria,
                            onClick = { onVerDetalhes(local) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardLocalTurista(local: LocalTurista, icone: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(BissauGreen.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Text(icone, fontSize = 26.sp)
            }

            Spacer(Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    local.nome,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "📍 ${local.endereco}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
                if (local.telefone.isNotBlank()) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        "📞 ${local.telefone}",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        maxLines = 1
                    )
                }
            }

            Icon(
                Icons.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

fun iconePara(categoria: String): String {
    return when (categoria) {
        "Hospitais" -> "🏥"
        "Embaixadas" -> "🌍"
        "Ministerios" -> "🏛️"
        "Futebol" -> "⚽"
        "Farmacias" -> "💊"
        "Discotecas" -> "🎉"
        "Supermercados" -> "🛒"
        "Feiras" -> "🛍️"
        "Combustivel" -> "⛽"
        else -> "📍"
    }
}

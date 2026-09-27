package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bissaulife.app.data.Restaurante
import com.bissaulife.app.theme.BissauGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheScreen(
    restaurante: Restaurante,
    onVoltar: () -> Unit
) {
    var favorito by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            // Foto de topo com gradiente
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                AsyncImage(
                    model = restaurante.imagemUrl,
                    contentDescription = restaurante.nome,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.4f),
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.3f)
                                )
                            )
                        )
                )
            }

            // Conteúdo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    restaurante.nome,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "${restaurante.nota}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFFFF9800)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "(${restaurante.avaliacoes} avaliações)",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        restaurante.categoria,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        "📍 ${restaurante.distancia}",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }

                Spacer(Modifier.height(20.dp))

                // Card de preço
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FBF5)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Preço médio",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Text(
                                "A partir de ${restaurante.preco}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = BissauGreen
                            )
                        }
                        Text("💰", fontSize = 32.sp)
                    }
                }

                Spacer(Modifier.height(20.dp))

                Text(
                    "Sobre",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    restaurante.descricao,
                    fontSize = 14.sp,
                    color = Color(0xFF444444),
                    lineHeight = 20.sp
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    "Horário",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Segunda a Sábado: 11h às 23h",
                    fontSize = 14.sp,
                    color = Color(0xFF444444)
                )
                Text(
                    "Domingo: 12h às 22h",
                    fontSize = 14.sp,
                    color = Color(0xFF444444)
                )

                Spacer(Modifier.height(100.dp))
            }
        }

        // Botão voltar flutuante (topo esquerdo)
        IconButton(
            onClick = onVoltar,
            modifier = Modifier
                .padding(16.dp)
                .size(44.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.9f))
                .align(Alignment.TopStart)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.Black
            )
        }

        // Botão favorito flutuante (topo direito)
        IconButton(
            onClick = { favorito = !favorito },
            modifier = Modifier
                .padding(16.dp)
                .size(44.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.9f))
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = if (favorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Favorito",
                tint = if (favorito) Color(0xFFE91E63) else Color.Black
            )
        }

        // Botão principal (fundo)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    "Reservar agora",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

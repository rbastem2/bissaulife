package com.bissaulife.app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current

    fun abrirWhatsApp() {
        val numero = restaurante.whatsapp.replace("+", "").replace(" ", "").replace("-", "")
        val mensagem = "Olá! Vim pelo BissauLife e gostaria de saber mais sobre ${restaurante.nome}."
        val url = "https://wa.me/$numero?text=${Uri.encode(mensagem)}"
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$numero"))
            )
        }
    }

    fun abrirMaps() {
        val endereco = restaurante.endereco
        try {
            // Tenta abrir direto no app do Google Maps
            val uri = Uri.parse("geo:0,0?q=${Uri.encode(endereco)}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            context.startActivity(intent)
        } catch (e: Exception) {
            try {
                // Fallback 1: qualquer app de mapa
                val uri = Uri.parse("geo:0,0?q=${Uri.encode(endereco)}")
                context.startActivity(Intent(Intent.ACTION_VIEW, uri))
            } catch (e2: Exception) {
                // Fallback 2: navegador
                val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(endereco)}")
                context.startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
    }

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

                Spacer(Modifier.height(20.dp))

                Text(
                    "Endereço",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    restaurante.endereco,
                    fontSize = 14.sp,
                    color = Color(0xFF444444)
                )

                Spacer(Modifier.height(20.dp))

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

                Spacer(Modifier.height(110.dp))
            }
        }

        // Botão voltar flutuante
        IconButton(
            onClick = onVoltar,
            modifier = Modifier
                .padding(16.dp)
                .size(44.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.9f))
                .align(Alignment.TopStart)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
        }

        // Botão favorito flutuante
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

        // Barra inferior com 2 botões
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { abrirWhatsApp() },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Icon(Icons.Filled.Chat, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("WhatsApp", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                }

                Button(
                    onClick = { abrirMaps() },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Icon(Icons.Filled.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Como chegar", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

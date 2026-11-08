package com.bissaulife.app.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.bissaulife.app.data.FavoritosViewModel
import com.bissaulife.app.data.Restaurante
import com.bissaulife.app.theme.BissauGreen
import com.bissaulife.app.utils.isOnline

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheScreen(
    restaurante: Restaurante,
    viewModel: FavoritosViewModel,
    onVoltar: () -> Unit
) {
    val context = LocalContext.current
    val favorito = viewModel.isRestauranteFav(restaurante.id)

    fun abrirGoogleMaps() {
        if (isOnline(context)) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${Uri.encode(restaurante.endereco)}"))
                intent.setPackage("com.google.android.apps.maps")
                context.startActivity(intent)
            } catch (e: Exception) {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(restaurante.endereco)}")))
            }
        } else {
            Toast.makeText(context, "Sem conexao a internet", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).verticalScroll(rememberScrollState())) {
            Box(modifier = Modifier.fillMaxWidth().height(280.dp)) {
                AsyncImage(model = restaurante.imagemUrl, contentDescription = restaurante.nome, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(colors = listOf(Color.Black.copy(alpha = 0.4f), Color.Transparent, Color.Black.copy(alpha = 0.3f)))))
            }
            Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                Text(restaurante.nome, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(18.dp)); Spacer(Modifier.width(4.dp))
                    Text("${restaurante.nota}", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFFFF9800)); Spacer(Modifier.width(4.dp))
                    Text("(${restaurante.avaliacoes} avaliações)", fontSize = 13.sp, color = Color.Gray)
                }
                Spacer(Modifier.height(8.dp))
                Text("${restaurante.categoria} • ${restaurante.distancia}", fontSize = 13.sp, color = Color.Gray)
                Spacer(Modifier.height(20.dp))
                Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant), modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) { Text("Preço médio", fontSize = 12.sp, color = Color.Gray); Text(restaurante.preco, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = BissauGreen) }
                    }
                }
                Spacer(Modifier.height(20.dp))
                Text("Sobre", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground); Spacer(Modifier.height(8.dp))
                Text(restaurante.descricao, fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f), lineHeight = 20.sp); Spacer(Modifier.height(20.dp))
                Text("Telefone", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground); Spacer(Modifier.height(8.dp))
                Text(restaurante.telefone, fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)); Spacer(Modifier.height(20.dp))
                Text("Endereço", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground); Spacer(Modifier.height(8.dp))
                Text(restaurante.endereco, fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)); Spacer(Modifier.height(110.dp))
            }
        }
        IconButton(onClick = onVoltar, modifier = Modifier.padding(16.dp).size(44.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.9f)).align(Alignment.TopStart)) { Icon(Icons.Filled.ArrowBack, "Voltar", tint = Color.Black) }
        IconButton(onClick = { viewModel.toggleRestaurante(restaurante.id) }, modifier = Modifier.padding(16.dp).size(44.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.9f)).align(Alignment.TopEnd)) {
            Icon(imageVector = if (favorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder, contentDescription = "Favorito", tint = if (favorito) Color(0xFFE91E63) else Color.Black)
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { try { context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${restaurante.telefone}"))) } catch (e: Exception) {} }, modifier = Modifier.weight(1f).height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = BissauGreen), shape = RoundedCornerShape(28.dp)) {
                    Icon(Icons.Filled.Phone, null, tint = Color.White, modifier = Modifier.size(20.dp)); Spacer(Modifier.width(6.dp)); Text("Ligar", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                }
                Button(onClick = { abrirGoogleMaps() }, modifier = Modifier.weight(1f).height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)), shape = RoundedCornerShape(28.dp)) {
                    Icon(Icons.Filled.LocationOn, null, tint = Color.White, modifier = Modifier.size(20.dp)); Spacer(Modifier.width(4.dp)); Text("Como chegar", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

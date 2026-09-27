package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bissaulife.app.data.Restaurante
import com.bissaulife.app.data.Restaurantes
import com.bissaulife.app.theme.BissauGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastronomiaScreen(
    onVoltar: () -> Unit,
    onVerDetalhes: (Restaurante) -> Unit
) {
    var filtroSelecionado by remember { mutableStateOf("Todos") }
    val filtros = listOf("Todos", "Restaurantes", "Comida típica", "Bolos")

    val listaFiltrada = remember(filtroSelecionado) {
        when (filtroSelecionado) {
            "Todos" -> Restaurantes.lista
            "Restaurantes" -> Restaurantes.lista.filter { it.categoria.contains("Português", true) }
            "Comida típica" -> Restaurantes.lista.filter { it.categoria.contains("Tradicional", true) || it.categoria.contains("Africana", true) }
            "Bolos" -> Restaurantes.lista.filter { it.categoria.contains("Doces", true) }
            else -> Restaurantes.lista
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Gastronomia",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notificações")
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
                .background(Color.White)
        ) {
            // Filtros
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filtros.forEach { filtro ->
                    val selecionado = filtro == filtroSelecionado
                    Surface(
                        onClick = { filtroSelecionado = filtro },
                        shape = RoundedCornerShape(20.dp),
                        color = if (selecionado) BissauGreen else Color(0xFFF0F0F0)
                    ) {
                        Text(
                            filtro,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            color = if (selecionado) Color.White else Color.Black,
                            fontSize = 13.sp,
                            fontWeight = if (selecionado) FontWeight.SemiBold else FontWeight.Normal
                        )
                    }
                }
            }

            // Lista
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listaFiltrada) { restaurante ->
                    CardRestaurante(
                        restaurante = restaurante,
                        onClick = { onVerDetalhes(restaurante) }
                    )
                }
            }
        }
    }
}

@Composable
fun CardRestaurante(restaurante: Restaurante, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = restaurante.imagemUrl,
            contentDescription = restaurante.nome,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                restaurante.nome,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Black
            )
            Spacer(Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    "${restaurante.nota}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = Color(0xFFFF9800)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    "(${restaurante.avaliacoes})",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    restaurante.categoria,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    "📍 ${restaurante.distancia}",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                modifier = Modifier.height(34.dp)
            ) {
                Text("Ver detalhes", fontSize = 12.sp)
            }
        }
    }
}

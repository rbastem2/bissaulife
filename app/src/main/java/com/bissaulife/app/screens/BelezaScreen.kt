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
import com.bissaulife.app.data.Beleza
import com.bissaulife.app.data.Item
import com.bissaulife.app.theme.BissauGreen
import com.bissaulife.app.theme.BissauPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BelezaScreen(
    onVoltar: () -> Unit,
    onVerDetalhes: (Item) -> Unit
) {
    var filtroSelecionado by remember { mutableStateOf("Todos") }
    val filtros = listOf("Todos", "Cabelo", "Unhas", "Maquiagem", "Spa", "Estetica")

    val listaFiltrada = remember(filtroSelecionado) {
        when (filtroSelecionado) {
            "Todos" -> Beleza.lista
            "Cabelo" -> Beleza.lista.filter { it.categoria.contains("Cabelo", true) }
            "Unhas" -> Beleza.lista.filter { it.categoria.contains("Unhas", true) }
            "Maquiagem" -> Beleza.lista.filter { it.categoria.contains("Maquiagem", true) }
            "Spa" -> Beleza.lista.filter { it.categoria.contains("Spa", true) }
            "Estetica" -> Beleza.lista.filter { it.categoria.contains("Estetica", true) }
            else -> Beleza.lista
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Beleza", fontWeight = FontWeight.Bold, fontSize = 20.sp)
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
                .background(Color.White)
        ) {
            BannerBeleza()
            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filtros.forEach { filtro ->
                    val selecionado = filtro == filtroSelecionado
                    Surface(
                        onClick = { filtroSelecionado = filtro },
                        shape = RoundedCornerShape(20.dp),
                        color = if (selecionado) BissauPink else Color(0xFFF0F0F0)
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

            Spacer(Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listaFiltrada) { item ->
                    CardBeleza(item = item, onClick = { onVerDetalhes(item) })
                }
            }
        }
    }
}

@Composable
fun BannerBeleza() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        AsyncImage(
            model = "https://images.unsplash.com/photo-1487412947147-5cebf100ffc2?w=800&q=80",
            contentDescription = "Beleza",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            BissauPink.copy(alpha = 0.8f),
                            Color.Transparent
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Cuide-se bem",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Saloes, spa, unhas e muito mais",
                color = Color.White.copy(alpha = 0.95f),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun CardBeleza(item: Item, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = item.imagemUrl,
            contentDescription = item.nome,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                item.nome,
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
                    "${item.nota}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = Color(0xFFFF9800)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    "(${item.avaliacoes})",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                item.categoria,
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(4.dp))

            Text(
                "A partir de ${item.preco}",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = BissauPink
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = BissauPink),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                modifier = Modifier.height(34.dp)
            ) {
                Text("Ver detalhes", fontSize = 12.sp)
            }
        }
    }
}

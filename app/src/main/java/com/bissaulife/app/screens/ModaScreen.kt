package com.bissaulife.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bissaulife.app.R
import com.bissaulife.app.data.Item
import com.bissaulife.app.data.Moda
import com.bissaulife.app.data.Negocio
import com.bissaulife.app.data.NegociosViewModel
import com.bissaulife.app.theme.BissauGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModaScreen(
    negociosVM: NegociosViewModel,
    onVoltar: () -> Unit,
    onVerDetalhes: (Item) -> Unit,
    onVerNegocio: (Negocio) -> Unit
) {
    var filtroSelecionado by remember { mutableStateOf("Todos") }
    val filtros = listOf("Todos", "Roupas", "Calcados", "Bolsas", "Acessorios")

    LaunchedEffect(Unit) {
        negociosVM.recarregar()
    }

    val novosNegocios = negociosVM.porCategoria("Moda")

    val listaFiltrada = remember(filtroSelecionado) {
        when (filtroSelecionado) {
            "Todos" -> Moda.lista
            "Roupas" -> Moda.lista.filter { it.categoria.contains("Roupas", true) }
            "Calcados" -> Moda.lista.filter { it.categoria.contains("Cal", true) }
            "Bolsas" -> Moda.lista.filter { it.categoria.contains("Bolsa", true) }
            "Acessorios" -> Moda.lista.filter { it.categoria.contains("Acess", true) }
            else -> Moda.lista
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Moda", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
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
            // BANNER com imagem
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .height(160.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.banner_moda),
                    contentDescription = "Moda",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.75f),
                                    Color.Black.copy(alpha = 0.15f)
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
                        "Estilo em todas as ocasioes",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Roupas, calcados, bolsas e acessorios",
                        color = Color.White.copy(alpha = 0.95f),
                        fontSize = 12.sp
                    )
                }
            }

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

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(novosNegocios) { negocio ->
                    CardNegocioModa(negocio = negocio, onClick = { onVerNegocio(negocio) })
                }
                items(listaFiltrada) { item ->
                    CardProduto(item = item, onClick = { onVerDetalhes(item) })
                }
            }
        }
    }
}

@Composable
fun CardNegocioModa(negocio: Negocio, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF5F5F5))
        ) {
            if (negocio.imagemUrl.isNotBlank()) {
                AsyncImage(
                    model = negocio.imagemUrl,
                    contentDescription = negocio.nome,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🛍️", fontSize = 50.sp)
                }
            }
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = BissauGreen,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
            ) {
                Text(
                    "NOVO",
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                    fontSize = 9.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(8.dp))
        Text(negocio.nome, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, maxLines = 1)
        Spacer(Modifier.height(3.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(13.dp))
            Spacer(Modifier.width(3.dp))
            Text("5.0", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFFFF9800))
            Spacer(Modifier.width(3.dp))
            Text("(Novo)", fontSize = 10.sp, color = Color.Gray)
        }
        Spacer(Modifier.height(3.dp))
        Text(negocio.preco.ifBlank { "Sob consulta" }, fontSize = 13.sp, color = Color(0xFF666666))
        Spacer(Modifier.height(6.dp))
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
            shape = RoundedCornerShape(18.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            Text("Ver detalhes", fontSize = 11.sp)
        }
    }
}

@Composable
fun CardProduto(item: Item, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF5F5F5))
        ) {
            AsyncImage(
                model = item.imagemUrl,
                contentDescription = item.nome,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(8.dp))
        Text(item.nome, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, maxLines = 1)
        Spacer(Modifier.height(2.dp))
        Text(item.preco, fontSize = 13.sp, color = Color(0xFF666666))
        Spacer(Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(14.dp))
            Spacer(Modifier.width(3.dp))
            Text("${item.nota}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFFFF9800))
            Spacer(Modifier.width(3.dp))
            Text("(${item.avaliacoes})", fontSize = 11.sp, color = Color.Gray)
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(BissauGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.AddShoppingCart, null, tint = Color.White, modifier = Modifier.size(16.dp))
            }
        }
    }
}

package com.bissaulife.app.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tour
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bissaulife.app.R
import com.bissaulife.app.data.Item
import com.bissaulife.app.data.Negocio
import com.bissaulife.app.data.NegociosViewModel
import com.bissaulife.app.data.Viagens
import com.bissaulife.app.theme.BissauGreen

data class CategoriaViagem(val nome: String, val icone: ImageVector, val cor: Color)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViagensScreen(
    negociosVM: NegociosViewModel,
    onVoltar: () -> Unit,
    onVerDetalhes: (Item) -> Unit,
    onVerNegocio: (Negocio) -> Unit
) {
    var categoriaSelecionada by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        negociosVM.recarregar()
    }

    val novosNegocios = negociosVM.porCategoria("Viagens")

    val listaFiltrada = remember(categoriaSelecionada) {
        if (categoriaSelecionada.isEmpty()) Viagens.lista
        else Viagens.lista.filter { it.categoria.contains(categoriaSelecionada, true) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Viagens", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                // BANNER com imagem
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .height(180.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.banner_viagens),
                        contentDescription = "Viagens",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.7f),
                                        Color.Black.copy(alpha = 0.1f)
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
                            "Explore a Guine-Bissau",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "Ilhas, cultura, natureza e aventura",
                            color = Color.White.copy(alpha = 0.95f),
                            fontSize = 13.sp
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
            }

            item {
                CategoriasViagemRow(
                    selecionada = categoriaSelecionada,
                    onSelecionar = { nova ->
                        categoriaSelecionada = if (categoriaSelecionada == nova) "" else nova
                    }
                )
                Spacer(Modifier.height(16.dp))
            }

            if (novosNegocios.isNotEmpty()) {
                item {
                    Text(
                        "🆕 Adicionados recentemente",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = BissauGreen,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                }
                items(novosNegocios) { negocio ->
                    ItemViagemNegocio(negocio = negocio, onClick = { onVerNegocio(negocio) })
                    Spacer(Modifier.height(12.dp))
                }
                item {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Sugestoes da nossa equipe",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Destaques",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        "Ver todos >",
                        fontSize = 13.sp,
                        color = Color(0xFF2196F3),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(12.dp))
            }

            items(listaFiltrada) { item ->
                ItemViagem(item = item, onClick = { onVerDetalhes(item) })
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ItemViagemNegocio(negocio: Negocio, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.Top
    ) {
        if (negocio.imagemUrl.isNotBlank()) {
            AsyncImage(
                model = negocio.imagemUrl,
                contentDescription = negocio.nome,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        } else {
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(BissauGreen.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text("✈️", fontSize = 40.sp)
            }
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(shape = RoundedCornerShape(6.dp), color = BissauGreen) {
                    Text(
                        "NOVO",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        fontSize = 9.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.width(6.dp))
                Text(negocio.subcategoria, fontSize = 11.sp, color = Color.Gray)
            }
            Spacer(Modifier.height(4.dp))
            Text(negocio.nome, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(15.dp))
                Spacer(Modifier.width(4.dp))
                Text("5.0", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFFFF9800))
                Spacer(Modifier.width(4.dp))
                Text("(Novo)", fontSize = 11.sp, color = Color.Gray)
            }
            Spacer(Modifier.height(4.dp))
            if (negocio.preco.isNotBlank()) {
                Text("A partir de ${negocio.preco}", fontSize = 13.sp, color = Color(0xFF666666))
            }
            Spacer(Modifier.height(4.dp))
            Text("📍 ${negocio.endereco}", fontSize = 11.sp, color = Color.Gray, maxLines = 1)
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

@Composable
fun CategoriasViagemRow(selecionada: String, onSelecionar: (String) -> Unit) {
    val categorias = listOf(
        CategoriaViagem("Hoteis", Icons.Filled.Hotel, Color(0xFF2196F3)),
        CategoriaViagem("Casas", Icons.Filled.Home, Color(0xFF4CAF50)),
        CategoriaViagem("Passeios", Icons.Filled.Tour, Color(0xFF2196F3)),
        CategoriaViagem("Transporte", Icons.Filled.DirectionsCar, Color(0xFF3F51B5))
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        categorias.forEach { cat ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onSelecionar(cat.nome) }
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(
                            if (selecionada == cat.nome) cat.cor else cat.cor.copy(alpha = 0.85f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(cat.icone, null, tint = Color.White, modifier = Modifier.size(28.dp))
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    cat.nome,
                    fontSize = 11.sp,
                    color = Color.Black,
                    fontWeight = if (selecionada == cat.nome) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun ItemViagem(item: Item, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.CenterVertically
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
            Text(item.nome, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(Modifier.height(4.dp))
            Text("A partir de ${item.preco}", fontSize = 13.sp, color = Color(0xFF666666))
            Spacer(Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(3.dp))
                Text("${item.nota}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFFFF9800))
                Spacer(Modifier.width(3.dp))
                Text("(${item.avaliacoes})", fontSize = 11.sp, color = Color.Gray)
            }
        }

        Icon(Icons.Filled.ArrowForwardIos, null, tint = Color.Gray, modifier = Modifier.size(16.dp))
    }
}

package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bissaulife.app.data.Beleza
import com.bissaulife.app.data.FavoritosViewModel
import com.bissaulife.app.data.Item
import com.bissaulife.app.data.Moda
import com.bissaulife.app.data.Restaurante
import com.bissaulife.app.data.Restaurantes
import com.bissaulife.app.data.Viagens
import com.bissaulife.app.theme.BissauGreen

sealed class FavoritoItem {
    data class RestauranteFav(val item: Restaurante) : FavoritoItem()
    data class ModaFav(val item: Item) : FavoritoItem()
    data class ViagemFav(val item: Item) : FavoritoItem()
    data class BelezaFav(val item: Item) : FavoritoItem()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritosScreen(
    viewModel: FavoritosViewModel,
    onAbrirRestaurante: (Restaurante) -> Unit,
    onAbrirItem: (Item, String) -> Unit
) {
    val versao = viewModel.versao

    val lista = remember(versao) {
        buildList {
            viewModel.idsRestaurantes().forEach { id ->
                Restaurantes.lista.find { it.id == id }?.let { add(FavoritoItem.RestauranteFav(it)) }
            }
            viewModel.idsModa().forEach { id ->
                Moda.lista.find { it.id == id }?.let { add(FavoritoItem.ModaFav(it)) }
            }
            viewModel.idsViagens().forEach { id ->
                Viagens.lista.find { it.id == id }?.let { add(FavoritoItem.ViagemFav(it)) }
            }
            viewModel.idsBeleza().forEach { id ->
                Beleza.lista.find { it.id == id }?.let { add(FavoritoItem.BelezaFav(it)) }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Cabecalho
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BissauGreen)
                .padding(20.dp)
        ) {
            Text(
                "Favoritos",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (lista.isEmpty()) {
            VazioFavoritos()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(lista) { favorito ->
                    when (favorito) {
                        is FavoritoItem.RestauranteFav -> {
                            CardFavorito(
                                imagemUrl = favorito.item.imagemUrl,
                                nome = favorito.item.nome,
                                categoria = favorito.item.categoria,
                                preco = "A partir de ${favorito.item.preco}",
                                tipo = "Restaurante",
                                onClick = { onAbrirRestaurante(favorito.item) },
                                onRemover = { viewModel.toggleRestaurante(favorito.item.id) }
                            )
                        }
                        is FavoritoItem.ModaFav -> {
                            CardFavorito(
                                imagemUrl = favorito.item.imagemUrl,
                                nome = favorito.item.nome,
                                categoria = favorito.item.categoria,
                                preco = favorito.item.preco,
                                tipo = "Moda",
                                onClick = { onAbrirItem(favorito.item, "moda") },
                                onRemover = { viewModel.toggleModa(favorito.item.id) }
                            )
                        }
                        is FavoritoItem.ViagemFav -> {
                            CardFavorito(
                                imagemUrl = favorito.item.imagemUrl,
                                nome = favorito.item.nome,
                                categoria = favorito.item.categoria,
                                preco = "A partir de ${favorito.item.preco}",
                                tipo = "Viagem",
                                onClick = { onAbrirItem(favorito.item, "viagem") },
                                onRemover = { viewModel.toggleViagem(favorito.item.id) }
                            )
                        }
                        is FavoritoItem.BelezaFav -> {
                            CardFavorito(
                                imagemUrl = favorito.item.imagemUrl,
                                nome = favorito.item.nome,
                                categoria = favorito.item.categoria,
                                preco = "A partir de ${favorito.item.preco}",
                                tipo = "Beleza",
                                onClick = { onAbrirItem(favorito.item, "beleza") },
                                onRemover = { viewModel.toggleBeleza(favorito.item.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VazioFavoritos() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = null,
            tint = Color(0xFFE0E0E0),
            modifier = Modifier.size(90.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "Ainda sem favoritos",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF666666)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Toque no coracao de qualquer item para salva-lo aqui.",
            fontSize = 14.sp,
            color = Color(0xFF999999),
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun CardFavorito(
    imagemUrl: String,
    nome: String,
    categoria: String,
    preco: String,
    tipo: String,
    onClick: () -> Unit,
    onRemover: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imagemUrl,
                contentDescription = nome,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = BissauGreen.copy(alpha = 0.15f)
                ) {
                    Text(
                        tipo,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        fontSize = 10.sp,
                        color = BissauGreen,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    nome,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    categoria,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    preco,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BissauGreen
                )
            }

            IconButton(onClick = onRemover) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "Remover",
                    tint = Color(0xFFE53935)
                )
            }
        }
    }
}

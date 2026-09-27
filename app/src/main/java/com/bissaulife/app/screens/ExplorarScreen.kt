package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
import com.bissaulife.app.data.Item
import com.bissaulife.app.data.Moda
import com.bissaulife.app.data.Restaurante
import com.bissaulife.app.data.Restaurantes
import com.bissaulife.app.data.Viagens
import com.bissaulife.app.theme.BissauGreen
import com.bissaulife.app.theme.BissauOrange
import com.bissaulife.app.theme.BissauPink
import com.bissaulife.app.theme.BissauPurple

sealed class ResultadoBusca {
    data class RestauranteR(val item: Restaurante) : ResultadoBusca()
    data class ModaR(val item: Item) : ResultadoBusca()
    data class ViagemR(val item: Item) : ResultadoBusca()
    data class BelezaR(val item: Item) : ResultadoBusca()
}

@Composable
fun ExplorarScreen(
    onAbrirRestaurante: (Restaurante) -> Unit,
    onAbrirItem: (Item, String) -> Unit
) {
    var query by remember { mutableStateOf("") }

    val resultados = remember(query) {
        if (query.trim().length < 2) emptyList()
        else {
            val q = query.trim().lowercase()
            buildList {
                Restaurantes.lista
                    .filter {
                        it.nome.lowercase().contains(q) ||
                        it.categoria.lowercase().contains(q) ||
                        it.descricao.lowercase().contains(q)
                    }
                    .forEach { add(ResultadoBusca.RestauranteR(it)) }

                Moda.lista
                    .filter {
                        it.nome.lowercase().contains(q) ||
                        it.categoria.lowercase().contains(q) ||
                        it.descricao.lowercase().contains(q)
                    }
                    .forEach { add(ResultadoBusca.ModaR(it)) }

                Viagens.lista
                    .filter {
                        it.nome.lowercase().contains(q) ||
                        it.categoria.lowercase().contains(q) ||
                        it.descricao.lowercase().contains(q)
                    }
                    .forEach { add(ResultadoBusca.ViagemR(it)) }

                Beleza.lista
                    .filter {
                        it.nome.lowercase().contains(q) ||
                        it.categoria.lowercase().contains(q) ||
                        it.descricao.lowercase().contains(q)
                    }
                    .forEach { add(ResultadoBusca.BelezaR(it)) }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BissauGreen)
                .padding(20.dp)
        ) {
            Text(
                "Explorar",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Barra de busca
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Buscar restaurantes, lojas, hoteis...", fontSize = 13.sp) },
            leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.Gray) },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { query = "" }) {
                        Icon(Icons.Filled.Clear, null, tint = Color.Gray)
                    }
                }
            },
            shape = RoundedCornerShape(28.dp),
            singleLine = true
        )

        // Conteudo
        when {
            query.trim().length < 2 -> InstrucoesBusca()
            resultados.isEmpty() -> NadaEncontrado(query)
            else -> {
                Text(
                    "${resultados.size} resultado(s) para \"$query\"",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(resultados) { resultado ->
                        when (resultado) {
                            is ResultadoBusca.RestauranteR -> {
                                CardResultado(
                                    imagemUrl = resultado.item.imagemUrl,
                                    nome = resultado.item.nome,
                                    categoria = resultado.item.categoria,
                                    preco = "A partir de ${resultado.item.preco}",
                                    nota = resultado.item.nota,
                                    tipo = "Restaurante",
                                    corTipo = BissauOrange,
                                    onClick = { onAbrirRestaurante(resultado.item) }
                                )
                            }
                            is ResultadoBusca.ModaR -> {
                                CardResultado(
                                    imagemUrl = resultado.item.imagemUrl,
                                    nome = resultado.item.nome,
                                    categoria = resultado.item.categoria,
                                    preco = resultado.item.preco,
                                    nota = resultado.item.nota,
                                    tipo = "Moda",
                                    corTipo = BissauPurple,
                                    onClick = { onAbrirItem(resultado.item, "moda") }
                                )
                            }
                            is ResultadoBusca.ViagemR -> {
                                CardResultado(
                                    imagemUrl = resultado.item.imagemUrl,
                                    nome = resultado.item.nome,
                                    categoria = resultado.item.categoria,
                                    preco = "A partir de ${resultado.item.preco}",
                                    nota = resultado.item.nota,
                                    tipo = "Viagem",
                                    corTipo = Color(0xFF2196F3),
                                    onClick = { onAbrirItem(resultado.item, "viagem") }
                                )
                            }
                            is ResultadoBusca.BelezaR -> {
                                CardResultado(
                                    imagemUrl = resultado.item.imagemUrl,
                                    nome = resultado.item.nome,
                                    categoria = resultado.item.categoria,
                                    preco = "A partir de ${resultado.item.preco}",
                                    nota = resultado.item.nota,
                                    tipo = "Beleza",
                                    corTipo = BissauPink,
                                    onClick = { onAbrirItem(resultado.item, "beleza") }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InstrucoesBusca() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Filled.Search,
            contentDescription = null,
            tint = Color(0xFFDDDDDD),
            modifier = Modifier.size(90.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "O que voce procura?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF666666)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Digite pelo menos 2 letras para buscar em restaurantes, moda, viagens e beleza.",
            fontSize = 13.sp,
            color = Color(0xFF999999),
            textAlign = TextAlign.Center,
            lineHeight = 19.sp
        )
    }
}

@Composable
fun NadaEncontrado(query: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🔍", fontSize = 60.sp)
        Spacer(Modifier.height(16.dp))
        Text(
            "Nada encontrado",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF666666)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Nao encontramos resultados para \"$query\". Tente outra palavra.",
            fontSize = 13.sp,
            color = Color(0xFF999999),
            textAlign = TextAlign.Center,
            lineHeight = 19.sp
        )
    }
}

@Composable
fun CardResultado(
    imagemUrl: String,
    nome: String,
    categoria: String,
    preco: String,
    nota: Double,
    tipo: String,
    corTipo: Color,
    onClick: () -> Unit
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
                    .size(85.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = corTipo.copy(alpha = 0.15f)
                ) {
                    Text(
                        tipo,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        fontSize = 10.sp,
                        color = corTipo,
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        "$nota",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFFF9800)
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        preco,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BissauGreen
                    )
                }
            }
        }
    }
}

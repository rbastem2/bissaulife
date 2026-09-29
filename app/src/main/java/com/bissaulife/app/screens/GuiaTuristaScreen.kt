package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.theme.BissauGreen

data class CategoriaTurista(
    val nome: String,
    val icone: String,
    val cor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuiaTuristaScreen(
    onVoltar: () -> Unit,
    onAbrirCategoria: (String) -> Unit
) {
    val categorias = listOf(
        CategoriaTurista("Hospitais", "🏥", Color(0xFFE53935)),
        CategoriaTurista("Embaixadas", "🌍", Color(0xFF1E88E5)),
        CategoriaTurista("Ministerios", "🏛️", Color(0xFF6D4C41)),
        CategoriaTurista("Futebol", "⚽", Color(0xFF43A047)),
        CategoriaTurista("Farmacias", "💊", Color(0xFF00ACC1)),
        CategoriaTurista("Discotecas", "🎉", Color(0xFF8E24AA)),
        CategoriaTurista("Supermercados", "🛒", Color(0xFFFB8C00)),
        CategoriaTurista("Feiras", "🛍️", Color(0xFFD81B60)),
        CategoriaTurista("Combustivel", "⛽", Color(0xFF546E7A)),
        CategoriaTurista("Bancos e Cambio", "🏦", Color(0xFF00897B)),
        CategoriaTurista("Pontos Turisticos", "📸", Color(0xFF6A1B9A))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Guia do Turista", fontWeight = FontWeight.Bold, fontSize = 20.sp)
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFF003366), Color(0xFF00A86B))
                        )
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        "Bem-vindo a Guine-Bissau!",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Tudo o que voce precisa saber para se orientar na cidade.",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                "Escolha uma categoria",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categorias) { cat ->
                    CardCategoriaTurista(
                        categoria = cat,
                        onClick = { onAbrirCategoria(cat.nome) }
                    )
                }
            }
        }
    }
}

@Composable
fun CardCategoriaTurista(categoria: CategoriaTurista, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(50))
                    .background(categoria.cor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(categoria.icone, fontSize = 32.sp)
            }
            Spacer(Modifier.height(10.dp))
            Text(
                categoria.nome,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

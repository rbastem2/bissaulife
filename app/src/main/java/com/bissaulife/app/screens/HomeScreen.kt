package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.data.FavoritosViewModel
import com.bissaulife.app.data.Item
import com.bissaulife.app.data.Restaurante
import com.bissaulife.app.theme.*

data class Categoria(val nome: String, val icone: ImageVector, val cor: Color)

@Composable
fun HomeScreen(
    viewModel: FavoritosViewModel,
    onAbrirCategoria: (String) -> Unit,
    onAbrirRestaurante: (Restaurante) -> Unit,
    onAbrirItem: (Item, String) -> Unit,
    onAbrirPlanos: () -> Unit
) {
    var abaAtual by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = abaAtual == 0,
                    onClick = { abaAtual = 0 },
                    icon = { Icon(Icons.Filled.Home, null) },
                    label = { Text("Inicio") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BissauGreen,
                        selectedTextColor = BissauGreen,
                        indicatorColor = BissauGreen.copy(alpha = 0.15f)
                    )
                )
                NavigationBarItem(
                    selected = abaAtual == 1,
                    onClick = { abaAtual = 1 },
                    icon = { Icon(Icons.Filled.Search, null) },
                    label = { Text("Explorar") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BissauGreen,
                        selectedTextColor = BissauGreen,
                        indicatorColor = BissauGreen.copy(alpha = 0.15f)
                    )
                )
                NavigationBarItem(
                    selected = abaAtual == 2,
                    onClick = { abaAtual = 2 },
                    icon = { Icon(Icons.Outlined.FavoriteBorder, null) },
                    label = { Text("Favoritos") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BissauGreen,
                        selectedTextColor = BissauGreen,
                        indicatorColor = BissauGreen.copy(alpha = 0.15f)
                    )
                )
                NavigationBarItem(
                    selected = abaAtual == 3,
                    onClick = { abaAtual = 3 },
                    icon = { Icon(Icons.Filled.Person, null) },
                    label = { Text("Perfil") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BissauGreen,
                        selectedTextColor = BissauGreen,
                        indicatorColor = BissauGreen.copy(alpha = 0.15f)
                    )
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (abaAtual) {
                0 -> ConteudoInicio(onAbrirCategoria)
                1 -> ExplorarPlaceholder()
                2 -> FavoritosScreen(
                    viewModel = viewModel,
                    onAbrirRestaurante = onAbrirRestaurante,
                    onAbrirItem = onAbrirItem
                )
                3 -> PerfilScreen(onAbrirPlanos = onAbrirPlanos)
            }
        }
    }
}

@Composable
fun ConteudoInicio(onAbrirCategoria: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .verticalScroll(rememberScrollState())
    ) {
        Cabecalho()
        Spacer(Modifier.height(16.dp))
        CategoriasGrid(onCategoriaClick = onAbrirCategoria)
        Spacer(Modifier.height(16.dp))
        BarraBusca()
        Spacer(Modifier.height(16.dp))
        BannerDestaque(onClick = { onAbrirCategoria("Gastronomia") })
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun ExplorarPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(32.dp),
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
            "Explorar",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF666666)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Em breve voce podera buscar servicos, produtos e lugares.",
            fontSize = 13.sp,
            color = Color(0xFF999999),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 19.sp
        )
    }
}

@Composable
fun Cabecalho() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(BissauDarkBlue, Color(0xFF00509E))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🌍", fontSize = 28.sp)
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        "BissauLife",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Descubra. Escolha. Compre. Viva.",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(
                "O seu guia e marketplace da Guine-Bissau.",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun CategoriasGrid(onCategoriaClick: (String) -> Unit) {
    val categorias = listOf(
        Categoria("Gastronomia", Icons.Filled.Restaurant, BissauOrange),
        Categoria("Beleza", Icons.Filled.Spa, BissauPink),
        Categoria("Moda", Icons.Filled.Checkroom, BissauPurple),
        Categoria("Viagens", Icons.Filled.Flight, BissauBlue)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categorias.forEach { cat ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(cat.cor)
                    .clickable { onCategoriaClick(cat.nome) }
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(cat.icone, null, tint = Color.White, modifier = Modifier.size(32.dp))
                Spacer(Modifier.height(8.dp))
                Text(
                    cat.nome,
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun BarraBusca() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = { Text("Pesquisar servicos, produtos, lugares...", fontSize = 13.sp) },
        leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.Gray) },
        shape = RoundedCornerShape(28.dp),
        singleLine = true
    )
}

@Composable
fun BannerDestaque(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF003366), Color(0xFF00A86B))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Sabores da nossa terra",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Restaurantes, pratos tipicos e muito mais.",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 13.sp
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Ver agora")
            }
        }
    }
}

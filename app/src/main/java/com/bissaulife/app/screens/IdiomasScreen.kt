package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.theme.BissauGreen

data class Idioma(
    val codigo: String,
    val nome: String,
    val nativo: String,
    val bandeira: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdiomasScreen(onVoltar: () -> Unit) {
    var idiomaSelecionado by remember { mutableStateOf("pt") }

    val idiomas = listOf(
        Idioma("pt", "Portugues", "Portugues", "🇬🇼"),
        Idioma("fr", "Frances", "Francais", "🇫🇷"),
        Idioma("en", "Ingles", "English", "🇬🇧"),
        Idioma("es", "Espanhol", "Espanol", "🇪🇸"),
        Idioma("kr", "Kriol", "Kriol", "🇬🇼")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Idiomas", fontWeight = FontWeight.Bold, fontSize = 20.sp)
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
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            Text(
                "Escolha o idioma do app",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    idiomas.forEachIndexed { index, idioma ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { idiomaSelecionado = idioma.codigo }
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFF0F0F0)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(idioma.bandeira, fontSize = 22.sp)
                            }
                            Spacer(Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    idioma.nome,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                Text(
                                    idioma.nativo,
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                            if (idiomaSelecionado == idioma.codigo) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(BissauGreen),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        Icons.Filled.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                        if (index < idiomas.size - 1) {
                            Divider(color = Color(0xFFEEEEEE))
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("ℹ️", fontSize = 20.sp)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        "A traducao completa para os outros idiomas sera adicionada em breve.",
                        fontSize = 12.sp,
                        color = Color(0xFF795548),
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }
}

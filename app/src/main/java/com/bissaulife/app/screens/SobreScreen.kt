package com.bissaulife.app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.R
import com.bissaulife.app.theme.BissauGreen

private const val WHATSAPP_COMERCIAL = "245955572393"
private const val EMAIL_CONTATO = "contato@bissaulife.gw"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreScreen(onVoltar: () -> Unit) {
    val context = LocalContext.current

    fun abrirWhatsApp() {
        val mensagem = "Ola! Vim pelo app BissauLife."
        val url = "https://wa.me/$WHATSAPP_COMERCIAL?text=${Uri.encode(mensagem)}"
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
        }
    }

    fun enviarEmail() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$EMAIL_CONTATO"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Contato BissauLife")
            context.startActivity(intent)
        } catch (e: Exception) {
        }
    }

    fun compartilharApp() {
        val texto = "Conheca o BissauLife - o guia e marketplace da Guine-Bissau!\n" +
                "https://github.com/rbastem2/bissaulife"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, texto)
        }
        context.startActivity(Intent.createChooser(intent, "Compartilhar BissauLife"))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Sobre o BissauLife", fontWeight = FontWeight.Bold, fontSize = 19.sp)
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "BissauLife",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(24.dp))
            )

            Spacer(Modifier.height(14.dp))

            Text(
                "BissauLife",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                "Versao 1.1",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(24.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        "O que e o BissauLife?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "O BissauLife e o guia completo e marketplace da Guine-Bissau. " +
                        "Aqui voce descobre restaurantes, saloes de beleza, lojas de moda e " +
                        "destinos turisticos, tudo em um so lugar.\n\n" +
                        "Nosso objetivo e conectar pessoas aos melhores servicos e produtos " +
                        "do pais, valorizando o comercio local e a cultura guineense.\n\n" +
                        "Descubra. Escolha. Compre. Viva.",
                        fontSize = 13.sp,
                        color = Color(0xFF444444),
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("4", "Categorias")
                    StatItem("24", "Lugares")
                    StatItem("100%", "Guineense")
                }
            }

            Spacer(Modifier.height(14.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        "Fale conosco",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 18.dp, top = 16.dp, bottom = 8.dp)
                    )
                    ItemContato(
                        icone = Icons.Filled.Phone,
                        titulo = "WhatsApp",
                        subtitulo = "+245 955 572 393",
                        onClick = { abrirWhatsApp() }
                    )
                    Divider(color = Color(0xFFEEEEEE))
                    ItemContato(
                        icone = Icons.Filled.Email,
                        titulo = "E-mail",
                        subtitulo = EMAIL_CONTATO,
                        onClick = { enviarEmail() }
                    )
                    Divider(color = Color(0xFFEEEEEE))
                    ItemContato(
                        icone = Icons.Filled.Language,
                        titulo = "Site",
                        subtitulo = "bissaulife.gw (em breve)",
                        onClick = { }
                    )
                    Divider(color = Color(0xFFEEEEEE))
                    ItemContato(
                        icone = Icons.Filled.Share,
                        titulo = "Compartilhar o app",
                        subtitulo = "Convide amigos",
                        onClick = { compartilharApp() }
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                "Feito com carinho na Guine-Bissau",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "(c) 2025 BissauLife. Todos os direitos reservados.",
                fontSize = 11.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun StatItem(valor: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            valor,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = BissauGreen
        )
        Text(
            label,
            fontSize = 11.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun ItemContato(
    icone: ImageVector,
    titulo: String,
    subtitulo: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icone, contentDescription = null, tint = BissauGreen, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                subtitulo,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        TextButton(onClick = onClick) {
            Text("Abrir", color = BissauGreen, fontSize = 13.sp)
        }
    }
}

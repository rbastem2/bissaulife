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
private const val EMAIL_CONTATO = "bissaulife@gmail.com"
private const val URL_APKPURE = "https://apkpure.com/p/com.bissaulife.app"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreScreen(onVoltar: () -> Unit) {
    val context = LocalContext.current

    fun abrirWhatsApp() {
        val mensagem = "Olá! Vim pelo app BissauLife."
        val url = "https://wa.me/$WHATSAPP_COMERCIAL?text=${Uri.encode(mensagem)}"
        try { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) } catch (e: Exception) {}
    }

    fun enviarEmail() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$EMAIL_CONTATO"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Contato BissauLife")
            context.startActivity(intent)
        } catch (e: Exception) {}
    }

    fun compartilharApp() {
        val texto = "Conheça o BissauLife - o guia e marketplace da Guiné-Bissau!\n" +
                "Baixe agora na APKPure:\n$URL_APKPURE"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, texto)
        }
        context.startActivity(Intent.createChooser(intent, "Compartilhar BissauLife"))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sobre o BissauLife", fontWeight = FontWeight.Bold, fontSize = 19.sp) },
                navigationIcon = { IconButton(onClick = onVoltar) { Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar") } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface, titleContentColor = MaterialTheme.colorScheme.onSurface)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).background(MaterialTheme.colorScheme.background).verticalScroll(rememberScrollState()).padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "BissauLife", contentScale = ContentScale.Crop, modifier = Modifier.size(100.dp).clip(RoundedCornerShape(24.dp)))
            Spacer(Modifier.height(14.dp))
            Text("BissauLife", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
            Text("Versão 1.5", fontSize = 13.sp, color = Color.Gray)
            Spacer(Modifier.height(24.dp))

            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("O que é o BissauLife?", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "O BissauLife é o guia completo e marketplace da Guiné-Bissau. Aqui você descobre restaurantes, salões de beleza, lojas de moda e destinos turísticos, tudo em um só lugar.\n\n" +
                        "Nosso objetivo é conectar pessoas aos melhores serviços e produtos do país, valorizando o comércio local e a cultura guineense.\n\n" +
                        "Descubra. Escolha. Compre. Viva.",
                        fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f), lineHeight = 20.sp
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Row(modifier = Modifier.fillMaxWidth().padding(18.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                    StatItem("4", "Categorias")
                    StatItem("96", "Locais no Guia")
                    StatItem("100%", "Guineense")
                }
            }

            Spacer(Modifier.height(14.dp))

            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Column {
                    Text("Fale conosco", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(start = 18.dp, top = 16.dp, bottom = 8.dp))
                    ItemContato(Icons.Filled.Phone, "WhatsApp", "+245 955 572 393", { abrirWhatsApp() })
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                    ItemContato(Icons.Filled.Email, "E-mail", EMAIL_CONTATO, { enviarEmail() })
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                    ItemContato(Icons.Filled.Language, "Site", "bissaulife.gw (em breve)", { })
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                    ItemContato(Icons.Filled.Share, "Compartilhar o app", "Envie o link da APKPure", { compartilharApp() })
                }
            }

            Spacer(Modifier.height(20.dp))
            BotaoDoacao()
            Spacer(Modifier.height(24.dp))

            Text("Feito com carinho na Guiné-Bissau", fontSize = 12.sp, color = Color.Gray, textAlign = TextAlign.Center)
            Spacer(Modifier.height(4.dp))
            Text("(c) 2026 BissauLife. Todos os direitos reservados.", fontSize = 11.sp, color = Color.LightGray, textAlign = TextAlign.Center)
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun StatItem(valor: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(valor, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = BissauGreen)
        Text(label, fontSize = 11.sp, color = Color.Gray)
    }
}

@Composable
fun ItemContato(icone: ImageVector, titulo: String, subtitulo: String, onClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icone, contentDescription = null, tint = BissauGreen, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(titulo, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
            Text(subtitulo, fontSize = 12.sp, color = Color.Gray)
        }
        TextButton(onClick = onClick) { Text("Abrir", color = BissauGreen, fontSize = 13.sp) }
    }
}

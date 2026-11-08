package com.bissaulife.app.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.theme.BissauGreen

private const val URL_ATUALIZAR = "https://apkpure.com/p/com.bissaulife.app"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefinicoesScreen(
    onVoltar: () -> Unit,
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var notificacoesAtivas by remember { mutableStateOf(true) }

    fun abrirLink(url: String) {
        try { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) } catch (e: Exception) {}
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Definições", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onVoltar) { Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar") }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface, titleContentColor = MaterialTheme.colorScheme.onSurface)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text("Preferências", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.padding(start = 6.dp, bottom = 8.dp))
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth()) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Notifications, null, tint = BissauGreen, modifier = Modifier.size(22.dp))
                        Spacer(Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Ativar notificações", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                            Text("Receber novidades e ofertas", fontSize = 12.sp, color = Color.Gray)
                        }
                        Switch(checked = notificacoesAtivas, onCheckedChange = { notificacoesAtivas = it }, colors = SwitchDefaults.colors(checkedThumbColor = BissauGreen, checkedTrackColor = BissauGreen.copy(alpha = 0.5f)))
                    }
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                    
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Storage, null, tint = BissauGreen, modifier = Modifier.size(22.dp))
                        Spacer(Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Modo escuro", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                            Text("Alterar tema do aplicativo", fontSize = 12.sp, color = Color.Gray)
                        }
                        Switch(checked = isDarkMode, onCheckedChange = { onToggleDarkMode(it) }, colors = SwitchDefaults.colors(checkedThumbColor = BissauGreen, checkedTrackColor = BissauGreen.copy(alpha = 0.5f)))
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            Text("Privacidade e segurança", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.padding(start = 6.dp, bottom = 8.dp))
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth()) {
                Column {
                    ItemDefinicao(Icons.Filled.Lock, "Política de privacidade", "Como usamos seus dados", { abrirLink("https://rbastem2.github.io/bissaulife/politica_privacidade.html") })
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                    ItemDefinicao(Icons.Filled.Lock, "Termos de uso", "Regras do app", { abrirLink("https://rbastem2.github.io/bissaulife/politica_privacidade.html") })
                }
            }

            Spacer(Modifier.height(18.dp))

            Text("Atualizações", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.padding(start = 6.dp, bottom = 8.dp))
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth()) {
                Column {
                    ItemDefinicao(Icons.Filled.Refresh, "Versão atual", "1.5", { })
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                    ItemDefinicao(Icons.Filled.Download, "Verificar atualizações", "Baixar nova versão na APKPure", { abrirLink(URL_ATUALIZAR) })
                }
            }

            Spacer(Modifier.height(30.dp))
            Text("BissauLife v1.5", fontSize = 11.sp, color = Color.LightGray, modifier = Modifier.fillMaxWidth(), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }
    }
}

@Composable
fun ItemDefinicao(icone: ImageVector, titulo: String, subtitulo: String, onClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icone, contentDescription = null, tint = BissauGreen, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(titulo, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
            Text(subtitulo, fontSize = 12.sp, color = Color.Gray)
        }
        Icon(Icons.Filled.ArrowForward, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(14.dp))
    }
}

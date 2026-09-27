package com.bissaulife.app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Storage
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

private const val WHATSAPP_COMERCIAL = "245955572393"
private const val URL_ATUALIZAR = "https://github.com/rbastem2/bissaulife/actions"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefinicoesScreen(onVoltar: () -> Unit) {
    val context = LocalContext.current
    var notificacoesAtivas by remember { mutableStateOf(true) }
    var modoEscuro by remember { mutableStateOf(false) }

    fun abrirNavegador(url: String) {
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Definicoes", fontWeight = FontWeight.Bold, fontSize = 20.sp)
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
                .padding(16.dp)
        ) {
            // NOTIFICACOES
            Text(
                "Notificacoes",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                modifier = Modifier.padding(start = 6.dp, bottom = 8.dp)
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.Notifications, null, tint = BissauGreen, modifier = Modifier.size(22.dp))
                        Spacer(Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Ativar notificacoes",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                            Text(
                                "Receber novidades e ofertas",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                        Switch(
                            checked = notificacoesAtivas,
                            onCheckedChange = { notificacoesAtivas = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = BissauGreen, checkedTrackColor = BissauGreen.copy(alpha = 0.5f))
                        )
                    }
                    Divider(color = Color(0xFFEEEEEE))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.Storage, null, tint = BissauGreen, modifier = Modifier.size(22.dp))
                        Spacer(Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Modo escuro",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                            Text(
                                "Em breve",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                        Switch(
                            checked = modoEscuro,
                            onCheckedChange = { },
                            enabled = false,
                            colors = SwitchDefaults.colors(checkedThumbColor = BissauGreen, checkedTrackColor = BissauGreen.copy(alpha = 0.5f))
                        )
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            // PRIVACIDADE E SEGURANCA
            Text(
                "Privacidade e seguranca",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                modifier = Modifier.padding(start = 6.dp, bottom = 8.dp)
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    ItemDefinicao(
                        icone = Icons.Filled.Lock,
                        titulo = "Politica de privacidade",
                        subtitulo = "Como usamos seus dados",
                        onClick = { abrirNavegador("https://github.com/rbastem2/bissaulife") }
                    )
                    Divider(color = Color(0xFFEEEEEE))
                    ItemDefinicao(
                        icone = Icons.Filled.Lock,
                        titulo = "Termos de uso",
                        subtitulo = "Regras do app",
                        onClick = { abrirNavegador("https://github.com/rbastem2/bissaulife") }
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            // ATUALIZACOES
            Text(
                "Atualizacoes",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                modifier = Modifier.padding(start = 6.dp, bottom = 8.dp)
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    ItemDefinicao(
                        icone = Icons.Filled.Refresh,
                        titulo = "Versao atual",
                        subtitulo = "1.1",
                        onClick = { }
                    )
                    Divider(color = Color(0xFFEEEEEE))
                    ItemDefinicao(
                        icone = Icons.Filled.Download,
                        titulo = "Verificar atualizacoes",
                        subtitulo = "Baixar nova versao",
                        onClick = { abrirNavegador(URL_ATUALIZAR) }
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            // SUPORTE
            Text(
                "Suporte",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                modifier = Modifier.padding(start = 6.dp, bottom = 8.dp)
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemDefinicao(
                    icone = Icons.Filled.Notifications,
                    titulo = "Falar com suporte",
                    subtitulo = "Atendimento via WhatsApp",
                    onClick = {
                        val url = "https://wa.me/$WHATSAPP_COMERCIAL?text=Ola!%20Preciso%20de%20ajuda%20com%20o%20app%20BissauLife."
                        abrirNavegador(url)
                    }
                )
            }

            Spacer(Modifier.height(30.dp))

            Text(
                "BissauLife v1.1",
                fontSize = 11.sp,
                color = Color.LightGray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
fun ItemDefinicao(
    icone: ImageVector,
    titulo: String,
    subtitulo: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
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
        Icon(
            Icons.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(14.dp)
        )
    }
}

package com.bissaulife.app.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Dados bancarios
private const val BANCO_NOME = "Banque Atlantique"
private const val BANCO_TITULAR = "Bastem Sonino Mbumbe"
private const val BANCO_CONTA = "20082640017"
private const val BANCO_IBAN = "GW19 5010 0120 0826 4001 744"

@Composable
fun BotaoDoacao() {
    val context = LocalContext.current
    var mostrarModalBanco by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFFE91E63), Color(0xFFC2185B))
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("❤️", fontSize = 40.sp)
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Apoie o projeto",
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Ajude a manter o BissauLife gratuito e a melhora-lo",
                            color = Color.White.copy(alpha = 0.95f),
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Botao de Doacao
                Button(
                    onClick = { mostrarModalBanco = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("💰", fontSize = 20.sp)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Faca uma doacao",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF003087)
                    )
                }
            }
        }
    }

    // Modal com dados bancarios
    if (mostrarModalBanco) {
        AlertDialog(
            onDismissRequest = { mostrarModalBanco = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("🏦", fontSize = 26.sp)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Transferencia Bancaria",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "Use os dados abaixo para fazer sua doacao via transferencia:",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )

                    Spacer(Modifier.height(16.dp))

                    ItemDadoBanco("Banco", BANCO_NOME, context)
                    Spacer(Modifier.height(10.dp))
                    ItemDadoBanco("Titular", BANCO_TITULAR, context)
                    Spacer(Modifier.height(10.dp))
                    ItemDadoBanco("Numero da Conta", BANCO_CONTA, context)
                    Spacer(Modifier.height(10.dp))
                    ItemDadoBanco("IBAN", BANCO_IBAN, context)

                    Spacer(Modifier.height(16.dp))

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Row(modifier = Modifier.padding(12.dp)) {
                            Text("💡", fontSize = 18.sp)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "Toque em qualquer dado para copiar.",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { mostrarModalBanco = false }) {
                    Text("Fechar", color = Color(0xFFE91E63), fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}

@Composable
fun ItemDadoBanco(titulo: String, valor: String, context: Context) {
    var copiado by remember { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText(titulo, valor)
                clipboard.setPrimaryClip(clip)
                copiado = true
            }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(titulo, fontSize = 11.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(3.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(valor, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
                if (copiado) {
                    Text("✓", fontSize = 14.sp, color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold)
                } else {
                    Text("📋", fontSize = 14.sp)
                }
            }
        }
    }

    LaunchedEffect(copiado) {
        if (copiado) {
            kotlinx.coroutines.delay(2000)
            copiado = false
        }
    }
}

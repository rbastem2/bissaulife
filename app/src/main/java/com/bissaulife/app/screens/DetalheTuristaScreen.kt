package com.bissaulife.app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.data.LocalTurista
import com.bissaulife.app.theme.BissauGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheTuristaScreen(
    local: LocalTurista,
    onVoltar: () -> Unit
) {
    val context = LocalContext.current
    val icone = iconePara(local.categoria)

    fun abrirTelefone() {
        if (local.telefone.isBlank()) return
        val numero = local.telefone.replace(" ", "").replace("-", "")
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$numero"))
            context.startActivity(intent)
        } catch (e: Exception) {
        }
    }

    fun abrirMaps() {
        val endereco = local.endereco
        try {
            val uri = Uri.parse("geo:0,0?q=${Uri.encode(endereco)}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            context.startActivity(intent)
        } catch (e: Exception) {
            try {
                val uri = Uri.parse("geo:0,0?q=${Uri.encode(endereco)}")
                context.startActivity(Intent(Intent.ACTION_VIEW, uri))
            } catch (e2: Exception) {
                val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(endereco)}")
                context.startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
    }

    fun compartilhar() {
        val texto = "${icone} ${local.nome}\n" +
                "📍 ${local.endereco}\n" +
                (if (local.telefone.isNotBlank()) "📞 ${local.telefone}\n" else "") +
                "\nVia BissauLife - o guia da Guine-Bissau"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, texto)
        }
        context.startActivity(Intent.createChooser(intent, "Compartilhar"))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            // Cabecalho com icone grande
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF003366), Color(0xFF00A86B))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(icone, fontSize = 60.sp)
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(
                        local.categoria.uppercase(),
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    local.nome,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(Modifier.height(16.dp))

                // Card de horario
                if (local.horario.isNotBlank()) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FBF5)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("🕐", fontSize = 24.sp)
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text("Horario", fontSize = 11.sp, color = Color.Gray)
                                Text(
                                    local.horario,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = BissauGreen
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }

                // Sobre
                if (local.descricao.isNotBlank()) {
                    Text("Sobre", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Spacer(Modifier.height(8.dp))
                    Text(
                        local.descricao,
                        fontSize = 14.sp,
                        color = Color(0xFF444444),
                        lineHeight = 20.sp
                    )
                    Spacer(Modifier.height(20.dp))
                }

                // Telefone
                if (local.telefone.isNotBlank()) {
                    Text("Telefone", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Spacer(Modifier.height(8.dp))
                    Text(local.telefone, fontSize = 14.sp, color = Color(0xFF444444))
                    Spacer(Modifier.height(20.dp))
                }

                // Endereco
                if (local.endereco.isNotBlank()) {
                    Text("Endereco", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Spacer(Modifier.height(8.dp))
                    Text(local.endereco, fontSize = 14.sp, color = Color(0xFF444444))
                    Spacer(Modifier.height(20.dp))
                }

                // Dica para turistas
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text("💡", fontSize = 22.sp)
                        Spacer(Modifier.width(10.dp))
                        Text(
                            "Dica: leve sempre dinheiro em FCFA. Muitos lugares nao aceitam cartao.",
                            fontSize = 12.sp,
                            color = Color(0xFF795548),
                            lineHeight = 17.sp
                        )
                    }
                }

                Spacer(Modifier.height(110.dp))
            }
        }

        // Botao voltar
        IconButton(
            onClick = onVoltar,
            modifier = Modifier
                .padding(16.dp)
                .size(44.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.9f))
                .align(Alignment.TopStart)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
        }

        // Botao compartilhar
        IconButton(
            onClick = { compartilhar() },
            modifier = Modifier
                .padding(16.dp)
                .size(44.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.9f))
                .align(Alignment.TopEnd)
        ) {
            Icon(Icons.Filled.Share, contentDescription = "Compartilhar", tint = Color.Black)
        }

        // Botoes inferiores
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (local.telefone.isNotBlank()) {
                    Button(
                        onClick = { abrirTelefone() },
                        modifier = Modifier.weight(1f).height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Icon(Icons.Filled.Phone, null, tint = Color.White, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Ligar", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                Button(
                    onClick = { abrirMaps() },
                    modifier = Modifier.weight(1f).height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Icon(Icons.Filled.LocationOn, null, tint = Color.White, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Como chegar", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

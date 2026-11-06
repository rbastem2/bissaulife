package com.bissaulife.app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.R
import com.bissaulife.app.theme.BissauGreen

private const val WHATSAPP_COMERCIAL = "245955572393"
private const val EMAIL_ADMIN = "bissaulife@gmail.com"

@Composable
fun PerfilScreen(
    estaLogado: Boolean,
    emailUsuario: String,
    emailVerificado: Boolean,
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit,
    onAbrirPlanos: () -> Unit,
    onAbrirIdiomas: () -> Unit,
    onAbrirSobre: () -> Unit,
    onAbrirDefinicoes: () -> Unit,
    onAbrirLogin: () -> Unit,
    onAbrirCadastroNegocio: () -> Unit,
    onAbrirAdmin: () -> Unit,
    onAbrirVerificarEmail: () -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val ehAdmin = estaLogado && emailUsuario.equals(EMAIL_ADMIN, ignoreCase = true)

    fun abrirWhatsAppComercial() {
        val mensagem = "Olá! Vim pelo app BissauLife e quero anunciar minha loja."
        val url = "https://wa.me/$WHATSAPP_COMERCIAL?text=${Uri.encode(mensagem)}"
        try { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) } catch (e: Exception) {}
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).verticalScroll(rememberScrollState())) {
        Box(modifier = Modifier.fillMaxWidth().background(Brush.verticalGradient(colors = listOf(Color(0xFF003366), Color(0xFF00509E)))).padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(72.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                    Icon(Icons.Filled.Person, contentDescription = null, tint = Color(0xFF2196F3), modifier = Modifier.size(48.dp))
                }
                Spacer(Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(if (estaLogado) "Meu perfil" else "Visitante", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        if (ehAdmin) {
                            Spacer(Modifier.width(8.dp))
                            Surface(shape = RoundedCornerShape(6.dp), color = Color(0xFFFFC107)) {
                                Text("ADMIN", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 9.sp, color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    Text(if (estaLogado) emailUsuario else "Toque em entrar para acessar", color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp, maxLines = 1)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        if (estaLogado && !emailVerificado) {
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).clickable { onAbrirVerificarEmail() }) {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("📧", fontSize = 28.sp)
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Confirme seu email", color = Color(0xFFE65100), fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(3.dp))
                        Text("Toque aqui para verificar", color = Color(0xFFE65100).copy(alpha = 0.85f), fontSize = 12.sp)
                    }
                    Icon(Icons.Filled.ArrowForwardIos, contentDescription = null, tint = Color(0xFFE65100), modifier = Modifier.size(16.dp))
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        if (!estaLogado) {
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = BissauGreen), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).clickable { onAbrirLogin() }) {
                Row(modifier = Modifier.fillMaxWidth().padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Login, null, tint = Color.White, modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Entrar ou criar conta", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text("Para anunciar seu negócio", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
                    }
                    Icon(Icons.Filled.ArrowForwardIos, null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        if (ehAdmin) {
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF003366)), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).clickable { onAbrirAdmin() }) {
                Row(modifier = Modifier.fillMaxWidth().padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("🔐", fontSize = 28.sp)
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Painel Admin", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text("Aprovar negócios cadastrados", color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                    }
                    Icon(Icons.Filled.ArrowForwardIos, null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.Transparent), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).clickable { onAbrirPlanos() }) {
            Box(modifier = Modifier.fillMaxWidth().background(Brush.horizontalGradient(colors = listOf(Color(0xFFFFC107), Color(0xFFFF9800)))).padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("⭐", fontSize = 40.sp)
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Torne-se Destaque", color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                        Text("Apareça no topo da busca", color = Color.White.copy(alpha = 0.95f), fontSize = 12.sp)
                        Spacer(Modifier.height(4.dp))
                        Text("A partir de 8.000 FCFA/semestre", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Icon(Icons.Filled.ArrowForwardIos, null, tint = Color.White, modifier = Modifier.size(18.dp))
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Column {
                ItemMenu(Icons.Filled.Store, "Anunciar minha loja", if (estaLogado) "Cadastrar novo negócio" else "Faça login primeiro", { if (estaLogado) onAbrirCadastroNegocio() else onAbrirLogin() })
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                ItemMenu(Icons.Filled.BusinessCenter, "Área do comerciante", "Fale com nosso comercial", { abrirWhatsAppComercial() })
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                ItemMenu(Icons.Filled.Language, "Idiomas", "Português", onAbrirIdiomas)
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                ItemMenu(Icons.Filled.Info, "Sobre o BissauLife", "Versão 1.5", onAbrirSobre)
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                ItemMenu(Icons.Filled.Settings, "Definições", "Notificações, privacidade, modo escuro", onAbrirDefinicoes)
            }
        }

        Spacer(Modifier.height(16.dp))
        BotaoDoacao()

        if (estaLogado) {
            Spacer(Modifier.height(16.dp))
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                ItemMenu(Icons.Filled.Logout, "Sair da conta", "Desconectar do aplicativo", onLogout, corIcone = Color(0xFFE53935))
            }
        }

        Spacer(Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(160.dp).clip(RoundedCornerShape(20.dp))) {
            Image(painter = painterResource(id = R.drawable.banner_perfil), contentDescription = "Juntos fazemos Bissau crescer", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            Box(modifier = Modifier.fillMaxSize().background(Brush.horizontalGradient(colors = listOf(Color.Black.copy(alpha = 0.65f), Color.Black.copy(alpha = 0.15f)))))
            Column(modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.Center) {
                Text("Juntos fazemos", color = Color.White, fontSize = 14.sp)
                Text("Bissau crescer!", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(6.dp))
                Text("💚❤️💛", fontSize = 18.sp)
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun ItemMenu(icone: ImageVector, titulo: String, subtitulo: String, onClick: () -> Unit, corIcone: Color = BissauGreen) {
    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icone, contentDescription = null, tint = corIcone, modifier = Modifier.size(24.dp))
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(titulo, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
            Text(subtitulo, fontSize = 12.sp, color = Color.Gray)
        }
        Icon(Icons.Filled.ArrowForwardIos, null, tint = Color.LightGray, modifier = Modifier.size(14.dp))
    }
}

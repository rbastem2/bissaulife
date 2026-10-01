package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.data.AuthRepository
import com.bissaulife.app.theme.BissauGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificarEmailScreen(
    onVoltar: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val authRepo = remember { AuthRepository() }

    val emailUsuario = authRepo.emailUsuario()
    var carregando by remember { mutableStateOf(false) }
    var mensagem by remember { mutableStateOf("") }
    var sucesso by remember { mutableStateOf(false) }

    fun reenviarEmail() {
        carregando = true
        mensagem = ""
        scope.launch {
            val r = authRepo.reenviarVerificacao()
            carregando = false
            if (r.isSuccess) {
                sucesso = true
                mensagem = "Email reenviado! Verifique sua caixa de entrada."
            } else {
                sucesso = false
                mensagem = "Erro ao reenviar. Tente novamente mais tarde."
            }
        }
    }

    fun verificarAgora() {
        carregando = true
        mensagem = ""
        scope.launch {
            authRepo.recarregarUsuario()
            carregando = false
            if (authRepo.emailVerificado()) {
                sucesso = true
                mensagem = "Email verificado com sucesso!"
                // Espera 1 segundo e volta
                kotlinx.coroutines.delay(1500)
                onVoltar()
            } else {
                sucesso = false
                mensagem = "Email ainda nao verificado. Verifique sua caixa de entrada."
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Verificar Email", fontWeight = FontWeight.Bold, fontSize = 19.sp)
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icone grande
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFF3E0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = null,
                    tint = Color(0xFFFF9800),
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(
                "Confirme seu email",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(10.dp))

            Text(
                "Enviamos um link de verificacao para:",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(6.dp))

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFF0FBF5)
            ) {
                Text(
                    emailUsuario,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BissauGreen
                )
            }

            Spacer(Modifier.height(20.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        "Como verificar?",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(Modifier.height(12.dp))
                    PassoVerificacao("1", "Abra o app de email no seu celular")
                    PassoVerificacao("2", "Procure o email do BissauLife")
                    PassoVerificacao("3", "Toque no link de confirmacao")
                    PassoVerificacao("4", "Volte aqui e toque em \"Ja verifiquei\"")
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Nao achou o email? Verifique a caixa de SPAM.",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        lineHeight = 17.sp
                    )
                }
            }

            if (mensagem.isNotEmpty()) {
                Spacer(Modifier.height(16.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (sucesso) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            if (sucesso) "✅" else "⚠️",
                            fontSize = 18.sp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            mensagem,
                            fontSize = 12.sp,
                            color = if (sucesso) Color(0xFF2E7D32) else Color(0xFFC62828),
                            lineHeight = 17.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Botao "Ja verifiquei"
            Button(
                onClick = { verificarAgora() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                shape = RoundedCornerShape(26.dp),
                enabled = !carregando
            ) {
                if (carregando) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(22.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        "Ja verifiquei meu email",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Botao reenviar
            OutlinedButton(
                onClick = { reenviarEmail() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp),
                enabled = !carregando
            ) {
                Text(
                    "Reenviar email de verificacao",
                    fontSize = 14.sp,
                    color = BissauGreen,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun PassoVerificacao(numero: String, texto: String) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(BissauGreen.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                numero,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = BissauGreen
            )
        }
        Spacer(Modifier.width(10.dp))
        Text(
            texto,
            fontSize = 13.sp,
            color = Color(0xFF333333)
        )
    }
}

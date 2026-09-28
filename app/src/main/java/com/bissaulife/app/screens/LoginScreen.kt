package com.bissaulife.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.R
import com.bissaulife.app.data.AuthRepository
import com.bissaulife.app.theme.BissauGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onVoltar: () -> Unit,
    onLoginSucesso: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val authRepo = remember { AuthRepository() }

    var modoCadastro by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    var carregando by remember { mutableStateOf(false) }
    var erro by remember { mutableStateOf("") }

    fun executar() {
        erro = ""
        if (email.isBlank() || senha.isBlank()) {
            erro = "Preencha email e senha"
            return
        }
        if (senha.length < 6) {
            erro = "Senha precisa ter ao menos 6 caracteres"
            return
        }

        carregando = true
        scope.launch {
            val resultado = if (modoCadastro) {
                authRepo.cadastrar(email.trim(), senha)
            } else {
                authRepo.login(email.trim(), senha)
            }
            carregando = false
            if (resultado.isSuccess) {
                onLoginSucesso()
            } else {
                erro = traduzirErro(resultado.exceptionOrNull()?.message ?: "Erro desconhecido")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (modoCadastro) "Criar conta" else "Entrar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
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
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "BissauLife",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(24.dp))
            )

            Spacer(Modifier.height(20.dp))

            Text(
                if (modoCadastro) "Crie sua conta" else "Bem-vindo de volta",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(Modifier.height(6.dp))
            Text(
                if (modoCadastro)
                    "Cadastre-se para anunciar seu negocio"
                else
                    "Entre para gerenciar seus anuncios",
                fontSize = 13.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(28.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it; erro = "" },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Filled.Email, null, tint = BissauGreen) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it; erro = "" },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Senha") },
                leadingIcon = { Icon(Icons.Filled.Lock, null, tint = BissauGreen) },
                trailingIcon = {
                    IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                        Icon(
                            if (senhaVisivel) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            null,
                            tint = Color.Gray
                        )
                    }
                },
                visualTransformation = if (senhaVisivel)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            if (erro.isNotEmpty()) {
                Spacer(Modifier.height(12.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFFFEBEE),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        erro,
                        modifier = Modifier.padding(12.dp),
                        fontSize = 12.sp,
                        color = Color(0xFFC62828)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { executar() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                shape = RoundedCornerShape(27.dp),
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
                        if (modoCadastro) "Criar conta" else "Entrar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            TextButton(onClick = {
                modoCadastro = !modoCadastro
                erro = ""
            }) {
                Text(
                    if (modoCadastro)
                        "Ja tem conta? Entrar"
                    else
                        "Nao tem conta? Criar agora",
                    color = BissauGreen,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(30.dp))
        }
    }
}

private fun traduzirErro(msg: String): String {
    return when {
        msg.contains("password is invalid", true) ||
        msg.contains("credential is incorrect", true) ->
            "Email ou senha incorretos"
        msg.contains("no user record", true) ->
            "Usuario nao encontrado"
        msg.contains("email address is already in use", true) ->
            "Este email ja esta cadastrado"
        msg.contains("network", true) ->
            "Sem conexao. Verifique sua internet"
        msg.contains("badly formatted", true) ->
            "Email invalido"
        else -> "Erro: $msg"
    }
}

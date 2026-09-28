package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.data.AuthRepository
import com.bissaulife.app.data.Negocio
import com.bissaulife.app.data.NegocioRepository
import com.bissaulife.app.theme.BissauGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroNegocioScreen(
    onVoltar: () -> Unit,
    onSucesso: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val authRepo = remember { AuthRepository() }
    val negocioRepo = remember { NegocioRepository() }

    // Formulario
    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Gastronomia") }
    var subcategoria by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var whatsapp by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var imagemUrl by remember { mutableStateOf("") }

    var carregando by remember { mutableStateOf(false) }
    var erro by remember { mutableStateOf("") }
    var mostrarSucesso by remember { mutableStateOf(false) }

    val categorias = listOf("Gastronomia", "Moda", "Viagens", "Beleza")
    var menuCategoriaAberto by remember { mutableStateOf(false) }

    fun validar(): String? {
        if (nome.isBlank()) return "Preencha o nome do negocio"
        if (subcategoria.isBlank()) return "Preencha a subcategoria"
        if (descricao.isBlank()) return "Preencha a descricao"
        if (telefone.isBlank()) return "Preencha o telefone"
        if (endereco.isBlank()) return "Preencha o endereco"
        return null
    }

    fun enviar() {
        val erroValidacao = validar()
        if (erroValidacao != null) {
            erro = erroValidacao
            return
        }

        carregando = true
        erro = ""
        scope.launch {
            val negocio = Negocio(
                nome = nome.trim(),
                categoria = categoria,
                subcategoria = subcategoria.trim(),
                descricao = descricao.trim(),
                telefone = telefone.trim(),
                whatsapp = whatsapp.trim(),
                endereco = endereco.trim(),
                preco = preco.trim(),
                imagemUrl = imagemUrl.trim(),
                donoNome = "",
                donoEmail = authRepo.emailUsuario()
            )
            val resultado = negocioRepo.cadastrar(negocio)
            carregando = false
            if (resultado.isSuccess) {
                mostrarSucesso = true
            } else {
                erro = "Erro ao enviar: ${resultado.exceptionOrNull()?.message ?: "tente novamente"}"
            }
        }
    }

    // Dialog de sucesso
    if (mostrarSucesso) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text("Cadastro enviado!", fontWeight = FontWeight.Bold, color = BissauGreen)
            },
            text = {
                Text(
                    "Seu negocio foi enviado para analise. Voce sera notificado quando for aprovado.",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    mostrarSucesso = false
                    onSucesso()
                }) {
                    Text("Entendi", color = BissauGreen, fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Anunciar negocio", fontWeight = FontWeight.Bold, fontSize = 19.sp)
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
                .padding(20.dp)
        ) {
            Text(
                "Preencha os dados do seu negocio",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(20.dp))

            // NOME
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it; erro = "" },
                label = { Text("Nome do negocio *") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(Modifier.height(12.dp))

            // CATEGORIA
            ExposedDropdownMenuBox(
                expanded = menuCategoriaAberto,
                onExpandedChange = { menuCategoriaAberto = it }
            ) {
                OutlinedTextField(
                    value = categoria,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Categoria *") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = menuCategoriaAberto) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    shape = RoundedCornerShape(12.dp)
                )
                ExposedDropdownMenu(
                    expanded = menuCategoriaAberto,
                    onDismissRequest = { menuCategoriaAberto = false }
                ) {
                    categorias.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat) },
                            onClick = {
                                categoria = cat
                                menuCategoriaAberto = false
                            }
                        )
                    }
                }
            }
            Spacer(Modifier.height(12.dp))

            // SUBCATEGORIA
            OutlinedTextField(
                value = subcategoria,
                onValueChange = { subcategoria = it; erro = "" },
                label = { Text("Subcategoria * (ex: Restaurante, Hotel)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(Modifier.height(12.dp))

            // DESCRICAO
            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it; erro = "" },
                label = { Text("Descricao *") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(12.dp),
                maxLines = 5
            )
            Spacer(Modifier.height(12.dp))

            // TELEFONE
            OutlinedTextField(
                value = telefone,
                onValueChange = { telefone = it; erro = "" },
                label = { Text("Telefone *") },
                placeholder = { Text("+245 955 XXX XXX") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(Modifier.height(12.dp))

            // WHATSAPP
            OutlinedTextField(
                value = whatsapp,
                onValueChange = { whatsapp = it },
                label = { Text("WhatsApp (opcional)") },
                placeholder = { Text("+245 955 XXX XXX") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(Modifier.height(12.dp))

            // ENDERECO
            OutlinedTextField(
                value = endereco,
                onValueChange = { endereco = it; erro = "" },
                label = { Text("Endereco *") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(Modifier.height(12.dp))

            // PRECO
            OutlinedTextField(
                value = preco,
                onValueChange = { preco = it },
                label = { Text("Preco medio (opcional)") },
                placeholder = { Text("Ex: 5.000 FCFA") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(Modifier.height(12.dp))

            // IMAGEM URL
            OutlinedTextField(
                value = imagemUrl,
                onValueChange = { imagemUrl = it },
                label = { Text("Link da foto (opcional)") },
                placeholder = { Text("Cole o link de uma imagem") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Dica: use links de fotos publicas do Facebook, Instagram ou Google Drive. Nossa equipe pode ajustar depois.",
                fontSize = 11.sp,
                color = Color.Gray,
                lineHeight = 16.sp
            )

            if (erro.isNotEmpty()) {
                Spacer(Modifier.height(14.dp))
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

            // BOTAO ENVIAR
            Button(
                onClick = { enviar() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                shape = RoundedCornerShape(28.dp),
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
                        "Enviar para analise",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(Modifier.height(12.dp))
            Text(
                "Ao enviar, seu negocio sera analisado pela equipe BissauLife e voce sera notificado quando for aprovado.",
                fontSize = 11.sp,
                color = Color.Gray,
                lineHeight = 16.sp
            )

            Spacer(Modifier.height(30.dp))
        }
    }
}

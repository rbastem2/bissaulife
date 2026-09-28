package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bissaulife.app.data.Negocio
import com.bissaulife.app.data.NegocioRepository
import com.bissaulife.app.theme.BissauGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(onVoltar: () -> Unit) {
    val scope = rememberCoroutineScope()
    val repo = remember { NegocioRepository() }

    var abaAtual by remember { mutableIntStateOf(0) }
    var todos by remember { mutableStateOf<List<Negocio>>(emptyList()) }
    var carregando by remember { mutableStateOf(true) }
    var erro by remember { mutableStateOf("") }
    var versao by remember { mutableIntStateOf(0) }

    LaunchedEffect(versao) {
        carregando = true
        erro = ""
        val r = repo.listarTodos()
        if (r.isSuccess) {
            todos = r.getOrDefault(emptyList())
        } else {
            erro = "Erro ao carregar: ${r.exceptionOrNull()?.message ?: "verifique conexao"}"
        }
        carregando = false
    }

    val pendentes = todos.filter { it.status == "pendente" }
    val aprovados = todos.filter { it.status == "aprovado" }

    fun aprovar(id: String) {
        scope.launch {
            repo.aprovar(id)
            versao++
        }
    }

    fun rejeitar(id: String) {
        scope.launch {
            repo.rejeitar(id)
            versao++
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Painel Admin", fontWeight = FontWeight.Bold, fontSize = 19.sp)
                },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { versao++ }) {
                        Text("🔄", fontSize = 18.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF003366),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F5F5))
        ) {
            TabRow(
                selectedTabIndex = abaAtual,
                containerColor = Color.White,
                contentColor = BissauGreen
            ) {
                Tab(
                    selected = abaAtual == 0,
                    onClick = { abaAtual = 0 },
                    text = {
                        Text("Pendentes (${pendentes.size})", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                )
                Tab(
                    selected = abaAtual == 1,
                    onClick = { abaAtual = 1 },
                    text = {
                        Text("Aprovados (${aprovados.size})", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                )
            }

            if (carregando) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = BissauGreen)
                }
            } else if (erro.isNotEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("⚠️", fontSize = 40.sp)
                        Spacer(Modifier.height(12.dp))
                        Text(erro, color = Color.Red, textAlign = TextAlign.Center, fontSize = 13.sp)
                    }
                }
            } else {
                val lista = if (abaAtual == 0) pendentes else aprovados
                if (lista.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                if (abaAtual == 0) "🎉" else "📭",
                                fontSize = 60.sp
                            )
                            Spacer(Modifier.height(12.dp))
                            Text(
                                if (abaAtual == 0) "Nenhum negocio pendente"
                                else "Nenhum negocio aprovado ainda",
                                fontSize = 15.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(14.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(lista) { negocio ->
                            CardAdmin(
                                negocio = negocio,
                                mostrarBotoes = abaAtual == 0,
                                onAprovar = { aprovar(negocio.id) },
                                onRejeitar = { rejeitar(negocio.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardAdmin(
    negocio: Negocio,
    mostrarBotoes: Boolean,
    onAprovar: () -> Unit,
    onRejeitar: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row {
                if (negocio.imagemUrl.isNotBlank()) {
                    AsyncImage(
                        model = negocio.imagemUrl,
                        contentDescription = negocio.nome,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(Modifier.width(12.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        negocio.nome.ifBlank { "(sem nome)" },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "${negocio.categoria} • ${negocio.subcategoria}",
                        fontSize = 12.sp,
                        color = BissauGreen,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        negocio.descricao,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        maxLines = 2
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Divider(color = Color(0xFFEEEEEE))
            Spacer(Modifier.height(10.dp))

            InfoLinha("📞", negocio.telefone)
            if (negocio.whatsapp.isNotBlank()) InfoLinha("💬", negocio.whatsapp)
            InfoLinha("📍", negocio.endereco)
            if (negocio.preco.isNotBlank()) InfoLinha("💰", negocio.preco)
            InfoLinha("📧", negocio.donoEmail)

            if (mostrarBotoes) {
                Spacer(Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onRejeitar,
                        modifier = Modifier.weight(1f).height(44.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
                        shape = RoundedCornerShape(22.dp)
                    ) {
                        Icon(Icons.Filled.Close, null, tint = Color.White, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("Rejeitar", fontSize = 13.sp)
                    }
                    Button(
                        onClick = onAprovar,
                        modifier = Modifier.weight(1f).height(44.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                        shape = RoundedCornerShape(22.dp)
                    ) {
                        Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("Aprovar", fontSize = 13.sp)
                    }
                }
            } else {
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "Aprovado",
                        fontSize = 12.sp,
                        color = BissauGreen,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun InfoLinha(icone: String, valor: String) {
    if (valor.isBlank()) return
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(icone, fontSize = 12.sp)
        Spacer(Modifier.width(6.dp))
        Text(valor, fontSize = 12.sp, color = Color(0xFF444444))
    }
}

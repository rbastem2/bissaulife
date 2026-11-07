package com.bissaulife.app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.data.Negocio
import com.bissaulife.app.data.NegocioRepository
import com.bissaulife.app.theme.BissauGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(onVoltar: () -> Unit) {
    val scope = rememberCoroutineScope()
    val repo = remember { NegocioRepository() }
    val context = LocalContext.current

    var abaAtual by remember { mutableIntStateOf(0) }
    var todos by remember { mutableStateOf<List<Negocio>>(emptyList()) }
    var carregando by remember { mutableStateOf(true) }
    var erro by remember { mutableStateOf("") }
    var versao by remember { mutableIntStateOf(0) }
    var query by remember { mutableStateOf("") }

    var negocioParaDeletar by remember { mutableStateOf<Negocio?>(null) }
    var negocioParaEditar by remember { mutableStateOf<Negocio?>(null) }

    LaunchedEffect(versao) {
        carregando = true
        val r = repo.listarTodos()
        if (r.isSuccess) todos = r.getOrDefault(emptyList())
        else erro = "Erro ao carregar."
        carregando = false
    }

    val filtrados = if (query.isBlank()) todos else todos.filter { 
        it.nome.contains(query, ignoreCase = true) || it.donoEmail.contains(query, ignoreCase = true) 
    }

    val pendentes = filtrados.filter { it.status == "pendente" }
    val aprovados = filtrados.filter { it.status == "aprovado" }
    val arquivados = filtrados.filter { it.status == "arquivado" }

    fun aprovar(id: String) { scope.launch { repo.aprovar(id); versao++ } }
    fun rejeitar(id: String) { scope.launch { repo.rejeitar(id); versao++ } }
    fun arquivar(id: String) { scope.launch { repo.arquivar(id); versao++ } }
    fun restaurar(id: String) { scope.launch { repo.restaurar(id); versao++ } }
    fun destacar(n: Negocio) { scope.launch { repo.definirDestaque(n.id, !n.destaque, if (!n.destaque) "manual" else ""); versao++ } }
    
    fun abrirWhatsApp(negocio: Negocio, aprovado: Boolean) {
        val numero = negocio.telefone.replace(" ", "").replace("-", "").replace("+", "")
        if (numero.isBlank()) return
        val texto = if (aprovado) "Ola! O seu negocio '${negocio.nome}' foi APROVADO no BissauLife. Ja esta disponivel no app!" else "Ola! Precisamos de mais informacoes sobre o seu negocio '${negocio.nome}' para aprovar no BissauLife."
        try { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$numero?text=${Uri.encode(texto)}"))) } catch (e: Exception) {}
    }

    // DIALOGO DE DELECAO
    if (negocioParaDeletar != null) {
        AlertDialog(
            onDismissRequest = { negocioParaDeletar = null },
            title = { Text("Eliminar definitivamente?") },
            text = { Text("Apagar '${negocioParaDeletar?.nome}'? Nao pode ser desfeito.") },
            confirmButton = { TextButton(onClick = { negocioParaDeletar?.let { scope.launch { repo.deletar(it.id); versao++ } }; negocioParaDeletar = null }) { Text("SIM, ELIMINAR", color = Color.Red, fontWeight = FontWeight.Bold) } },
            dismissButton = { TextButton(onClick = { negocioParaDeletar = null }) { Text("Cancelar") } }
        )
    }

    // DIALOGO DE EDICAO
    if (negocioParaEditar != null) {
        EditNegocioDialog(
            negocioOriginal = negocioParaEditar!!,
            onDismiss = { negocioParaEditar = null },
            onSave = { atualizado ->
                scope.launch {
                    repo.atualizar(atualizado)
                    negocioParaEditar = null
                    versao++
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Painel Admin", fontWeight = FontWeight.Bold, fontSize = 19.sp) },
                navigationIcon = { IconButton(onClick = onVoltar) { Icon(Icons.Filled.ArrowBack, null) } },
                actions = { IconButton(onClick = { versao++ }) { Text("🔄", fontSize = 18.sp) } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF003366), titleContentColor = Color.White, navigationIconContentColor = Color.White)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).background(MaterialTheme.colorScheme.background)) {
            OutlinedTextField(
                value = query, onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                placeholder = { Text("Buscar loja...", fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.Gray) },
                shape = RoundedCornerShape(28.dp), singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White)
            )

            TabRow(selectedTabIndex = abaAtual, containerColor = MaterialTheme.colorScheme.surface, contentColor = BissauGreen) {
                Tab(selected = abaAtual == 0, onClick = { abaAtual = 0 }, text = { Text("Pend. (${pendentes.size})", fontSize = 11.sp) })
                Tab(selected = abaAtual == 1, onClick = { abaAtual = 1 }, text = { Text("Aprov. (${aprovados.size})", fontSize = 11.sp) })
                Tab(selected = abaAtual == 2, onClick = { abaAtual = 2 }, text = { Text("Arq. (${arquivados.size})", fontSize = 11.sp) })
            }

            if (carregando) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator(color = BissauGreen) }
            } else {
                val listaAtual = when (abaAtual) { 0 -> pendentes; 1 -> aprovados; else -> arquivados }
                if (listaAtual.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Nenhum item.", color = Color.Gray)
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(listaAtual) { n ->
                            CardAdmin(
                                negocio = n, abaAtual = abaAtual,
                                onAprovar = { aprovar(n.id) }, onRejeitar = { rejeitar(n.id) },
                                onArquivar = { arquivar(n.id) }, onRestaurar = { restaurar(n.id) },
                                onDeletar = { negocioParaDeletar = n }, onDestacar = { destacar(n) },
                                onWhatsApp = { abrirWhatsApp(n, abaAtual == 1) },
                                onEditar = { negocioParaEditar = n } // AQUI ABRE A EDICAO
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
    negocio: Negocio, abaAtual: Int,
    onAprovar: () -> Unit, onRejeitar: () -> Unit, onArquivar: () -> Unit,
    onRestaurar: () -> Unit, onDeletar: () -> Unit, onDestacar: () -> Unit,
    onWhatsApp: () -> Unit, onEditar: () -> Unit
) {
    Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (negocio.destaque) {
                    Surface(shape = RoundedCornerShape(4.dp), color = Color(0xFFFFC107)) {
                        Text("⭐ DESTAQUE", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Spacer(Modifier.width(6.dp))
                }
                Text(negocio.nome.ifBlank { "(sem nome)" }, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.weight(1f))
                IconButton(onClick = onEditar) { Icon(Icons.Filled.Edit, "Editar", tint = BissauGreen) }
            }
            
            Text("${negocio.categoria} • ${negocio.subcategoria}", fontSize = 12.sp, color = BissauGreen, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(10.dp)); Divider(color = Color(0xFFEEEEEE)); Spacer(Modifier.height(10.dp))
            InfoLinha("📞", negocio.telefone); InfoLinha("📍", negocio.endereco); InfoLinha("📧", negocio.donoEmail)
            Spacer(Modifier.height(14.dp))

            when (abaAtual) {
                0 -> Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(onClick = onRejeitar, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935))) { Text("Rejeitar", fontSize = 13.sp) }
                    Button(onClick = onAprovar, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = BissauGreen)) { Text("Aprovar", fontSize = 13.sp) }
                }
                1 -> Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onWhatsApp) { Text("💬", fontSize = 22.sp) }
                    Button(onClick = onDestacar, colors = ButtonDefaults.buttonColors(containerColor = if (negocio.destaque) Color(0xFFFF9800) else Color.LightGray), shape = RoundedCornerShape(8.dp), contentPadding = PaddingValues(horizontal = 8.dp)) {
                        Text(if (negocio.destaque) "Tirar Destaque" else "Destacar", fontSize = 11.sp)
                    }
                    IconButton(onClick = onArquivar) { Icon(Icons.Filled.Archive, "Arquivar", tint = Color(0xFF607D8B)) }
                    IconButton(onClick = onDeletar) { Icon(Icons.Filled.Delete, "Eliminar", tint = Color.Red) }
                }
                2 -> Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = onRestaurar, colors = ButtonDefaults.buttonColors(containerColor = BissauGreen)) { Text("Restaurar", fontSize = 12.sp) }
                    IconButton(onClick = onDeletar) { Icon(Icons.Filled.Delete, "Eliminar", tint = Color.Red) }
                }
            }
        }
    }
}

// TELA DE EDICAO (DIALOGO)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNegocioDialog(negocioOriginal: Negocio, onDismiss: () -> Unit, onSave: (Negocio) -> Unit) {
    var nome by remember { mutableStateOf(negocioOriginal.nome) }
    var categoria by remember { mutableStateOf(negocioOriginal.categoria) }
    var subcategoria by remember { mutableStateOf(negocioOriginal.subcategoria) }
    var telefone by remember { mutableStateOf(negocioOriginal.telefone) }
    var endereco by remember { mutableStateOf(negocioOriginal.endereco) }
    var preco by remember { mutableStateOf(negocioOriginal.preco) }
    var imagemUrl by remember { mutableStateOf(negocioOriginal.imagemUrl) }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Negocio", fontWeight = FontWeight.Bold) },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxWidth()) {
                OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = categoria, onValueChange = { categoria = it }, label = { Text("Categoria") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = subcategoria, onValueChange = { subcategoria = it }, label = { Text("Subcategoria") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = telefone, onValueChange = { telefone = it }, label = { Text("Telefone") }, modifier = Modifier.fillMaxWidth(), singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = endereco, onValueChange = { endereco = it }, label = { Text("Endereco") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = preco, onValueChange = { preco = it }, label = { Text("Preco") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = imagemUrl, onValueChange = { imagemUrl = it }, label = { Text("Link da Imagem") }, modifier = Modifier.fillMaxWidth(), singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri))
            }
        },
        confirmButton = {
            Button(
                onClick = { 
                    onSave(negocioOriginal.copy(
                        nome = nome, categoria = categoria, subcategoria = subcategoria,
                        telefone = telefone, endereco = endereco, preco = preco, imagemUrl = imagemUrl
                    ))
                },
                colors = ButtonDefaults.buttonColors(containerColor = BissauGreen)
            ) { Text("Salvar Alteracoes") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar", color = Color.Gray) } }
    )
}

@Composable
fun InfoLinha(icone: String, valor: String) {
    if (valor.isBlank()) return
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(icone, fontSize = 12.sp); Spacer(Modifier.width(6.dp)); Text(valor, fontSize = 12.sp, color = Color(0xFF444444))
    }
}

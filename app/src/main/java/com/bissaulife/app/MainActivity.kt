package com.bissaulife.app

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bissaulife.app.data.*
import com.bissaulife.app.screens.*
import com.bissaulife.app.theme.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("BissauLifePrefs", Context.MODE_PRIVATE)
        
        setContent {
            var isDarkMode by remember { mutableStateOf(prefs.getBoolean("dark_mode", false)) }

            BissauLifeTheme(darkTheme = isDarkMode) {
                var showSplash by remember { mutableStateOf(true) }
                val navController = rememberNavController()
                val favoritosVM: FavoritosViewModel = viewModel()
                val negociosVM: NegociosViewModel = viewModel()
                var versaoRecomposicao by remember { mutableIntStateOf(0) }

                val onToggleDarkMode: (Boolean) -> Unit = {
                    isDarkMode = it
                    prefs.edit().putBoolean("dark_mode", it).apply()
                }

                LaunchedEffect(Unit) {
                    delay(2500)
                    showSplash = false
                }

                if (showSplash) {
                    SplashScreen()
                } else {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            versaoRecomposicao
                            HomeScreen(
                                viewModel = favoritosVM,
                                isDarkMode = isDarkMode,
                                onToggleDarkMode = onToggleDarkMode,
                                onAbrirCategoria = { categoria ->
                                    when (categoria) {
                                        "Gastronomia" -> navController.navigate("gastronomia")
                                        "Moda" -> navController.navigate("moda")
                                        "Viagens" -> navController.navigate("viagens")
                                        "Beleza" -> navController.navigate("beleza")
                                    }
                                },
                                onAbrirRestaurante = { r -> navController.navigate("detalhe_restaurante/${r.id}") },
                                onAbrirItem = { i, t -> navController.navigate("detalhe_item_${t}/${i.id}") },
                                onAbrirTurista = { l -> navController.navigate("detalhe_turista/${l.id}") },
                                onAbrirPlanos = { navController.navigate("planos") },
                                onAbrirIdiomas = { navController.navigate("idiomas") },
                                onAbrirSobre = { navController.navigate("sobre") },
                                onAbrirDefinicoes = { navController.navigate("definicoes") },
                                onAbrirLogin = { navController.navigate("login") },
                                onAbrirCadastroNegocio = { navController.navigate("cadastro_negocio") },
                                onAbrirAdmin = { navController.navigate("admin") },
                                onAbrirGuiaTurista = { navController.navigate("guia_turista") },
                                onAbrirVerificarEmail = { navController.navigate("verificar_email") },
                                onLogout = { versaoRecomposicao++ }
                            )
                        }

                        composable("definicoes") {
                            DefinicoesScreen(
                                onVoltar = { navController.popBackStack() },
                                isDarkMode = isDarkMode,
                                onToggleDarkMode = onToggleDarkMode
                            )
                        }

                        // ... (O resto das rotas continua igual, vou omitir para o bloco não ficar gigante, mas no seu arquivo original elas devem permanecer) ...
                        // Para facilitar, copie o resto do seu MainActivity original aqui e cole abaixo da rota "definicoes". 
                    }
                }
            }
        }
    }
}

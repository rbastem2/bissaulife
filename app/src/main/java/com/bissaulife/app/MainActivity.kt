package com.bissaulife.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bissaulife.app.data.Beleza
import com.bissaulife.app.data.Moda
import com.bissaulife.app.data.Restaurantes
import com.bissaulife.app.data.Viagens
import com.bissaulife.app.screens.BelezaScreen
import com.bissaulife.app.screens.DetalheItemScreen
import com.bissaulife.app.screens.DetalheScreen
import com.bissaulife.app.screens.GastronomiaScreen
import com.bissaulife.app.screens.HomeScreen
import com.bissaulife.app.screens.ModaScreen
import com.bissaulife.app.screens.SplashScreen
import com.bissaulife.app.screens.ViagensScreen
import com.bissaulife.app.theme.BissauLifeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BissauLifeTheme {
                var showSplash by remember { mutableStateOf(true) }
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    delay(2500)
                    showSplash = false
                }

                if (showSplash) {
                    SplashScreen()
                } else {
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        // HOME
                        composable("home") {
                            HomeScreen(
                                onAbrirCategoria = { categoria ->
                                    when (categoria) {
                                        "Gastronomia" -> navController.navigate("gastronomia")
                                        "Moda" -> navController.navigate("moda")
                                        "Viagens" -> navController.navigate("viagens")
                                        "Beleza" -> navController.navigate("beleza")
                                    }
                                }
                            )
                        }

                        // GASTRONOMIA
                        composable("gastronomia") {
                            GastronomiaScreen(
                                onVoltar = { navController.popBackStack() },
                                onVerDetalhes = { restaurante ->
                                    navController.navigate("detalhe_restaurante/${restaurante.id}")
                                }
                            )
                        }

                        composable("detalhe_restaurante/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val restaurante = Restaurantes.lista.find { it.id == id }
                                ?: Restaurantes.lista.first()
                            DetalheScreen(
                                restaurante = restaurante,
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        // MODA
                        composable("moda") {
                            ModaScreen(
                                onVoltar = { navController.popBackStack() },
                                onVerDetalhes = { item ->
                                    navController.navigate("detalhe_item/${item.id}")
                                }
                            )
                        }

                        composable("detalhe_item/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val item = Moda.lista.find { it.id == id }
                                ?: Viagens.lista.find { it.id == id }
                                ?: Beleza.lista.find { it.id == id }
                                ?: Moda.lista.first()
                            DetalheItemScreen(
                                item = item,
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        // VIAGENS
                        composable("viagens") {
                            ViagensScreen(
                                onVoltar = { navController.popBackStack() },
                                onVerDetalhes = { item ->
                                    navController.navigate("detalhe_item/${item.id}")
                                }
                            )
                        }

                        // BELEZA
                        composable("beleza") {
                            BelezaScreen(
                                onVoltar = { navController.popBackStack() },
                                onVerDetalhes = { item ->
                                    navController.navigate("detalhe_item/${item.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

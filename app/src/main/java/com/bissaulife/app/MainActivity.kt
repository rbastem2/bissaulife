package com.bissaulife.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bissaulife.app.data.Restaurantes
import com.bissaulife.app.screens.DetalheScreen
import com.bissaulife.app.screens.GastronomiaScreen
import com.bissaulife.app.screens.HomeScreen
import com.bissaulife.app.screens.SplashScreen
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
                        composable("home") {
                            HomeScreen(
                                onAbrirCategoria = { categoria ->
                                    if (categoria == "Gastronomia") {
                                        navController.navigate("gastronomia")
                                    }
                                }
                            )
                        }

                        composable("gastronomia") {
                            GastronomiaScreen(
                                onVoltar = { navController.popBackStack() },
                                onVerDetalhes = { restaurante ->
                                    navController.navigate("detalhe/${restaurante.id}")
                                }
                            )
                        }

                        composable("detalhe/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val restaurante = Restaurantes.lista.find { it.id == id }
                                ?: Restaurantes.lista.first()
                            DetalheScreen(
                                restaurante = restaurante,
                                onVoltar = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

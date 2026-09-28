package com.bissaulife.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bissaulife.app.data.Beleza
import com.bissaulife.app.data.FavoritosViewModel
import com.bissaulife.app.data.Moda
import com.bissaulife.app.data.Restaurantes
import com.bissaulife.app.data.Viagens
import com.bissaulife.app.screens.BelezaScreen
import com.bissaulife.app.screens.CadastroNegocioScreen
import com.bissaulife.app.screens.DefinicoesScreen
import com.bissaulife.app.screens.DetalheItemScreen
import com.bissaulife.app.screens.DetalheScreen
import com.bissaulife.app.screens.GastronomiaScreen
import com.bissaulife.app.screens.HomeScreen
import com.bissaulife.app.screens.IdiomasScreen
import com.bissaulife.app.screens.LoginScreen
import com.bissaulife.app.screens.ModaScreen
import com.bissaulife.app.screens.PlanosScreen
import com.bissaulife.app.screens.SobreScreen
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
                val favoritosVM: FavoritosViewModel = viewModel()
                var versaoRecomposicao by remember { mutableIntStateOf(0) }

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
                            // Forca recomposicao do home quando volta de login
                            versaoRecomposicao
                            HomeScreen(
                                viewModel = favoritosVM,
                                onAbrirCategoria = { categoria ->
                                    when (categoria) {
                                        "Gastronomia" -> navController.navigate("gastronomia")
                                        "Moda" -> navController.navigate("moda")
                                        "Viagens" -> navController.navigate("viagens")
                                        "Beleza" -> navController.navigate("beleza")
                                    }
                                },
                                onAbrirRestaurante = { restaurante ->
                                    navController.navigate("detalhe_restaurante/${restaurante.id}")
                                },
                                onAbrirItem = { item, tipo ->
                                    when (tipo) {
                                        "moda" -> navController.navigate("detalhe_item_moda/${item.id}")
                                        "viagem" -> navController.navigate("detalhe_item_viagem/${item.id}")
                                        "beleza" -> navController.navigate("detalhe_item_beleza/${item.id}")
                                    }
                                },
                                onAbrirPlanos = { navController.navigate("planos") },
                                onAbrirIdiomas = { navController.navigate("idiomas") },
                                onAbrirSobre = { navController.navigate("sobre") },
                                onAbrirDefinicoes = { navController.navigate("definicoes") },
                                onAbrirLogin = { navController.navigate("login") },
                                onAbrirCadastroNegocio = { navController.navigate("cadastro_negocio") },
                                onLogout = { versaoRecomposicao++ }
                            )
                        }

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
                                viewModel = favoritosVM,
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        composable("moda") {
                            ModaScreen(
                                onVoltar = { navController.popBackStack() },
                                onVerDetalhes = { item ->
                                    navController.navigate("detalhe_item_moda/${item.id}")
                                }
                            )
                        }

                        composable("detalhe_item_moda/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val item = Moda.lista.find { it.id == id } ?: Moda.lista.first()
                            DetalheItemScreen(
                                item = item,
                                tipo = "moda",
                                viewModel = favoritosVM,
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        composable("viagens") {
                            ViagensScreen(
                                onVoltar = { navController.popBackStack() },
                                onVerDetalhes = { item ->
                                    navController.navigate("detalhe_item_viagem/${item.id}")
                                }
                            )
                        }

                        composable("detalhe_item_viagem/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val item = Viagens.lista.find { it.id == id } ?: Viagens.lista.first()
                            DetalheItemScreen(
                                item = item,
                                tipo = "viagem",
                                viewModel = favoritosVM,
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        composable("beleza") {
                            BelezaScreen(
                                onVoltar = { navController.popBackStack() },
                                onVerDetalhes = { item ->
                                    navController.navigate("detalhe_item_beleza/${item.id}")
                                }
                            )
                        }

                        composable("detalhe_item_beleza/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val item = Beleza.lista.find { it.id == id } ?: Beleza.lista.first()
                            DetalheItemScreen(
                                item = item,
                                tipo = "beleza",
                                viewModel = favoritosVM,
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        composable("planos") {
                            PlanosScreen(
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        composable("idiomas") {
                            IdiomasScreen(
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        composable("sobre") {
                            SobreScreen(
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        composable("definicoes") {
                            DefinicoesScreen(
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        composable("login") {
                            LoginScreen(
                                onVoltar = { navController.popBackStack() },
                                onLoginSucesso = {
                                    versaoRecomposicao++
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("cadastro_negocio") {
                            CadastroNegocioScreen(
                                onVoltar = { navController.popBackStack() },
                                onSucesso = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

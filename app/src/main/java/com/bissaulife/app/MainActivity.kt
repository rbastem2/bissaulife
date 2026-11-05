package com.bissaulife.app

import android.net.Uri
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
import com.bissaulife.app.data.LocaisTurista
import com.bissaulife.app.data.Moda
import com.bissaulife.app.data.NegociosViewModel
import com.bissaulife.app.data.Restaurantes
import com.bissaulife.app.data.Viagens
import com.bissaulife.app.screens.*
import com.bissaulife.app.theme.BissauBlue
import com.bissaulife.app.theme.BissauGreen
import com.bissaulife.app.theme.BissauLifeTheme
import com.bissaulife.app.theme.BissauOrange
import com.bissaulife.app.theme.BissauPink
import com.bissaulife.app.theme.BissauPurple
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BissauLifeTheme {
                var showSplash by remember { mutableStateOf(true) }
                val navController = rememberNavController()
                val favoritosVM: FavoritosViewModel = viewModel()
                val negociosVM: NegociosViewModel = viewModel()
                var versaoRecomposicao by remember { mutableIntStateOf(0) }

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

                        // ROTA DO MAPA OFFLINE (Recebe o nome e o endereço via URL)
                        composable("mapa_offline/{nome}/{endereco}") { backStackEntry ->
                            val nome = backStackEntry.arguments?.getString("nome") ?: ""
                            val endereco = backStackEntry.arguments?.getString("endereco") ?: ""
                            MapaRotaScreen(
                                nomeDestino = Uri.decode(nome),
                                enderecoDestino = Uri.decode(endereco),
                                onVoltar = { navController.popBackStack() }
                            )
                        }

                        composable("gastronomia") {
                            GastronomiaScreen(negociosVM, { navController.popBackStack() }, { r -> navController.navigate("detalhe_restaurante/${r.id}") }, { n -> navController.navigate("detalhe_negocio/${n.id}") })
                        }

                        composable("detalhe_restaurante/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val restaurante = Restaurantes.lista.find { it.id == id } ?: Restaurantes.lista.first()
                            DetalheScreen(
                                restaurante = restaurante,
                                viewModel = favoritosVM,
                                onVoltar = { navController.popBackStack() },
                                onAbrirMapaOffline = { nome, end -> navController.navigate("mapa_offline/${Uri.encode(nome)}/${Uri.encode(end)}") }
                            )
                        }

                        composable("moda") {
                            ModaScreen(negociosVM, { navController.popBackStack() }, { i -> navController.navigate("detalhe_item_moda/${i.id}") }, { n -> navController.navigate("detalhe_negocio/${n.id}") })
                        }
                        composable("detalhe_item_moda/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val item = Moda.lista.find { it.id == id } ?: Moda.lista.first()
                            DetalheItemScreen(item, "moda", favoritosVM, { navController.popBackStack() }, { nome, end -> navController.navigate("mapa_offline/${Uri.encode(nome)}/${Uri.encode(end)}") })
                        }

                        composable("viagens") {
                            ViagensScreen(negociosVM, { navController.popBackStack() }, { i -> navController.navigate("detalhe_item_viagem/${i.id}") }, { n -> navController.navigate("detalhe_negocio/${n.id}") })
                        }
                        composable("detalhe_item_viagem/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val item = Viagens.lista.find { it.id == id } ?: Viagens.lista.first()
                            DetalheItemScreen(item, "viagem", favoritosVM, { navController.popBackStack() }, { nome, end -> navController.navigate("mapa_offline/${Uri.encode(nome)}/${Uri.encode(end)}") })
                        }

                        composable("beleza") {
                            BelezaScreen(negociosVM, { navController.popBackStack() }, { i -> navController.navigate("detalhe_item_beleza/${i.id}") }, { n -> navController.navigate("detalhe_negocio/${n.id}") })
                        }
                        composable("detalhe_item_beleza/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val item = Beleza.lista.find { it.id == id } ?: Beleza.lista.first()
                            DetalheItemScreen(item, "beleza", favoritosVM, { navController.popBackStack() }, { nome, end -> navController.navigate("mapa_offline/${Uri.encode(nome)}/${Uri.encode(end)}") })
                        }

                        composable("detalhe_negocio/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val negocio = negociosVM.aprovados.find { it.id == id }
                            if (negocio != null) {
                                val cor = when (negocio.categoria.lowercase()) {
                                    "gastronomia" -> BissauOrange; "moda" -> BissauPurple; "viagens" -> BissauBlue; "beleza" -> BissauPink; else -> BissauGreen
                                }
                                DetalheNegocioScreen(negocio, cor, { navController.popBackStack() })
                            } else {
                                LaunchedEffect(Unit) { navController.popBackStack() }
                            }
                        }

                        composable("guia_turista") {
                            GuiaTuristaScreen({ navController.popBackStack() }, { cat -> navController.navigate("lista_turista/$cat") })
                        }
                        composable("lista_turista/{categoria}") { backStackEntry ->
                            val cat = backStackEntry.arguments?.getString("categoria") ?: ""
                            ListaTuristaScreen(cat, { navController.popBackStack() }, { local -> navController.navigate("detalhe_turista/${local.id}") })
                        }
                        composable("detalhe_turista/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 1
                            val local = LocaisTurista.lista.find { it.id == id }
                            if (local != null) {
                                DetalheTuristaScreen(local, { navController.popBackStack() }, { nome, end -> navController.navigate("mapa_offline/${Uri.encode(nome)}/${Uri.encode(end)}") })
                            } else {
                                LaunchedEffect(Unit) { navController.popBackStack() }
                            }
                        }

                        composable("planos") { PlanosScreen({ navController.popBackStack() }) }
                        composable("idiomas") { IdiomasScreen({ navController.popBackStack() }) }
                        composable("sobre") { SobreScreen({ navController.popBackStack() }) }
                        composable("definicoes") { DefinicoesScreen({ navController.popBackStack() }) }
                        composable("login") { LoginScreen({ navController.popBackStack() }, { versaoRecomposicao++; navController.popBackStack() }, { navController.navigate("esqueci_senha") }) }
                        composable("esqueci_senha") { EsqueciSenhaScreen({ navController.popBackStack() }) }
                        composable("cadastro_negocio") { CadastroNegocioScreen({ navController.popBackStack() }, { navController.popBackStack() }, { navController.navigate("verificar_email") }) }
                        composable("admin") { AdminScreen({ navController.popBackStack() }) }
                        composable("verificar_email") { VerificarEmailScreen({ versaoRecomposicao++; navController.popBackStack() }) }
                    }
                }
            }
        }
    }
}

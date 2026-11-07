package com.bissaulife.app.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bissaulife.app.theme.BissauGreen
import org.osmdroid.tileprovider.cachemanager.CacheManager
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadMapScreen(
    onVoltar: () -> Unit,
    onDownloadConcluido: () -> Unit
) {
    val context = LocalContext.current
    var baixandoMapa by remember { mutableStateOf(false) }
    var progresso by remember { mutableStateOf(0) }
    var mapViewRef by remember { mutableStateOf<MapView?>(null) }

    // Garante que o mapa seja pausado/retomado corretamente para não dar crash
    DisposableEffect(Unit) {
        onDispose {
            mapViewRef?.onDetach()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Baixar Mapa Offline", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onVoltar) { Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Baixe a área de Bissau para navegar sem internet.\nRecomendamos usar Wi-Fi.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(16.dp))

            // Mapa visível na tela inteira
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AndroidView(
                    factory = { ctx ->
                        MapView(ctx).apply {
                            setTileSource(TileSourceFactory.MAPNIK)
                            setMultiTouchControls(true)
                            controller.setZoom(12.0)
                            controller.setCenter(GeoPoint(11.8636, -15.5977)) // Centro de Bissau
                            mapViewRef = this
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.height(16.dp))

            if (baixandoMapa) {
                LinearProgressIndicator(
                    progress = { progresso / 100f },
                    modifier = Modifier.fillMaxWidth().height(8.dp),
                    color = BissauGreen
                )
                Spacer(Modifier.height(8.dp))
                Text("Baixando... $progresso%", fontWeight = FontWeight.Bold)
            } else {
                Button(
                    onClick = {
                        val map = mapViewRef ?: return@Button
                        baixandoMapa = true
                        
                        // Área de Bissau (Latitude Norte, Longitude Oeste, Latitude Sul, Longitude Leste)
                        val bb = BoundingBox(12.0, -15.7, 11.7, -15.5)
                        val cacheManager = CacheManager(map)
                        
                        // Zoom de 13 a 15 (evita baixar zoom muito próximo e estourar a memória)
                        cacheManager.downloadAreaAsync(context, bb, 13, 15, object : CacheManager.CacheManagerCallback {
                            override fun onTaskComplete() {
                                baixandoMapa = false
                                progresso = 100
                                Toast.makeText(context, "Mapa baixado com sucesso!", Toast.LENGTH_LONG).show()
                                onDownloadConcluido()
                                onVoltar()
                            }
                            override fun onTaskFailed(errors: Int) {
                                baixandoMapa = false
                                Toast.makeText(context, "Erro ao baixar: $errors", Toast.LENGTH_SHORT).show()
                            }
                            override fun updateProgress(p: Int, currentZoomLevel: Int, zoomMin: Int, zoomMax: Int) {
                                progresso = p
                            }
                            override fun downloadStarted() {}
                            override fun setPossibleTilesInArea(p0: Int) {}
                        })
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Icon(Icons.Filled.Download, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Iniciar Download", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

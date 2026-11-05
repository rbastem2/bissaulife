package com.bissaulife.app.screens

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.cachemanager.CacheManager
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import kotlin.math.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapaRotaScreen(
    nomeDestino: String,
    enderecoDestino: String,
    onVoltar: () -> Unit
) {
    val context = LocalContext.current
    var userLocation by remember { mutableStateOf<GeoPoint?>(null) }
    var carregandoGps by remember { mutableStateOf(true) }
    var baixandoMapa by remember { mutableStateOf(false) }
    
    // Coordenadas fictícias apenas para o mapa abrir em Bissau
    val destinoPoint = GeoPoint(11.8636, -15.5977)

    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted -> hasPermission = isGranted }

    LaunchedEffect(Unit) {
        if (!hasPermission) permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    LaunchedEffect(hasPermission) {
        if (hasPermission) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            // Força a atualização do GPS em vez de pegar o último salvo
            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location: Location? ->
                    carregandoGps = false
                    if (location != null) {
                        userLocation = GeoPoint(location.latitude, location.longitude)
                    } else {
                        userLocation = GeoPoint(11.8636, -15.5977) // Fallback
                    }
                }
                .addOnFailureListener {
                    carregandoGps = false
                    userLocation = GeoPoint(11.8636, -15.5977)
                }
        } else {
            carregandoGps = false
            userLocation = GeoPoint(11.8636, -15.5977)
        }
    }

    val distanciaKm = userLocation?.let {
        calcularDistancia(it.latitude, it.longitude, destinoPoint.latitude, destinoPoint.longitude)
    } ?: 0.0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rota Offline", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Box(modifier = Modifier.weight(1f)) {
                var mapViewRef by remember { mutableStateOf<MapView?>(null) }
                
                AndroidView(
                    factory = { ctx ->
                        Configuration.getInstance().userAgentValue = ctx.packageName
                        MapView(ctx).apply {
                            setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
                            setMultiTouchControls(true)
                            controller.setZoom(13.0)
                            controller.setCenter(destinoPoint)
                            mapViewRef = this
                        }
                    },
                    update = { mapView ->
                        mapView.overlays.clear()
                        val markerDestino = Marker(mapView).apply {
                            position = destinoPoint
                            title = nomeDestino
                            snippet = enderecoDestino
                            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        }
                        mapView.overlays.add(markerDestino)

                        userLocation?.let { user ->
                            val markerUser = Marker(mapView).apply {
                                position = user
                                title = "Sua localização (GPS)"
                                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                            }
                            mapView.overlays.add(markerUser)

                            if (user.latitude != destinoPoint.latitude || user.longitude != destinoPoint.longitude) {
                                val linha = Polyline(mapView).apply {
                                    setPoints(listOf(user, destinoPoint))
                                    outlinePaint.color = android.graphics.Color.RED
                                    outlinePaint.strokeWidth = 8f
                                }
                                mapView.overlays.add(linha)
                                val boundingBox = BoundingBox.fromGeoPoints(listOf(user, destinoPoint))
                                mapView.zoomToBoundingBox(boundingBox, true, 100)
                            }
                        }
                        mapView.invalidate()
                    },
                    modifier = Modifier.fillMaxSize()
                )

                // Botão Flutuante para Baixar Mapa Offline
                FloatingActionButton(
                    onClick = {
                        val map = mapViewRef ?: return@FloatingActionButton
                        baixandoMapa = true
                        // Baixa uma área de aprox. 10x10km ao redor de Bissau
                        val bb = BoundingBox(12.0, -15.7, 11.7, -15.5) 
                        val cacheManager = CacheManager(map)
                        cacheManager.downloadAreaAsync(context, bb, 13, 16, object : CacheManager.CacheManagerCallback {
                            override fun onTaskComplete() {
                                baixandoMapa = false
                                Toast.makeText(context, "Mapa de Bissau baixado para offline!", Toast.LENGTH_LONG).show()
                            }
                            override fun onTaskFailed(errors: Int) {
                                baixandoMapa = false
                                Toast.makeText(context, "Erro ao baixar: $errors", Toast.LENGTH_SHORT).show()
                            }
                            override fun updateProgress(progress: Int, currentZoomLevel: Int, zoomMin: Int, zoomMax: Int) {}
                            override fun onTaskStarted() {}
                        })
                    },
                    modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    if (baixandoMapa) {
                        CircularProgressIndicator(color = androidx.compose.ui.graphics.Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Icon(Icons.Filled.Download, "Baixar Mapa Offline")
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.LocationOn, null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.width(8.dp))
                        Text(nomeDestino, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(enderecoDestino, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(12.dp))
                    
                    if (carregandoGps) {
                        Text("Obtendo sinal de GPS...", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    } else {
                        Text("Distância aproximada: %.2f km".format(distanciaKm), fontWeight = FontWeight.SemiBold)
                        Text("(Ative o GPS e o Wi-Fi/Internet para baixar o mapa primeiro)", fontSize = 10.sp, color = androidx.compose.ui.graphics.Color.Gray, textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.padding(top = 4.dp))
                    }
                }
            }
        }
    }
}

fun calcularDistancia(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val r = 6371.0
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a = sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2) * sin(dLon / 2)
    return r * 2 * atan2(sqrt(a), sqrt(1 - a))
}

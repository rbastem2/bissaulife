package com.bissaulife.app.screens

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import org.osmdroid.config.Configuration
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
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                userLocation = if (location != null) GeoPoint(location.latitude, location.longitude)
                else GeoPoint(11.8636, -15.5977) // Fallback Bissau
            }
        } else {
            userLocation = GeoPoint(11.8636, -15.5977)
        }
    }

    // Como não temos lat/lon exatos, centralizamos em Bissau para o mapa não ficar vazio
    val destinoPoint = GeoPoint(11.8636, -15.5977) 
    
    val distanciaKm = userLocation?.let {
        calcularDistancia(it.latitude, it.longitude, destinoPoint.latitude, destinoPoint.longitude)
    } ?: 0.0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mapa Offline", fontSize = 18.sp) },
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
                AndroidView(
                    factory = { ctx ->
                        Configuration.getInstance().userAgentValue = ctx.packageName
                        MapView(ctx).apply {
                            setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
                            setMultiTouchControls(true)
                            controller.setZoom(13.0)
                            controller.setCenter(destinoPoint)
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
                                title = "Sua localização"
                                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                            }
                            mapView.overlays.add(markerUser)

                            val linha = Polyline(mapView).apply {
                                setPoints(listOf(user, destinoPoint))
                                outlinePaint.color = android.graphics.Color.RED
                                outlinePaint.strokeWidth = 8f
                            }
                            mapView.overlays.add(linha)

                            val boundingBox = org.osmdroid.util.BoundingBox.fromGeoPoints(listOf(user, destinoPoint))
                            mapView.zoomToBoundingBox(boundingBox, true, 100)
                        }
                        mapView.invalidate()
                    },
                    modifier = Modifier.fillMaxSize()
                )
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
                    Text("Distância em linha reta: %.2f km".format(distanciaKm), fontWeight = FontWeight.SemiBold)
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

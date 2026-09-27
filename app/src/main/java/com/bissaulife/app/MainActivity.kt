package com.bissaulife.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
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

                LaunchedEffect(Unit) {
                    delay(2500)
                    showSplash = false
                }

                if (showSplash) {
                    SplashScreen()
                } else {
                    HomeScreen()
                }
            }
        }
    }
}

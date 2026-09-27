package com.bissaulife.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bissaulife.app.theme.BissauGreen

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF001F3F), Color(0xFF003366), Color(0xFF004C99))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Spacer(Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.White, RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("🌍", fontSize = 60.sp)
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "BissauLife",
                color = Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Descubra. Escolha. Compre. Viva.",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BissauGreen),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text("Começar", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text("Entrar", fontSize = 17.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

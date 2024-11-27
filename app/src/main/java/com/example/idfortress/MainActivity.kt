package com.example.idfortress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.idfortress.ui.theme.IdFortressTheme
import com.example.idfortress.screens.Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdFortressTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    Home()
                }
            }
        }
    }
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    // Seu conteúdo aqui
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    // Para a preview, utilizamos um padding fixo ou nenhum modificador
    IdFortressTheme {
        Home()
    }
}

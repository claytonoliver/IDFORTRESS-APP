package com.example.idfortress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.biometriaapp.BiomatriaDigital
import com.example.idfortress.screens.AutenticacaoCadastral
import com.example.idfortress.screens.BiometriaFacial
import com.example.idfortress.screens.ConsultaScoreCPF
import com.example.idfortress.screens.Documentoscopia
import com.example.idfortress.ui.theme.IdFortressTheme
import com.example.idfortress.screens.Home
import com.example.idfortress.screens.SimSwap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdFortressTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    var navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "Home"
                    ) {
                        composable(route = "Home"){ Home(navController) }
                        composable(route = "BiometriaFacial"){ BiometriaFacial(navController)}
                        composable(route = "BiometriaDigital"){ BiomatriaDigital(navController) }
                        composable(route = "SimSwap"){ SimSwap(navController) }
                        composable(route = "Documentoscopia"){ Documentoscopia(navController) }
                        composable(route = "ConsultaScoreCpf"){ ConsultaScoreCPF(navController) }
                        composable(route = "AutenticacaoCadastral"){ AutenticacaoCadastral(navController) }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    IdFortressTheme {

    }
}

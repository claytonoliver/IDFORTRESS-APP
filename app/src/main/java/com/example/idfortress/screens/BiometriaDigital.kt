package com.example.idfortress.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BiometriaDigital(navController: NavController) {

}

@Preview(showBackground = true)
@Composable
private fun BiometriaDigitalPreview() {
    val navController = rememberNavController();
    BiometriaDigital(navController)
}

package com.example.idfortress.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.idfortress.R

@Composable
fun SimSwap(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.Background))
    ) {
        Column {
            Header("SIM SWAP", navController)

            // Main content area
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = "",
                    onValueChange = { /* Handle text input */ },
                    label = { Text("Digite o número do celular") },
                    modifier = Modifier.fillMaxWidth(),

                )
                Row (modifier = Modifier.fillMaxWidth()){
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.Captura),
                            contentColor = Color.Black
                        )
                    ) {

                    }
                }

            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SimSwapPreview() {
    val navController = rememberNavController()
    SimSwap(navController = navController)
}
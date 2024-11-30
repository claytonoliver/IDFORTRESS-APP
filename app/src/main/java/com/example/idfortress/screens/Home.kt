package com.example.idfortress.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.idfortress.R
import com.example.idfortress.ui.theme.IdFortressTheme

@Composable
fun Home(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF2E2E2E))
    ) {
        // Imagem no topo
        androidx.compose.foundation.Image(
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.idfortresslogo),
            contentDescription = "Cabeçalho",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Black),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color(0xFF2E2E2E)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate("BiometriaFacial")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .width(260.dp)
                ) {
                    Text(
                        text = "BIOMETRIA FACIAL",
                        fontSize = 12.5.sp
                    )
                }
                Button(
                    onClick = { /* Ação do Botão 1 */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black,
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .width(260.dp)
                ) {
                    Text(
                        text = "BIOMETRIA DIGITAL",
                        fontSize = 12.5.sp
                    )
                }
                Button(
                    onClick = { navController.navigate("SimSwap") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .width(260.dp)
                ) {
                    Text(
                        text = "SIM SWAP",
                        fontSize = 12.5.sp
                    )
                }
                Button(
                    onClick = { /* Ação do Botão 1 */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .width(260.dp)
                ) {
                    Text(
                        text = "DOCUMENTOSCOPIA",
                        fontSize = 12.5.sp
                    )
                }
                Button(
                    onClick = { /* Ação do Botão 1 */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .width(260.dp)
                ) {
                    Text(
                        text = "CONSULTA SCORE CPF",
                        fontSize = 12.5.sp
                    )
                }
                Button(
                    onClick = { /* Ação do Botão 1 */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .width(260.dp)
                ) {
                    Text(
                        text = "AUTENTICAÇÃO CADASTRAL",
                        fontSize = 12.5.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    IdFortressTheme {
        val navController = rememberNavController()
        Home(navController = navController)
    }
}

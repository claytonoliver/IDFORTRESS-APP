package com.example.idfortress.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.idfortress.R
import kotlin.random.Random

@Composable
fun SimSwap(navController: NavController) {
    // Estado para armazenar o número do celular e a mensagem de risco
    val phoneNumber = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }

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
                // Campo de texto para o número do celular
                TextField(
                    value = phoneNumber.value,
                    onValueChange = { phoneNumber.value = it },
                    label = { Text("Digite o número do celular") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        modifier = Modifier
                            .width(160.dp)
                            .height(50.dp),
                        onClick = {
                            // Gerar um valor aleatório para determinar o risco
                            val riskLevel = Random.nextInt(0, 100)

                            // Definir a mensagem de risco com base no valor aleatório
                            message.value = when {
                                riskLevel < 25 -> "Risco Muito baixo: A troca não ocorreu ou ocorreu há mais de 15 dias."
                                riskLevel in 25..49 -> "Risco Baixo: Uma troca ocorreu em algum momento entre os últimos 3 e 14 dias."
                                riskLevel in 50..74 -> "Risco Médio: Uma troca ocorreu nas últimas 72 horas."
                                else -> "Risco Alto: Uma troca ocorreu no mesmo dia."
                            }

                            // Exibir o alerta
                            showDialog.value = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.Captura),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "Validar")
                    }
                }
            }
        }
    }

    // Exibindo o Dialog com a mensagem de risco
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Resultado do SIM SWAP") },
            text = { Text(text = message.value) },
            confirmButton = {
                Button(
                    onClick = { showDialog.value = false }
                ) {
                    Text("OK")
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SimSwapPreview() {
    val navController = rememberNavController()
    SimSwap(navController = navController)
}

package com.example.idfortress.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.idfortress.R
import kotlin.random.Random

@Composable
fun ConsultaScoreCPF(navController: NavController) {
    var cpf by remember { mutableStateOf("") }
    var score by remember { mutableStateOf<Int?>(null) }
    var message by remember { mutableStateOf("") }

    fun calcularScore(cpf: String): Int {
        return Random.nextInt(1, 1001)
    }

    fun gerarScore() {
        if (cpf.length == 11 && cpf.all { it.isDigit() }) {
            score = calcularScore(cpf)
            message = when {
                score!! <= 300 -> "Alerta! Alta chance de fraude."
                score!! <= 700 -> "Cuidado! Risco moderado de fraude."
                else -> "Risco baixo de fraude."
            }
        } else {
            score = null
            message = "CPF inválido! Digite um CPF com 11 dígitos."
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.Background))
    ){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header("Validação Score CPF", navController)
        Spacer(Modifier.height(100.dp))
        Text(
            text = "Digite Seu CPF",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 20.dp),
            color = Color.White
        )

        BasicTextField(
            value = cpf,
            onValueChange = { cpf = it },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp)
                .border(1.dp, Color.Gray)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { gerarScore() }) {
            Text("Gerar Score")
        }

        Spacer(modifier = Modifier.height(20.dp))

        score?.let {
            Text("Seu Score Antifraude é: $it", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(message, color = Color.Red)
    }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ConsultaScoreCPFPreview() {
    val navController = rememberNavController()
    ConsultaScoreCPF(navController)
}
package com.example.idfortress.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AutenticacaoCadastral(navController: NavController) {
    // State variables for form fields
    val cpf = remember { mutableStateOf("") }
    val dataNascimento = remember { mutableStateOf("") }
    val endereco = remember { mutableStateOf("") }
    val celular = remember { mutableStateOf("") }
    val feedbackMessage = remember { mutableStateOf("") }

    // Função para validar o CPF
    fun validarCpf(cpf: String): Boolean {
        val cpfLimpo = cpf.replace("[^0-9]".toRegex(), "")
        if (cpfLimpo.length != 11 || cpfLimpo.all { it == cpfLimpo[0] }) return false
        return true
    }

    // Função para validar a data de nascimento
    fun validarDataNascimento(data: String): Boolean {
        val regex = "^\\d{2}/\\d{2}/\\d{4}$".toRegex()
        return data.matches(regex)
    }

    // Função para validar o telefone
    fun validarTelefone(telefone: String): Boolean {
        val regex = "^\\(\\d{2}\\) \\d{5}-\\d{4}$".toRegex()
        return telefone.matches(regex)
    }

    // Função para validar o formulário
    fun validarFormulario(): Boolean {
        val cpfValido = validarCpf(cpf.value)
        val dataNascimentoValida = validarDataNascimento(dataNascimento.value)
        val telefoneValido = validarTelefone(celular.value)
        val enderecoValido = endereco.value.isNotEmpty()

        return when {
            !cpfValido -> {
                feedbackMessage.value = "CPF inválido. Verifique o número."
                false
            }
            !dataNascimentoValida -> {
                feedbackMessage.value = "Data de nascimento inválida. Formato esperado: dd/mm/aaaa."
                false
            }
            !telefoneValido -> {
                feedbackMessage.value = "Telefone inválido. Formato esperado: (XX) XXXXX-XXXX."
                false
            }
            !enderecoValido -> {
                feedbackMessage.value = "Endereço é obrigatório."
                false
            }
            else -> {
                feedbackMessage.value = "Dados autenticados com sucesso."
                true
            }
        }
    }

    // Layout do formulário
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // CPF
        TextField(
            value = cpf.value,
            onValueChange = { cpf.value = it },
            label = { Text("CPF") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Data de Nascimento
        TextField(
            value = dataNascimento.value,
            onValueChange = { dataNascimento.value = it },
            label = { Text("CPF") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Endereço
        TextField(
            value = endereco.value,
            onValueChange = { endereco.value = it },
            label = { Text("Endereço") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Celular
        TextField(
            value = celular.value,
            onValueChange = { celular.value = it },
            label = { Text("CPF") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botão Validar
        Button(
            onClick = {
                validarFormulario()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("VALIDAR", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Feedback da validação
        Text(text = feedbackMessage.value, color = if (feedbackMessage.value == "Dados autenticados com sucesso.") Color.Green else Color.Red)
    }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.biometriaapp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.example.idfortress.BiometricAuthenticator
import com.example.idfortress.BiometricAuthStatus

@Composable
fun BiomatriaDigital(navController: NavController) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val fragmentActivity = context as? FragmentActivity

    if (fragmentActivity != null) {
        val biometricAuthenticator = BiometricAuthenticator(context)

        // Tela principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TopAppBar(title = { Text("Autenticação Biométrica") })

            // Botão para iniciar a autenticação
            Button(onClick = {
                biometricAuthenticator.promptBiometricAuth(
                    title = "Autenticação Biométrica",
                    subTitle = "Utilize sua impressão digital ou reconhecimento facial",
                    negativeButtonText = "Cancelar",
                    fragmentActivity = fragmentActivity,
                    onSuccess = { result ->
                        Toast.makeText(context, "Autenticação bem-sucedida!", Toast.LENGTH_SHORT).show()
                    },
                    onFailed = {
                        Toast.makeText(context, "Falha na autenticação", Toast.LENGTH_SHORT).show()
                    },
                    onError = { errorCode, errString ->
                        Toast.makeText(context, "Erro: $errString", Toast.LENGTH_SHORT).show()
                    }
                )
            }) {
                Text("Autenticar com Biometria")
            }
        }
    } else {
        // Exibe uma mensagem de erro caso o contexto não seja um FragmentActivity
        Toast.makeText(context, "Erro: Atividade não encontrada.", Toast.LENGTH_SHORT).show()
    }
}

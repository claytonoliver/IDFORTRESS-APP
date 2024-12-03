package com.example.idfortress.screens
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.idfortress.R


class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNome: EditText = findViewById(R.id.edtNome)
        val edtCpf: EditText = findViewById(R.id.edtCpf)
        val edtEndereco: EditText = findViewById(R.id.edtEndereco)
        val edtTelefone: EditText = findViewById(R.id.edtTelefone)
        val btnEnviar: Button = findViewById(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val nome = edtNome.text.toString().trim()
            val cpf = edtCpf.text.toString().trim()
            val endereco = edtEndereco.text.toString().trim()
            val telefone = edtTelefone.text.toString().trim()

            if (validarFormulario(nome, cpf, endereco, telefone)) {
                Toast.makeText(this, "Dados enviados com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validarFormulario(nome: String, cpf: String, endereco: String, telefone: String): Boolean {
        if (TextUtils.isEmpty(nome)) {
            showToast("O nome é obrigatório")
            return false
        }

        if (!validarCpf(cpf)) {
            showToast("CPF inválido")
            return false
        }

        if (TextUtils.isEmpty(endereco)) {
            showToast("O endereço é obrigatório")
            return false
        }

        if (!validarTelefone(telefone)) {
            showToast("Telefone celular inválido")
            return false
        }

        return true
    }

    private fun validarCpf(cpf: String): Boolean {
        val cpfLimpo = cpf.replace("[^0-9]".toRegex(), "")

        if (cpfLimpo.length != 11 || cpfLimpo.all { it == cpfLimpo[0] }) return false

        val digito1 = calcularDigitoVerificador(cpfLimpo, 10)
        val digito2 = calcularDigitoVerificador(cpfLimpo, 11)

        return cpfLimpo[9] == digito1 && cpfLimpo[10] == digito2
    }

    private fun calcularDigitoVerificador(cpf: String, peso: Int): Char {
        var soma = 0
        var multiplicador = peso
        for (i in 0 until 9) {
            soma += cpf[i].digitToInt() * multiplicador
            multiplicador--
        }
        val digito = 11 - (soma % 11)
        return if (digito > 9) '0' else digito.toString()[0]
    }


    private fun validarTelefone(telefone: String): Boolean {
        val regex = "^\\(\\d{2}\\) \\d{5}-\\d{4}$".toRegex()
        return telefone.matches(regex)
    }

    private fun showToast(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}


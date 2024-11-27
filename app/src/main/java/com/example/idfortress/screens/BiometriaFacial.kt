package com.example.idfortress.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.idfortress.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun BiometriaFacial(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.Background))
    ){

        Column (
        )
        {
            Row() {
                Header("Biometria Facial", navController)
            }


            Row (
                Modifier.padding(20.dp)
            ){
                Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Aproxime Seu Rosto",
                textAlign = TextAlign.Center,
                color = Color.LightGray,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold

            ) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

                androidx.compose.foundation.Canvas(
                    modifier = Modifier.size(350.dp)
                ){
                    drawCircle(
                        color = Color.LightGray,
                        radius = size.minDimension /2,
                        style = Fill
                    )
                }
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                    Arrangement.Center,
                    Alignment.CenterVertically
                    ){
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.Captura),
                            contentColor = Color.Black
                        )
                    ) {

                        Text(
                            "Iniciar Captura",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }


        }
    }
}

@Composable
fun CircleCanvas(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BiometriaFacialPreview() {
    val navController = rememberNavController()
    BiometriaFacial(navController = navController)
}
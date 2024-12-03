package com.example.idfortress.screens

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.idfortress.R
import kotlinx.coroutines.delay
import java.io.File
import kotlin.random.Random

@Composable
fun Documentoscopia(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalContext.current as LifecycleOwner
    var showLoading by remember { mutableStateOf(false) }
    var validationResult by remember { mutableStateOf<String?>(null) }
    var imageCapture: ImageCapture? = remember { null }

    if (showLoading) {
        // Tela de loading
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Validando...",
                color = Color.White,
                fontSize = 24.sp
            )
        }

        // Simulação de validação
        LaunchedEffect(Unit) {
            delay(5000L)
            validationResult = if (Random.nextBoolean()) "Documento validado" else "Não foi possível ler, tente novamente"
            showLoading = false
        }
    } else if (validationResult != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = validationResult!!,
                color = Color.Black,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.Background))
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Row {
                    Header("Documentoscopia", navController)
                }
                Row(
                    Modifier.padding(10.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Tire uma foto do documento aberto",
                        textAlign = TextAlign.Center,
                        color = Color.LightGray,
                        fontSize = 30.sp
                    )
                }


                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(350.dp)
                            .fillMaxHeight()
                    ) {
                        CameraPreview(
                            modifier = Modifier.fillMaxSize(),
                            lifecycleOwner = lifecycleOwner,
                            onImageCaptureReady = { imageCapture = it }
                        )
                    }
                    Spacer(modifier = Modifier.height(80.dp))
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Button(
                            onClick = {
                                imageCapture?.let { captureImage(context, it) }
                                showLoading = true
                            },
                            modifier = Modifier.size(80.dp)
                        ) {
                            Text(text = "●", fontSize = 24.sp, color = Color.Red, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner,
    onImageCaptureReady: (ImageCapture) -> Unit
) {
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val previewView = androidx.camera.view.PreviewView(context).apply {
        implementationMode = androidx.camera.view.PreviewView.ImplementationMode.COMPATIBLE
    }

    AndroidView(
        factory = { previewView },
        modifier = modifier
    ) {
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = androidx.camera.core.Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
            val imageCapture = ImageCapture.Builder().build()
            onImageCaptureReady(imageCapture)

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(context))
    }
}

private fun captureImage(context: Context, imageCapture: ImageCapture) {
    val photoFile = File(
        context.filesDir,
        "photo_${System.currentTimeMillis()}.jpg"
    )
    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

            }

            override fun onError(exception: ImageCaptureException) {

            }
        }
    )
}

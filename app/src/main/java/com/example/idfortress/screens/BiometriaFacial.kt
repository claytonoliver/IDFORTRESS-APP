package com.example.idfortress.screens


import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.idfortress.R
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import java.util.concurrent.Executors

@Composable
fun BiometriaFacial(navController: NavController) {
    var isCameraVisible by remember { mutableStateOf(false) }
    var cameraSelector by remember { mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA) }
    var faceDetected by remember { mutableStateOf(false) }
    var countdown by remember { mutableStateOf(10) }
    var validationMessage by remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(faceDetected) {
        if (faceDetected) {
            countdown = 10
            for (i in 10 downTo 1) {
                countdown = i
                kotlinx.coroutines.delay(1000)
            }
            validationMessage = "Rosto Validado!"
            faceDetected = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.Background))
    ) {
        Column {
            Row {
                Header("Biometria Facial", navController)
            }

            Row(
                Modifier.padding(20.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Aproxime Seu Rosto",
                    textAlign = TextAlign.Center,
                    color = Color.LightGray,
                    fontSize = 30.sp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(350.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    if (isCameraVisible) {
                        CameraPreview(
                            cameraSelector = cameraSelector,
                            onFaceDetected = { faceDetected = true },
                            modifier = Modifier.matchParentSize()
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { isCameraVisible = !isCameraVisible },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.Captura),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = if (isCameraVisible) "Parar Captura" else "Iniciar Captura",
                            fontSize = 20.sp
                        )
                    }

                    Button(
                        onClick = {
                            cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA) {
                                CameraSelector.DEFAULT_BACK_CAMERA
                            } else {
                                CameraSelector.DEFAULT_FRONT_CAMERA
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = "Trocar Câmera",
                            fontSize = 16.sp
                        )
                    }
                }
                if (faceDetected) {
                    Text(
                        text = "Aguarde $countdown segundos...",
                        color = Color.Green,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
                if (validationMessage.isNotEmpty()) {
                    Text(
                        text = validationMessage,
                        color = Color.Green,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalGetImage::class)
@Composable
fun CameraPreview(
    cameraSelector: CameraSelector,
    onFaceDetected: (Face) -> Unit,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            PreviewView(ctx).apply {
                val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(surfaceProvider)
                    }

                    val faceAnalyzer = ImageAnalysis.Builder().build().apply {
                        setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
                            processImageProxy(imageProxy, onFaceDetected)
                        }
                    }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            faceAnalyzer
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }, ContextCompat.getMainExecutor(ctx))
            }
        },
        modifier = modifier
    )
}


@ExperimentalGetImage
private fun processImageProxy(
    imageProxy: ImageProxy,
    onFaceDetected: (Face) -> Unit
) {
    val mediaImage = imageProxy.image
    if (mediaImage == null) {
        Log.e("FaceDetection", "ImageProxy não possui imagem")
        imageProxy.close()
        return
    }

    val rotationDegrees = imageProxy.imageInfo.rotationDegrees
    Log.d("FaceDetection", "Rotation degrees: $rotationDegrees")

    val image = InputImage.fromMediaImage(mediaImage, rotationDegrees)
    val detector = FaceDetection.getClient()

    detector.process(image)
        .addOnSuccessListener { faces ->
            Log.d("FaceDetection", "Faces detectados: ${faces.size}")
            if (faces.isNotEmpty()) {
                Log.d("FaceDetection", "Face detectada: ${faces[0].boundingBox}")
                onFaceDetected(faces[0])
            } else {
                Log.d("FaceDetection", "Nenhum rosto detectado.")
            }
        }
        .addOnFailureListener { e ->
            Log.e("FaceDetection", "Erro ao detectar rosto: ${e.message}")
        }
        .addOnCompleteListener {
            Log.d("FaceDetection", "Processamento completo")
            imageProxy.close() // Certifique-se de liberar o recurso
        }
}


package com.github.mag0716.mlkitsample

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MLKitSample"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    private lateinit var cameraExecutor: ExecutorService

    private var preview: Preview? = null
    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            }
        } else {
            Toast.makeText(
                this,
                "Permissions not granted by the user.",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            preview = Preview.Builder().build()

            val cameraSelector =
                CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

            try {
                cameraProvider.unbindAll()

                camera = cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    CreditCardAnalyzer.generateImageAnalysisUseCase(cameraExecutor)
                )
                preview?.setSurfaceProvider(viewFinder.createSurfaceProvider(camera?.cameraInfo))
            } catch (exception: Exception) {
                Log.e(TAG, "Use case binding failed", exception)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }

    private class CreditCardAnalyzer : ImageAnalysis.Analyzer {

        companion object {
            fun generateImageAnalysisUseCase(cameraExecutor: ExecutorService): ImageAnalysis {
                return ImageAnalysis
                    .Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, CreditCardAnalyzer())
                    }
            }
        }

        private val recognizer = TextRecognition.getClient()

        @SuppressLint("UnsafeExperimentalUsageError")
        override fun analyze(imageProxy: ImageProxy) {
            Log.d(TAG, "analyze!!")
            val mediaImage = imageProxy.image
            runBlocking {
                if (mediaImage != null) {
                    val image =
                        InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
                    val creditCard = detectCreditCardInfo(recognizeCreditCard(image))
                    Log.d(TAG, "analyze : $creditCard")
                }
                imageProxy.close()
            }
        }

        private suspend fun recognizeCreditCard(input: InputImage): Text =
            suspendCoroutine { continuation ->
                recognizer.process(input)
                    .addOnSuccessListener { visionText ->
                        Log.d(TAG, "success : $visionText")
                        continuation.resume(visionText)
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "failed", exception)
                        continuation.resumeWithException(exception)
                    }
            }

        private fun detectCreditCardInfo(visionText: Text): CreditCard? {
            val textBlocks = visionText.textBlocks
            var number: String? = null
            var expiredDate: String? = null
            for (block in textBlocks) {
                if (number == null) {
                    number = detectCreditCardNumber(block.text)
                }
                if (expiredDate == null) {
                    expiredDate = detectCreditCardExpiredDate(block.text)
                }
                if (number != null && expiredDate != null) {
                    return CreditCard(number, expiredDate)
                }
            }
            return null
        }

        private fun detectCreditCardNumber(text: String): String? {
            if (text.replace(" ", "")
                    .matches(Regex("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})\$"))
            ) {
                return text
            }
            return null
        }

        private fun detectCreditCardExpiredDate(text: String): String? {
            //if(text.matches(Regex("[01][0-9]/[0-9]{2}"))) {
            if (text.matches(Regex("[0-9]{2}/[0-9]{2}"))) {
                return text
            }
            return null
        }
    }
}

data class CreditCard(
    val number: String,
    val expiredDate: String
)

package com.github.mag0716.mlkitsample

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ExecutorService
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CreditCardAnalyzer(private val recognizedCreditCard: IRecognizedCreditCard) :
    ImageAnalysis.Analyzer {

    companion object {
        fun generateImageAnalysisUseCase(
            cameraExecutor: ExecutorService,
            recognizedCreditCard: IRecognizedCreditCard
        ): ImageAnalysis {
            return ImageAnalysis
                .Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, CreditCardAnalyzer(recognizedCreditCard))
                }
        }
    }

    interface IRecognizedCreditCard {
        fun onSuccess(creditCard: CreditCard)
    }

    private val recognizer = TextRecognition.getClient()

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        Log.d(MainActivity.TAG, "analyze!!")
        val mediaImage = imageProxy.image
        runBlocking {
            var creditCard: CreditCard? = null
            if (mediaImage != null) {
                val image =
                    InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
                creditCard = detectCreditCardInfo(recognizeCreditCard(image))
            }
            imageProxy.close()
            if (creditCard != null) {
                Log.d(MainActivity.TAG, "analyze : $creditCard")
                recognizedCreditCard.onSuccess(creditCard)
            }
        }
    }

    private suspend fun recognizeCreditCard(input: InputImage): Text =
        suspendCoroutine { continuation ->
            recognizer.process(input)
                .addOnSuccessListener { visionText ->
                    Log.d(MainActivity.TAG, "success : $visionText")
                    continuation.resume(visionText)
                }
                .addOnFailureListener { exception ->
                    Log.w(MainActivity.TAG, "failed", exception)
                    continuation.resumeWithException(exception)
                }
        }

    private fun detectCreditCardInfo(visionText: Text): CreditCard? {
        val textBlocks = visionText.textBlocks
        var number: String? = null
        var expiredDate: String? = null
        for (block in textBlocks) {
            Log.i(MainActivity.TAG, "detectCreditCardInfo : ${block.text}($number, $expiredDate)")
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
        val nonSpaceText = text.replace(" ", "")
        if (nonSpaceText.matches(Regex("[0-9]{2}/[0-9]{2}"))) {
            return nonSpaceText
        }
        return null
    }
}
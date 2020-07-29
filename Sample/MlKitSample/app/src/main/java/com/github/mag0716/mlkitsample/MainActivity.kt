package com.github.mag0716.mlkitsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.mag0716.mlkitsample.CameraActivity.Companion.RESULT_CREDIT_CARD
import io.card.payment.CardIOActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MLKitSample"
    }

    private val launcherCameraActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val creditCard = result.data?.getSerializableExtra(RESULT_CREDIT_CARD) as CreditCard
            textCreditCardNumber.editText?.setText(creditCard.number)
            textCreditCardExpiredDate.editText?.setText(creditCard.expiredDate)
        }
    }

    private val launcherCardIoActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val scanResult =
                result.data?.getParcelableExtra<io.card.payment.CreditCard>(CardIOActivity.EXTRA_SCAN_RESULT)
            if (scanResult != null) {
                textCreditCardNumber.editText?.setText(scanResult.redactedCardNumber)
                textCreditCardExpiredDate.editText?.setText("${scanResult.expiryMonth}/${scanResult.expiryYear}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textCreditCardNumber.setEndIconOnClickListener {
            if (checkScanMethod.isChecked) {
                launcherCameraActivity.launch(Intent(this, CameraActivity::class.java))
            } else {
                launcherCardIoActivity.launch(Intent(this, CardIOActivity::class.java).apply {
                    putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
                })
            }
        }
    }
}
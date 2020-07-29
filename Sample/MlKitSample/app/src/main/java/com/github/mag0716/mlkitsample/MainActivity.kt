package com.github.mag0716.mlkitsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.mag0716.mlkitsample.CameraActivity.Companion.RESULT_CREDIT_CARD
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MLKitSample"
    }

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val creditCard = result.data?.getSerializableExtra(RESULT_CREDIT_CARD) as CreditCard
            textCreditCardNumber.editText?.setText(creditCard.number)
            textCreditCardExpiredDate.editText?.setText(creditCard.expiredDate)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textCreditCardNumber.setEndIconOnClickListener {
            launcher.launch(Intent(this, CameraActivity::class.java))
        }
    }
}
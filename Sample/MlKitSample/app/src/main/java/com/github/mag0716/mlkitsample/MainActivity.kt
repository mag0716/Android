package com.github.mag0716.mlkitsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mag0716.mlkitsample.CameraActivity.Companion.RESULT_CREDIT_CARD
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MLKitSample"
        private const val REQUEST_CODE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textCreditCardNumber.setEndIconOnClickListener {
            startActivityForResult(
                Intent(this, CameraActivity::class.java),
                REQUEST_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val creditCard = data?.getSerializableExtra(RESULT_CREDIT_CARD) as CreditCard
                textCreditCardNumber.editText?.setText(creditCard.number)
                textCreditCardExpiredDate.editText?.setText(creditCard.expiredDate)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
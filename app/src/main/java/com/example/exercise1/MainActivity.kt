package com.example.exercise1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.lang.Exception
import java.text.DecimalFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var CarPrice: EditText
    lateinit var DownPayment: EditText
    lateinit var LoanPeriod: EditText
    lateinit var InterestRate: EditText
    lateinit var ViewLoan: TextView
    lateinit var ViewInterest: TextView
    lateinit var ViewMonthRepay: TextView

    val formater = DecimalFormat("0.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CarPrice = findViewById(R.id.editTextCarPrice)
        DownPayment = findViewById(R.id.editTextDownPayment)
        LoanPeriod = findViewById(R.id.editTextLoanPeriod)
        InterestRate = findViewById(R.id.editTextInterestRate)

        ViewLoan = findViewById(R.id.textViewLoan)
        ViewInterest = findViewById(R.id.textViewInterest)
        ViewMonthRepay = findViewById(R.id.textViewMonthlyRepayment)


        findViewById<Button>(R.id.buttonCalculate).setOnClickListener {
            calculate(it)
        }

        val resetbutton: Button = findViewById(R.id.buttonReset)
        resetbutton.setOnClickListener { Reset() }
    }
    private fun calculate(view: View){

        try {

            ViewLoan.setText(formater.format(CarPrice.text.toString().toDouble() - DownPayment.text.toString().toDouble()))
            ViewInterest.setText(formater.format(ViewLoan.text.toString().toDouble() * InterestRate.text.toString().toDouble() * LoanPeriod.text.toString().toDouble()))

            if((ViewLoan.text.toString().toDouble() + InterestRate.text.toString().toDouble()) == 0.toDouble()){
                ViewMonthRepay.setText(formater.format(0))
            }else {
                ViewMonthRepay.setText(formater.format((ViewLoan.text.toString().toDouble() + InterestRate.text.toString().toDouble()) / LoanPeriod.text.toString().toDouble() / 12.toDouble()))
            }

        }catch (e:Exception){
            ViewLoan.setText(formater.format(0))
            ViewInterest.setText(formater.format(0))
            ViewMonthRepay.setText(formater.format(0))
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private fun Reset(){
        CarPrice.setText("")
        DownPayment.setText("")
        LoanPeriod.setText("")
        InterestRate.setText("")
        ViewLoan.setText("")
        ViewInterest.setText("")
        ViewMonthRepay.setText("")
    }
}

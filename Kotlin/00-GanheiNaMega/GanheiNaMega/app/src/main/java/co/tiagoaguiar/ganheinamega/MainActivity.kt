package co.tiagoaguiar.ganheinamega

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var preferences : SharedPreferences

    companion object {
        private const val TAG: String = "MainActivity"
        private const val DATABASE: String = "db"
        private const val RESULT: String = "result"
        private const val MIN_NUMBER = 6
        private const val MAX_NUMBER = 15
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences(DATABASE, Context.MODE_PRIVATE)
        val result = preferences.getString(RESULT, null)

        val editNumber: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

        /*Atualiza o text view com o ultimo resultado se diferente de null-*/
        result?.let { txtResult.text = "Ultimo resultado: $result" }

        btnGenerate.setOnClickListener {
            val text = editNumber.text.toString()
            Log.d(TAG, "onCreate: $text")
            numberGenerator(text, txtResult)
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun numberGenerator(text: String, txtResult: TextView) {
        if(text.isEmpty()) {
            Toast.makeText(this, R.string.number_alert, Toast.LENGTH_SHORT).show()
            return
        }
        val numberQty = text.toInt()
        if(numberQty < MIN_NUMBER || numberQty > MAX_NUMBER) {
            Toast.makeText(this, R.string.number_alert, Toast.LENGTH_SHORT).show()
            return
        }

        val numbers = mutableSetOf<Int>()
        while (numbers.size < numberQty) {
            val randomNumber = Random.nextInt(1, 61)
            Log.d(TAG, "numberGenerator: $randomNumber")
            numbers.add(randomNumber)

        }
        txtResult.text = numbers.joinToString(" - ")
        saveNumbers(txtResult.text.toString())
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveNumbers(result: String) {
        /*
        val editor = preferences.edit()
        editor.putString(RESULT, result)
        //Apply - Assincrono - commit - sincrono
        editor.apply()
         */
        preferences.edit().apply() {
            putString(RESULT, result)
            apply()
        }
    }
}

package co.tiagoaguiar.fitnesstracker

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import co.tiagoaguiar.fitnesstracker.model.Calc

class ImcActivity : AppCompatActivity() {

    private lateinit var editHeight: EditText
    private lateinit var editWeight: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editHeight = findViewById(R.id.edit_imc_height)
        editWeight = findViewById(R.id.edit_imc_weight)

        val btnCalcImc: Button = findViewById(R.id.btn_imc)

        btnCalcImc.setOnClickListener {
            if (!isValidFields()) {
                Toast.makeText(this, R.string.fields_message, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val imc = calcImc(weight, height)
            val result = showResult(imc)

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.imc_response, imc))
                .setMessage(getString(result))
                .setPositiveButton(android.R.string.ok) { _: DialogInterface, _: Int ->
                    Log.d("CLICK", "OK")
                }
                .setNegativeButton(R.string.save) { _: DialogInterface, _: Int ->
                    Thread {
                        Log.d("CLICK", "SAVE")
                        val app = application as App
                        val dao = app.db.calcDAO()
                        val updateId = intent.extras?.getInt("update_id")
                        if (updateId != null) {
                            dao.update(Calc(id = updateId, type = "imc", res = imc))
                        } else {
                            dao.insert(Calc(type = "imc", res = imc))
                        }
                        runOnUiThread {
                            openListaCalcActivity()
                        }
                    }.start()
                }
                .create()
                .show()
            val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }

        /* Hide keyboard */
        val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            finish()
            openListaCalcActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isValidFields(): Boolean {
        return (editWeight.text.toString().isNotEmpty()
                && editHeight.text.toString().isNotEmpty()
                && !editWeight.text.toString().startsWith("0")
                && !editHeight.text.toString().startsWith("0"))
    }

    private fun calcImc(weight: Int, height: Int): Double {
        return weight / ((height / 100.0) * (height / 100.0))
    }

    @StringRes
    private fun showResult(result: Double): Int {
        return when {
            result < 15.0 -> R.string.imc_severely_low_weight
            result < 16.0 -> R.string.imc_very_low_weight
            result < 18.5 -> R.string.imc_low_weight
            result < 25.0 -> R.string.normal
            result < 30.0 -> R.string.imc_high_weight
            result < 35.0 -> R.string.imc_so_high_weight
            result < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
    }
    private fun openListaCalcActivity() {
        val intent = Intent(this, ListaCalcActivity::class.java)
        intent.putExtra("type", "imc")
        startActivity(intent)
    }
}
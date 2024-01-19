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
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import co.tiagoaguiar.fitnesstracker.model.Calc

class TmbActivity : AppCompatActivity() {

    private lateinit var editHeight: EditText
    private lateinit var editWeight: EditText
    private lateinit var editAge: EditText
    private lateinit var lifestyle: AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb)

        editHeight = findViewById(R.id.edit_tmb_height)
        editWeight = findViewById(R.id.edit_tmb_weight)
        editAge = findViewById(R.id.edit_tmb_age)

        val btnCalcImc: Button = findViewById(R.id.btn_tmb)
        btnCalcImc.setOnClickListener {
            if (!isValidFields()) {
                Toast.makeText(this, R.string.fields_message, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val age = editAge.text.toString().toInt()
            val tmb = calcTmb(weight, height, age)
            val result = showResult(tmb)

            AlertDialog.Builder(this)
                .setMessage(getString(R.string.tmb_response, result))
                .setPositiveButton(android.R.string.ok) { _: DialogInterface, _: Int ->
                    Log.d("CLICK", "OK")
                }
                .setNegativeButton(R.string.save) { _: DialogInterface, _: Int ->
                    Thread {
                        Log.d("CLICK", "SAVE")
                        val app = application as App
                        val dao = app.db.calcDAO()
                        val updateId = intent.extras?.getInt("updateId")
                        if (updateId != null) {
                            dao.update(Calc(id = updateId, type = "tmb", res = result))
                        } else {
                            dao.insert(Calc(type = "tmb", res = result))
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
        /*Adaptar AutoCompleteTextView com base no array de itens*/
        lifestyle = findViewById(R.id.auto_lifestyle)
        val items = resources.getStringArray(R.array.tmb_lifestyle)
        lifestyle.setText(items.first())
        val adapter = ArrayAdapter(this, R.layout.lista_calc_item, items)
        lifestyle.setAdapter(adapter)
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

    private fun showResult(tmb: Double): Double {
        val items = resources.getStringArray(R.array.tmb_lifestyle)
        return when(items.indexOf(lifestyle.text.toString())){
            0 -> tmb * 1.2
            1 -> tmb * 1.375
            2 -> tmb * 1.55
            3 -> tmb * 1.725
            4 -> tmb * 1.9
            else -> 0.0
        }
    }

    private fun calcTmb(weight: Int, height: Int, age: Int): Double {
        return 66 + (13.8 * weight) + (5 * height) - (6.8 * age)
    }

    private fun isValidFields(): Boolean {
        return (editHeight.text.toString().isNotEmpty()
                && editWeight.text.toString().isNotEmpty()
                && editAge.text.toString().isNotEmpty()
                && !editHeight.text.toString().startsWith("0")
                && !editWeight.text.toString().startsWith("0")
                && !editAge.text.toString().startsWith("0"))
    }

    private fun openListaCalcActivity() {
        val intent = Intent(this, ListaCalcActivity::class.java)
        intent.putExtra("type", "tmb")
        startActivity(intent)
    }
}

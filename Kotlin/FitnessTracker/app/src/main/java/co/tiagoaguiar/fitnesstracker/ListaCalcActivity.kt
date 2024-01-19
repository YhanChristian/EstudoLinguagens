package co.tiagoaguiar.fitnesstracker

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.fitnesstracker.model.Calc
import java.text.SimpleDateFormat
import java.util.Locale

class ListaCalcActivity : AppCompatActivity(), OnListClickListener {
    private lateinit var rvCalc: RecyclerView
    private lateinit var adapter: ListaCalcAdapter
    private lateinit var result: MutableList<Calc>

    companion object {
        const val TAG = "ListaCalcActivity"
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_calc)

        result = mutableListOf<Calc>()
        adapter = ListaCalcAdapter(result, this)
        rvCalc = findViewById(R.id.rv_lista_calc)
        rvCalc.layoutManager = LinearLayoutManager(this)
        rvCalc.adapter = adapter

        val type =
            intent?.extras?.getString("type") ?: throw IllegalStateException("type not found")

        Thread {
            val app = application as App
            val dao = app.db.calcDAO()
            val response = dao.getRegisterByType(type)
            runOnUiThread {
                result.addAll(response)
                adapter.notifyDataSetChanged()
            }
        }.start()

    }

    /*Cria o Adapter da RecyclerView*/
    private inner class ListaCalcAdapter(
        private val listaCalcItems: List<Calc>,
        private val listener: OnListClickListener
    ) : RecyclerView.Adapter<ListaCalcAdapter.ListaCalcViewHolder>() {

        /*Cria o ViewHolder da RecyclerView*/
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaCalcViewHolder {
            return ListaCalcViewHolder(
                layoutInflater.inflate(
                    R.layout.lista_calc_item,
                    parent,
                    false
                )
            )
        }

        /*Retorna a quantidade de itens da RecyclerView*/
        override fun getItemCount(): Int {
            return listaCalcItems.size
        }

        /*Busca referência de cada item da RecyclerView*/
        private inner class ListaCalcViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            @SuppressLint("SimpleDateFormat")
            fun bind(item: Calc) {
                val tv = itemView as TextView
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale("pt", "BR"))
                val res = item.res
                val createdDate = sdf.format(item.createdDate)
                tv.text = getString(R.string.list_response, res, createdDate)
                tv.setOnLongClickListener {
                    listener.onListLongClick(adapterPosition, item)
                    true
                }
                tv.setOnClickListener {
                    listener.onListClick(item.id, item.type)
                }
            }
        }

        /*Preenche cada item da RecyclerView*/
        override fun onBindViewHolder(holder: ListaCalcViewHolder, position: Int) {
            val itemCurrent = listaCalcItems[position]
            holder.bind(itemCurrent)
        }
    }

    override fun onListClick(id: Int, type: String) {
        when (type) {
            "imc" -> {
                val intent = Intent(this, ImcActivity::class.java)
                intent.putExtra("update_id", id)
                startActivity(intent)
            }

            "tmb" -> {
                val intent = Intent(this, TmbActivity::class.java)
                intent.putExtra("update_id", id)
                startActivity(intent)
            }
        }
        finish()
    }

    override fun onListLongClick(position: Int, calc: Calc) {
        AlertDialog.Builder(this)
            .setMessage(R.string.delete_message)
            .setNegativeButton(android.R.string.cancel) { _: DialogInterface, _: Int ->
                Log.d("CLICK", "CANCEL")
            }
            .setPositiveButton(android.R.string.ok) { _: DialogInterface, _: Int ->
                Thread {
                    val app = application as App
                    val dao = app.db.calcDAO()
                    val response = dao.delete(calc)
                    if (response > 0) {
                        runOnUiThread {
                            result.removeAt(position)
                            adapter.notifyItemRemoved(position)
                        }
                    }
                }.start()
            }
            .create()
            .show()
    }
}
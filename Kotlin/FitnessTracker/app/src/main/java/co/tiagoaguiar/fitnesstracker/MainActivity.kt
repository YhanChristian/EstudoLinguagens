package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView

    companion object {
        const val TAG = "MainActivity"
        const val GRID_SIZE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                1,
                R.drawable.baseline_wb_sunny_24,
                R.string.imc,
                R.color.light_gray
            )
        )
        mainItems.add(MainItem(2, R.drawable.baseline_balance_24, R.string.tmb, R.color.light_gray))

        val adapter = MainAdapter(mainItems) { id ->
            Log.i(TAG, "Clicou no item: $id")
            when (id) {
                1 -> {
                    val intent = Intent(this, ImcActivity::class.java)
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(this, TmbActivity::class.java)
                    startActivity(intent)
                }

                else -> {
                    Log.e(TAG, "Item desconhecido: $id")
                }
            }
        }
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = GridLayoutManager(this, GRID_SIZE)

    }

    /*Cria o Adapter da RecyclerView*/
    private inner class MainAdapter(
        private val mainItems: List<MainItem>,
        private val onItemClickListener: (Int) -> Unit,
    ) :
        RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

        /*Cria o ViewHolder da RecyclerView*/
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            return MainViewHolder(layoutInflater.inflate(R.layout.main_item, parent, false))
        }

        /*Retorna a quantidade de itens da RecyclerView*/
        override fun getItemCount(): Int {
            return mainItems.size
        }

        /*Busca referência de cada item da RecyclerView*/
        private inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: MainItem) {
                val img: ImageView = itemView.findViewById(R.id.item_img_icon)
                val name: TextView = itemView.findViewById(R.id.item_txt_name)
                val container: LinearLayout = itemView.findViewById(R.id.item_container_imc)

                img.setImageResource(item.drawableId)
                name.setText(item.textStringId)
                container.setBackgroundColor(item.color)

                container.setOnClickListener {
                    /* Chama função onClickItem passando o ID do item clicado */
                    onItemClickListener.invoke(item.id)
                }
            }
        }

        /*Preenche cada item da RecyclerView*/
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItems[position]
            holder.bind(itemCurrent)
        }
    }
}
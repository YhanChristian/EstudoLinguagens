package co.tiagoaguiar.fitnesstracker

import co.tiagoaguiar.fitnesstracker.model.Calc

interface OnListClickListener {
    fun onListClick(id: Int, type: String)
    fun onListLongClick(position: Int, calc: Calc)
}
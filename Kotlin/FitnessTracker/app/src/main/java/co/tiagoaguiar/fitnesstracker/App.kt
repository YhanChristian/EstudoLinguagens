package co.tiagoaguiar.fitnesstracker

import android.app.Application
import android.util.Log
import co.tiagoaguiar.fitnesstracker.model.AppDatabase

class App : Application() {
    lateinit var  db : AppDatabase
    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDatabase(this)
        Log.d("App", "onCreate")
        Log.d("App", "db: $db")
    }
}
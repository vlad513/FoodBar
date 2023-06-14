package com.foodbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.foodbar.connect.ConnectivityObserver
import com.foodbar.connect.NetworkConnectivityObserver
import com.foodbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity  : AppCompatActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        lifecycleScope.launch {
            connect()
        }
    }

    suspend fun connect() {

        val snackbar = Snackbar.make(
            binding.NavHostFragvent,
            "Нет подключения к сети",
            Snackbar.LENGTH_INDEFINITE
        )
        connectivityObserver.observe().collect {
            when (it.toString()) {
                "Available" -> {
                    snackbar.dismiss()
                }
                "Lost" -> {
                    snackbar.show()
                }

            }
        }
    }
}
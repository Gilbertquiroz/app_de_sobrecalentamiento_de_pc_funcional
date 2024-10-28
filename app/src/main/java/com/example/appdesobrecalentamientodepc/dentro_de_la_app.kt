package com.example.appdesobrecalentamientodepc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class dentro_de_la_app : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dentro_de_la_app)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        try {
            val nombre: String = intent.getStringExtra("nombre").toString()
            Toast.makeText(this, "Bienvenido $nombre", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }

        val btnRegresar = findViewById<Button>(R.id.btnRegresar)
        val imgCpu = findViewById<ImageView>(R.id.ImgCpu)
        val imgVen = findViewById<ImageView>(R.id.ImgVen)

        btnRegresar.setOnClickListener {
            finish()
        }

        imgCpu.setOnClickListener {
            val intent = Intent(this, interfaz_cpu::class.java)
            startActivity(intent)
        }

        imgVen.setOnClickListener {
            val intent = Intent(this, intefaz_de_ventiladores::class.java)
            startActivity(intent)
        }
    }
}

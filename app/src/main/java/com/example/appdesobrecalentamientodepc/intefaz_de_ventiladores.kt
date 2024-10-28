package com.example.appdesobrecalentamientodepc

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class intefaz_de_ventiladores : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intefaz_de_ventiladores)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEncender: Button = findViewById(R.id.btnEncender)
        btnEncender.setOnClickListener {
            Toast.makeText(this, "Iniciando...", Toast.LENGTH_SHORT).show()
        }

        val btnRegresar: Button = findViewById(R.id.btnRegresar)
        btnRegresar.setOnClickListener {
            finish()
        }
    }
}

package com.example.appdesobrecalentamientodepc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class interfaz_cpu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_interfaz_cpu)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnIniciar: Button = findViewById(R.id.btnIniciar)
        btnIniciar.setOnClickListener {
            Toast.makeText(this, "Iniciando...", Toast.LENGTH_SHORT).show()
        }
        val btnAgregarDatos = findViewById<Button>(R.id.btnAgregarDatos)

        btnAgregarDatos.setOnClickListener {
            val intent = Intent(this, datosCpu::class.java)
            startActivity(intent)
        }


        val btnDetener: Button = findViewById(R.id.btnDetener)
        btnDetener.setOnClickListener {
            Toast.makeText(this, "Deteniendo...", Toast.LENGTH_SHORT).show()


            val btnRegresar: Button = findViewById(R.id.btnRegresar)
            btnRegresar.setOnClickListener {
                finish()
            }
        }
    }
}
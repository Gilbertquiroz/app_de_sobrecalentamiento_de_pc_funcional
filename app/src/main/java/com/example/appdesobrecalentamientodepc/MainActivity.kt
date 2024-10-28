package com.example.appdesobrecalentamientodepc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)

        btnIniciarSesion.setOnClickListener {
            navigateToApp()
            btnIniciarSesion.setOnClickListener {
                try {
                    val nombre: String = findViewById<EditText>(R.id.usuario).text.toString()
                    val clave: String =
                        findViewById<EditText>(R.id.clave).text.toString()  // Cambio a `clave`

                    Log.e("Nombre", nombre)
                    Log.e("Clave", clave)

                    if (nombre.isNotEmpty() && clave.isNotEmpty()) {  // Ambos campos deben tener contenido
                        val intent = Intent(this, dentro_de_la_app::class.java)
                        intent.putExtra("nombre", nombre)
                        startActivity(intent)
                    } else {
                        // Mensaje de error si falta el nombre o la clave
                        if (nombre.isEmpty()) {
                            Toast.makeText(this, "No ingresaste tu nombre", Toast.LENGTH_SHORT)
                                .show()
                        } else if (clave.isEmpty()) {
                            Toast.makeText(this, "No ingresaste tu clave", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
    }

            private fun navigateToApp() {

    }
}

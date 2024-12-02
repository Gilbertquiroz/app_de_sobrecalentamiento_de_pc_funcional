package com.example.appdesobrecalentamientodepc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdesobrecalentamientodepc.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    // Configuración de ViewBinding
    private lateinit var binding: ActivityMainBinding

    // Configurar Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)

        // Iniciar ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajustar insets de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Iniciar Firebase Auth
        auth = Firebase.auth

        // Configurar el botón de inicio de sesión
        binding.btnIniciarSesion.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty()) {
                binding.etEmail.error = "Por favor, ingrese un correo"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Por favor, ingrese la contraseña"
                return@setOnClickListener
            }

            signIn(email, password)
        }

        //programar el enlace para registrarse
        binding.tvRegistrarse.setOnClickListener{
            try {
                val intent = Intent(this,RegistrarActivity::class.java)
                startActivity(intent)
            } catch (e: Exception){
                Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }



    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                    //para cambiar de pantalla
                    val intent = Intent(this, dentro_de_la_app::class.java)
                    startActivity(intent)


                } else {
                    Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_LONG).show()
                }
            }
    }
}

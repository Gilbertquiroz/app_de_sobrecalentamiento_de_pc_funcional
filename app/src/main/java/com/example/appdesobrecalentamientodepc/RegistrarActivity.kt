package com.example.appdesobrecalentamientodepc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdesobrecalentamientodepc.databinding.ActivityMainBinding
import com.example.appdesobrecalentamientodepc.databinding.ActivityRegistrarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrarActivity : AppCompatActivity() {

    // Configuración de ViewBinding
    private lateinit var binding: ActivityRegistrarBinding

    // Configurar Firebase Auth
    private lateinit var auth: FirebaseAuth


        private fun singUp(email: String, pass1: String) {
        auth.createUserWithEmailAndPassword(email,pass1)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "usuario registrado", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "error en el registro", Toast.LENGTH_SHORT).show()

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Iniciar ViewBinding
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Iniciar Firebase Auth
        auth = Firebase.auth

        binding.btnRegistrar.setOnClickListener{
            //obtener el correo
            val email = binding.etEmail.text.toString()
            val pass1 = binding.etPassword.text.toString()
            val pass2 = binding.etPassword2.text.toString()

            if (email.isEmpty()){
                binding.etEmail.error = "porfavor  ingrese un correo"
                return@setOnClickListener
            }

            if (pass1.isEmpty()){
                binding.etPassword.error = "porfavor  ingrese una contraseña"
                return@setOnClickListener
            }

            if (pass2.isEmpty()){
                binding.etPassword2.error = "porfavor confirma la contraseña"
                return@setOnClickListener
            }

            // Validar que ambas contraseñas coincidan
            if (pass1 != pass2) {
                binding.etPassword2.error = "Las contraseñas no coinciden"
                return@setOnClickListener
            } else {
                singUp(email,pass1)
            }



        }



    }
}
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
import com.example.appdesobrecalentamientodepc.databinding.ActivityDentroDeLaAppBinding
import com.example.appdesobrecalentamientodepc.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class dentro_de_la_app : AppCompatActivity() {

    //configurar el viewbinding
    private lateinit var binding: ActivityDentroDeLaAppBinding

    //configurar firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //inicar el binding
        binding = ActivityDentroDeLaAppBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setContentView(R.layout.activity_dentro_de_la_app)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //iniciar  fire base auth
        auth = Firebase.auth

        binding.btnRegresar.setOnClickListener{

            MaterialAlertDialogBuilder(this)
                .setTitle("cerrar sesion")
                .setMessage("estas seguro que deseas cerrar sesion?")
                .setNeutralButton("cancelar") { dialog, which ->
                    // Respond to neutral button press
                }
                .setPositiveButton("aceptar") { dialog, which ->
                    singOut() //cerrar sesion
                }
                .show()
        }

        try {
            val nombre: String = intent.getStringExtra("nombre").toString()
            Toast.makeText(this, "sesion cerrada", Toast.LENGTH_SHORT).show()
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

    private fun singOut() {
        Firebase.auth.signOut()
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_LONG).show()
        finish()
    }

}

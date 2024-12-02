package com.example.appdesobrecalentamientodepc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdesobrecalentamientodepc.databinding.ActivityDatosCpuBinding
import com.example.appdesobrecalentamientodepc.databinding.ActivityMainBinding
import com.example.appdesobrecalentamientodepc.models.dato
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class datosCpu : AppCompatActivity() {

    private lateinit var binding: ActivityDatosCpuBinding

    //activar el firebase realtime database
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding =  ActivityDatosCpuBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //iniciar base de datos

        database = FirebaseDatabase.getInstance().getReference("DATOS")

        binding.btnGuardar.setOnClickListener{
            //obtener los datos

            val temperatura = binding.etNombreProducto.text.toString()
            val velocidad = binding.etPrecioProducto.text.toString()
            val estado = binding.etDescripcionProducto.text.toString()
            //generar el id ramdon

            val id = database.child("DATOS").push().key

            if (temperatura.isEmpty()){
                binding.etNombreProducto.error = "por favor ingresa una temperatura"
                return@setOnClickListener
            }
            if (velocidad.isEmpty()){
                binding.etPrecioProducto.error = "por favor ingresa una velocidad"
                return@setOnClickListener
            }
            if (estado.isEmpty()){
                binding.etDescripcionProducto.error = "por favor ingresa un estado"
                return@setOnClickListener
            }


            val dato = dato (id,temperatura,velocidad,estado)

            database.child(id!!).setValue(dato)
                .addOnSuccessListener{
                    binding.etNombreProducto.setText("")
                    binding.etPrecioProducto.setText("")
                    binding.etDescripcionProducto.setText("")
                    Snackbar.make(binding.root, "dato agregado", Snackbar.LENGTH_SHORT).show()
                }

        }


    }
}
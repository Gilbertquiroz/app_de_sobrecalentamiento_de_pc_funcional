package com.example.appdesobrecalentamientodepc

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class datosCpu : AppCompatActivity() {
    private lateinit var editTextTemperatura: EditText
    private lateinit var editTextVelocidad: EditText
    private lateinit var editTextEstado: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button
    private lateinit var listView: ListView

    private val cpuData = mutableListOf<String>()
    private var selectedPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_datos_cpu)

        editTextTemperatura = findViewById(R.id.editTextTemperatura)
        editTextVelocidad = findViewById(R.id.editTextVelocidad)
        editTextEstado = findViewById(R.id.editTextEstado)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnActualizar = findViewById(R.id.btnActualizar)
        btnEliminar = findViewById(R.id.btnEliminar)
        listView = findViewById(R.id.listView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cpuData)
        listView.adapter = adapter

        btnAgregar.setOnClickListener {
            val temperatura = editTextTemperatura.text.toString()
            val velocidad = editTextVelocidad.text.toString()
            val estado = editTextEstado.text.toString()

            if (temperatura.isNotEmpty() && velocidad.isNotEmpty() && estado.isNotEmpty()) {
                val cpuInfo = "Temperatura: $temperatura, Velocidad: $velocidad, Estado: $estado"
                if (selectedPosition != null) {
                    cpuData[selectedPosition!!] = cpuInfo
                    selectedPosition = null
                } else {
                    cpuData.add(cpuInfo)
                }
                adapter.notifyDataSetChanged()
                clearFields()
            }
        }

        btnActualizar.setOnClickListener {
            selectedPosition?.let { position ->
                val temperatura = editTextTemperatura.text.toString()
                val velocidad = editTextVelocidad.text.toString()
                val estado = editTextEstado.text.toString()

                if (temperatura.isNotEmpty() && velocidad.isNotEmpty() && estado.isNotEmpty()) {
                    cpuData[position] = "Temperatura: $temperatura, Velocidad: $velocidad, Estado: $estado"
                    adapter.notifyDataSetChanged()
                    clearFields()
                    selectedPosition = null
                }
            }
        }

        btnEliminar.setOnClickListener {
            selectedPosition?.let { position ->
                cpuData.removeAt(position)
                adapter.notifyDataSetChanged()
                clearFields()
                selectedPosition = null
            }
        }


        listView.setOnItemClickListener { parent, view, position, id ->
            selectedPosition = position
            val selectedItem = cpuData[position]

            val parts = selectedItem.split(", ")
            if (parts.size == 3) {
                editTextTemperatura.setText(parts[0].substringAfter(": ").trim())
                editTextVelocidad.setText(parts[1].substringAfter(": ").trim())
                editTextEstado.setText(parts[2].substringAfter(": ").trim())
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnVolver: Button = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun clearFields() {
        editTextTemperatura.text.clear()
        editTextVelocidad.text.clear()
        editTextEstado.text.clear()
    }
}

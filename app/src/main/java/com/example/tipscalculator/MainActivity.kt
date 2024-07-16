package com.example.tipscalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.tipscalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var percentage = 0
        // Configure percentage based on RadioGroup selection
        binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 10
            }
        }

        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 15
            }
        }

        binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 20
            }
        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.num_people,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerNumberOfPeople.adapter = adapter

        var numOfPeopleSelected = 1 // Inicializando com 1 para evitar divisão por zero
        binding.spinnerNumberOfPeople.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    numOfPeopleSelected = position + 1 // Ajustar para considerar o número de pessoas corretamente
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    numOfPeopleSelected = 1 // Valor padrão para evitar erros
                }

            }

        // Calculate button
        binding.btnDone.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text

            if (totalTableTemp?.isEmpty() == true) {
                Snackbar
                    .make(binding.tieTotal, "Please fill in all fields.", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val nPeople: Int = numOfPeopleSelected

                if (nPeople == 0) {
                    Snackbar
                        .make(
                            binding.tieTotal,
                            "Number of people cannot be zero.",
                            Snackbar.LENGTH_LONG
                        )
                        .show()
                } else {
                    val tips = totalTable * percentage / 100 // Calcular gorjeta em cima do total da mesa
                    val totalWithTips = totalTable + tips // Adicionar gorjeta ao total da mesa
                    val totalPerPerson = totalWithTips / nPeople // Dividir o total com gorjeta por pessoa

                    //para abrir uma proxima tela abrimos algo chamado intent
                    //Explicita - diz o destino
                    //Impricita - nao diz o destino
                    //(this) activity, ele pede quem esta chamando, sempre que for this e a main activity.
                    //a intent e depois putextras, intent e um pacote de coisas que temos que por extras nele.

                    val intent = Intent(this, SummaryActivity::class.java) // chamando
                    intent.apply {
                        putExtra("totalTable", totalTable)
                        putExtra("numPeople", numOfPeopleSelected)
                        putExtra("percentage", percentage)
                        putExtra("totalAmount", totalPerPerson)
                    }
                    startActivity(intent) //iniciando

                }
            }
        }

        binding.btnClean.setOnClickListener {
            binding.tieTotal.setText("")
            binding.rbOptionOne.isChecked = false
            binding.rbOptionTwo.isChecked = false
            binding.rbOptionThree.isChecked = false
            binding.spinnerNumberOfPeople.setSelection(0) // Resetar o spinner
        }
    }
}















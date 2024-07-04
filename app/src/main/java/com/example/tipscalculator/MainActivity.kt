package com.example.tipscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipscalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    //Valor total da conta
    //Numero de pessoas
    //Porcentagem da gorjeta
    // 10%, 15% or 20%
    //Calcular
    //Limpar

    //Recuper as views do layout
    //Viewbinding
    //Recuper os radio buttons
    //Calculo de tips
    //Mostrar os resultados

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var percentage = 0
        binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
            println("Roque1 Option one:$isChecked")
            if (isChecked) {
                percentage = 10
            }
        }

        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            println("Roque1 Option Two:$isChecked")
            if (isChecked) {
                percentage = 15
            }
        }

        binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
            println("Roque1 Option Three: $isChecked")
            if (isChecked) {
                percentage = 20
            }
        }

        binding.btnClean.setOnClickListener {
            println("Roque1 " + binding.tieTotal.text)
            println("Roque1 " + binding.tieNumPeople.text)
        }

        binding.btnDone.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text
            val nPeopleTemp = binding.tieNumPeople.text

            if (totalTableTemp?.isEmpty() == true ||
                nPeopleTemp?.isEmpty() == true
            ) {
                Snackbar.make(binding.tieTotal, "Please fill in all fields.", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val nPeople: Int = nPeopleTemp.toString().toInt()

                val totalTemp = totalTable / nPeople
                val tips = totalTemp * percentage / 100
                val totalWithTips = totalTemp + tips
                "Total with tips: $totalWithTips".also {
                    binding.tvResult.text = it

                }
            }
        }
    }
}














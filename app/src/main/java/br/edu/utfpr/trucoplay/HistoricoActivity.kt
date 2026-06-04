package br.edu.utfpr.trucoplay

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoricoActivity : AppCompatActivity() {

    private lateinit var tvPlayer1: TextView
    private lateinit var tvPlayer2: TextView
    private lateinit var tvPlacar1: TextView
    private lateinit var tvPlacar2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvPlayer1 = findViewById(R.id.tvPlayer1)
        tvPlayer2 = findViewById(R.id.tvPlayer2)
        tvPlacar1 = findViewById(R.id.tvPlacar1)
        tvPlacar2 = findViewById(R.id.tvPlacar2)

        val nome1 = intent.getStringExtra("NOME_JOGADOR_1")
        if (!nome1.isNullOrEmpty()) {
            tvPlayer1.text = nome1
        }

        val nome2 = intent.getStringExtra("NOME_JOGADOR_2")
        if (!nome2.isNullOrEmpty()) {
            tvPlayer2.text = nome2
        }

        val vit1 = intent.getIntExtra("VITORIAS_1", 0)
        val vit2 = intent.getIntExtra("VITORIAS_2", 0)

        tvPlacar1.text = vit1.toString()
        tvPlacar2.text = vit2.toString()
    }

    fun btVoltarOnClick(view: View) {
        finish()
    }
}
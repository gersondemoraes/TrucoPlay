package br.edu.utfpr.trucoplay

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MarcacaoActivity : AppCompatActivity() {

    private lateinit var etJogador1: EditText
    private lateinit var etJogador2: EditText
    private lateinit var tvPlacar1: TextView
    private lateinit var tvPlacar2: TextView

    private var placar1 = 0
    private var placar2 = 0
    private var vitorias1 = 0
    private var vitorias2 = 0

    // Launcher para receber o resultado da NomesActivity
    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val nome1 = data?.getStringExtra("NOME_JOGADOR_1")
            val nome2 = data?.getStringExtra("NOME_JOGADOR_2")

            if (!nome1.isNullOrEmpty()) etJogador1.setText(nome1.uppercase())
            if (!nome2.isNullOrEmpty()) etJogador2.setText(nome2.uppercase())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_marcacao)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etJogador1 = findViewById(R.id.etJogador1)
        etJogador2 = findViewById(R.id.etJogador2)
        tvPlacar1 = findViewById(R.id.tvPlacar1)
        tvPlacar2 = findViewById(R.id.tvPlacar2)

        atualizarPlacar()
    }

    private fun atualizarPlacar() {
        tvPlacar1.text = placar1.toString()
        tvPlacar2.text = placar2.toString()
        
        // Regra: se chegar a 12 (ou >= 11 conforme seu pedido), conta vitória e zera rodada
        if (placar1 >= 12) {
            vitorias1++
            placar1 = 0
            placar2 = 0
            atualizarPlacar()
        } else if (placar2 >= 12) {
            vitorias2++
            placar1 = 0
            placar2 = 0
            atualizarPlacar()
        }
    }

    fun btMaisUmOnClick(view: View) {
        placar1 += 1
        atualizarPlacar()
    }

    fun btMaisTresOnClick(view: View) {
        placar1 += 3
        atualizarPlacar()
    }

    fun btMaisSeisOnClick(view: View) {
        placar1 += 6
        atualizarPlacar()
    }

    fun btMaisDozeOnClick(view: View) {
        placar1 += 12
        atualizarPlacar()
    }

    fun btMaisUmJogador2OnClick(view: View) {
        placar2 += 1
        atualizarPlacar()
    }

    fun btMaisTresJogador2OnClick(view: View) {
        placar2 += 3
        atualizarPlacar()
    }

    fun btMaisSeisJogador2OnClick(view: View) {
        placar2 += 6
        atualizarPlacar()
    }

    fun btMaisDozeJogador2OnClick(view: View) {
        placar2 += 12
        atualizarPlacar()
    }

    fun btHistoricoOnClick(view: View) {
        val intent = Intent(this, HistoricoActivity::class.java)
        intent.putExtra("NOME_JOGADOR_1", etJogador1.text.toString())
        intent.putExtra("NOME_JOGADOR_2", etJogador2.text.toString())
        intent.putExtra("VITORIAS_1", vitorias1)
        intent.putExtra("VITORIAS_2", vitorias2)
        startActivity(intent)
    }

    fun btZerarOnClick(view: View) {
        placar1 = 0
        placar2 = 0
        vitorias1 = 0
        vitorias2 = 0
        atualizarPlacar()
    }

    fun btInformarNomes(view: View) {
        val intent = Intent(this, NomesActivity::class.java)
        getResult.launch(intent)
    }
}

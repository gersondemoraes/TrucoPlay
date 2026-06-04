package br.edu.utfpr.trucoplay

// Importações necessárias para os componentes de UI e lógica da Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Classe responsável pela tela que exibe o resumo de vitórias e nomes dos jogadores
class HistoricoActivity : AppCompatActivity() {

    // Declaração das variáveis que representarão os nomes dos jogadores na tela
    private lateinit var tvPlayer1: TextView
    private lateinit var tvPlayer2: TextView
    // Declaração das variáveis que representarão o contador de vitórias totais na tela
    private lateinit var tvPlacar1: TextView
    private lateinit var tvPlacar2: TextView

    // Método principal de criação da Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita a visualização de tela cheia, integrando com as barras do sistema
        enableEdgeToEdge()
        // Define o layout XML que esta Activity deve carregar
        setContentView(R.layout.activity_historico)
        
        // Ajusta as margens da view principal para não sobrepor as barras de status e navegação
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Conecta as variáveis de código aos componentes visuais do XML usando os IDs
        tvPlayer1 = findViewById(R.id.tvPlayer1)
        tvPlayer2 = findViewById(R.id.tvPlayer2)
        tvPlacar1 = findViewById(R.id.tvPlacar1)
        tvPlacar2 = findViewById(R.id.tvPlacar2)

        // Recupera o nome do Jogador 1 enviado pela tela de Marcação através da Intent
        val nome1 = intent.getStringExtra("NOME_JOGADOR_1")
        // Se o nome recebido não for nulo ou vazio, atualiza o texto na tela
        if (!nome1.isNullOrEmpty()) {
            tvPlayer1.text = nome1
        }

        // Recupera o nome do Jogador 2 enviado pela tela de Marcação através da Intent
        val nome2 = intent.getStringExtra("NOME_JOGADOR_2")
        // Se o nome recebido não for nulo ou vazio, atualiza o texto na tela
        if (!nome2.isNullOrEmpty()) {
            tvPlayer2.text = nome2
        }

        // Recupera a quantidade de vitórias do Jogador 1 (valor padrão 0 se não encontrar)
        val vit1 = intent.getIntExtra("VITORIAS_1", 0)
        // Recupera a quantidade de vitórias do Jogador 2 (valor padrão 0 se não encontrar)
        val vit2 = intent.getIntExtra("VITORIAS_2", 0)

        // Converte os valores numéricos de vitórias para texto e exibe nos respectivos TextViews
        tvPlacar1.text = vit1.toString()
        tvPlacar2.text = vit2.toString()
    }

    // Método acionado pelo clique no botão "Voltar"
    fun btVoltarOnClick(view: View) {
        // Encerra a atividade atual e retorna para a tela anterior (MarcacaoActivity)
        finish()
    }
}

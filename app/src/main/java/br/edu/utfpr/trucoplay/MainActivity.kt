package br.edu.utfpr.trucoplay

// Importações necessárias para gerenciar navegação entre telas e componentes do Android
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Classe principal que representa a tela de abertura do aplicativo
class MainActivity : AppCompatActivity() {

    // Método chamado quando a atividade é criada pela primeira vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Ativa o suporte para exibição de conteúdo atrás das barras de status e navegação
        enableEdgeToEdge()
        
        // Define o arquivo de layout XML (activity_main.xml) que será carregado nesta tela
        setContentView(R.layout.activity_main)
        
        // Configura um listener para ajustar o padding da view principal conforme as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Obtém as dimensões das barras do sistema (barra de status e de navegação)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Aplica o recuo (padding) necessário para o conteúdo não ficar escondido atrás das barras
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            // Retorna os insets processados
            insets
        }
    }

    // Método disparado quando o usuário clica no botão para começar o jogo
    fun btComeceJogarOnClick(view: View) {
        // Cria uma Intent indicando que deseja sair da MainActivity e ir para a MarcacaoActivity
        val intent = Intent(this, MarcacaoActivity::class.java)
        // Inicia a nova tela (transição de tela)
        startActivity(intent)
    }
}

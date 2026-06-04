package br.edu.utfpr.trucoplay

// Importações necessárias para gerenciar dados entre telas e componentes de interface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Classe responsável pela tela onde o usuário informa os nomes dos jogadores
class NomesActivity : AppCompatActivity() {

    // Declaração das variáveis para os campos de entrada de texto
    private lateinit var etPlayerOne: EditText
    private lateinit var etPlayerTwo: EditText

    // Método disparado ao criar a tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita o design que ocupa toda a tela do dispositivo
        enableEdgeToEdge()
        // Define o layout XML que será desenhado nesta tela
        setContentView(R.layout.activity_nomes)
        
        // Ajusta o padding para que o conteúdo não fique sob as barras de status/navegação
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Conecta as variáveis de código aos IDs definidos no arquivo activity_nomes.xml
        etPlayerOne = findViewById(R.id.etPlayerOne)
        etPlayerTwo = findViewById(R.id.etPlayerTwo)

        // Define que o cursor de digitação começará automaticamente no campo do Jogador 1
        etPlayerOne.requestFocus()
    }

    // Método acionado pelo clique no botão "Confirmar"
    fun btConfirmarOnClick(view: View) {
        // Cria uma Intent vazia para carregar os dados de retorno
        val intent = Intent()
        // Adiciona o texto digitado no campo do Jogador 1 à Intent
        intent.putExtra("NOME_JOGADOR_1", etPlayerOne.text.toString())
        // Adiciona o texto digitado no campo do Jogador 2 à Intent
        intent.putExtra("NOME_JOGADOR_2", etPlayerTwo.text.toString())
        
        // Informa ao sistema que o resultado foi bem-sucedido e anexa a Intent com os nomes
        setResult(RESULT_OK, intent)
        // Fecha esta tela e volta para a tela de marcação, devolvendo os dados
        finish()
    }
}

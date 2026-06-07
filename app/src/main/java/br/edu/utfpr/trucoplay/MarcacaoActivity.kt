package br.edu.utfpr.trucoplay

// Importações necessárias para o funcionamento da Activity e seus componentes
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Classe principal da tela de marcação de pontos
class MarcacaoActivity : AppCompatActivity() {

    // Declaração das variáveis para os campos de entrada de texto (nomes dos jogadores)
    private lateinit var etJogador1: EditText
    private lateinit var etJogador2: EditText
    // Declaração das variáveis para os campos de exibição de texto (placar atual)
    private lateinit var tvPlacar1: TextView
    private lateinit var tvPlacar2: TextView

    // Variáveis inteiras para armazenar a pontuação da rodada atual
    private var placar1 = 0
    private var placar2 = 0
    // Variáveis inteiras para armazenar o total de partidas vencidas
    private var vitorias1 = 0
    private var vitorias2 = 0

    // Launcher responsável por iniciar a NomesActivity e processar os nomes retornados por ela
    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Verifica se a tela anterior retornou com sucesso (RESULT_OK)
        if (result.resultCode == RESULT_OK) {
            // Obtém os dados da intent retornada
            val data: Intent? = result.data
            // Extrai os nomes enviados pela NomesActivity
            val nome1 = data?.getStringExtra("NOME_JOGADOR_1")
            val nome2 = data?.getStringExtra("NOME_JOGADOR_2")

            // Se o nome não estiver vazio, preenche o campo do Jogador 1 em letras maiúsculas
            if (!nome1.isNullOrEmpty()) {
                etJogador1.setText(nome1.uppercase())
                etJogador1.error = null // Limpa o erro ao receber um nome
            }
            // Se o nome não estiver vazio, preenche o campo do Jogador 2 em letras maiúsculas
            if (!nome2.isNullOrEmpty()) {
                etJogador2.setText(nome2.uppercase())
                etJogador2.error = null // Limpa o erro ao receber um nome
            }
        }
    }

    // Método chamado na criação da Activity (quando a tela é aberta)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita o modo de tela cheia (borda a borda)
        enableEdgeToEdge()
        // Define o layout XML que será exibido nesta tela
        setContentView(R.layout.activity_marcacao)
        // Configura o preenchimento da tela para respeitar as barras do sistema (status bar e navegação)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Vincula as variáveis aos elementos visuais definidos no arquivo XML através dos seus IDs
        etJogador1 = findViewById(R.id.etJogador1)
        etJogador2 = findViewById(R.id.etJogador2)
        tvPlacar1 = findViewById(R.id.tvPlacar1)
        tvPlacar2 = findViewById(R.id.tvPlacar2)

        // Chama a função para exibir os valores iniciais (zero) no placar
        atualizarPlacar()
    }

    // Função interna que atualiza os textos dos placares na tela e verifica condições de vitória
    private fun atualizarPlacar() {
        // Define o texto do placar do Jogador 1 com o valor atual da variável
        tvPlacar1.text = placar1.toString()
        // Define o texto do placar do Jogador 2 com o valor atual da variável
        tvPlacar2.text = placar2.toString()

        // Verifica se o Jogador 1 atingiu a pontuação de 11 pontos ou mais
        if (placar1 >= 11) {
            // Incrementa o contador de vitórias totais do Jogador 1
            vitorias1++
            // Pega o nome digitado ou usa "Jogador 1" como padrão para o anúncio de vitória
            val nomeVencedor = etJogador1.text.toString().ifEmpty { getString(R.string.Jogador1) }
            // Exibe o pop-up parabenizando o vencedor
            mostrarPopupVitoria(nomeVencedor)
            // Reseta o placar da rodada atual para ambos os jogadores
            placar1 = 0
            placar2 = 0
            // Atualiza visualmente os zeros na tela
            tvPlacar1.text = placar1.toString()
            tvPlacar2.text = placar2.toString()
        } 
        // Verifica se o Jogador 2 atingiu a pontuação de 11 pontos ou mais
        else if (placar2 >= 11) {
            // Incrementa o contador de vitórias totais do Jogador 2
            vitorias2++
            // Pega o nome digitado ou usa "Jogador 2" como padrão para o anúncio de vitória
            val nomeVencedor = etJogador2.text.toString().ifEmpty { getString(R.string.Jogador2) }
            // Exibe o pop-up parabenizando o vencedor
            mostrarPopupVitoria(nomeVencedor)
            // Reseta o placar da rodada atual para ambos os jogadores
            placar1 = 0
            placar2 = 0
            // Atualiza visualmente os zeros na tela
            tvPlacar1.text = placar1.toString()
            tvPlacar2.text = placar2.toString()
        }
    }

    // Função que cria e exibe uma caixa de diálogo informativa de vitória
    private fun mostrarPopupVitoria(nome: String) {
        // Construtor do AlertDialog vinculado ao contexto desta tela
        val builder = AlertDialog.Builder(this)
        // Define o título da caixa de diálogo
        builder.setTitle(getString(R.string.fim_da_partida))
        // Define a mensagem central com o nome do vencedor
        builder.setMessage(getString(R.string.venceu, nome))
        // Define o botão de confirmação que fecha o pop-up ao ser clicado
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        // Cria a instância do diálogo a partir das configurações acima
        val dialog = builder.create()
        // Faz o diálogo aparecer na tela do usuário
        dialog.show()
    }

    // Método disparado ao clicar no botão "+1" do Jogador 1
    fun btMaisUmOnClick(view: View) {
        placar1 += 1 // Aumenta 1 ponto
        if (etJogador1.text.toString().trim().isEmpty()) {
            etJogador1.error = getString(R.string.erro_nome_vazio)
            return
        }
        atualizarPlacar() // Reflete a mudança na tela e checa vitória
    }

    // Método disparado ao clicar no botão "+3" do Jogador 1
    fun btMaisTresOnClick(view: View) {
        placar1 += 3 // Aumenta 3 pontos
        if (etJogador1.text.toString().trim().isEmpty()) {
            etJogador1.error = getString(R.string.erro_nome_vazio)
            return
        }
        atualizarPlacar() // Reflete a mudança na tela e checa vitória
    }

    // Método disparado ao clicar no botão "+6" do Jogador 1
    fun btMaisSeisOnClick(view: View) {
        placar1 += 6 // Aumenta 6 pontos
        if (etJogador1.text.toString().trim().isEmpty()) {
            etJogador1.error = getString(R.string.erro_nome_vazio)
            return
        }
        atualizarPlacar() // Reflete a mudança na tela e checa vitória
    }

    // Método disparado ao clicar no botão "+12" do Jogador 1
    fun btMaisDozeOnClick(view: View) {
        placar1 += 12 // Aumenta 12 pontos (vitória instantânea)
        if (etJogador1.text.toString().trim().isEmpty()) {
            etJogador1.error = getString(R.string.erro_nome_vazio)
            return
        }
        atualizarPlacar() // Reflete a mudança na tela e checa vitória
    }

    // Método disparado ao clicar no botão "+1" do Jogador 2
    fun btMaisUmJogador2OnClick(view: View) {
        placar2 += 1 // Aumenta 1 ponto
        if (etJogador1.text.toString().trim().isEmpty()) {
            etJogador1.error = getString(R.string.erro_nome_vazio)
            return
        }
        atualizarPlacar() // Reflete a mudança na tela e checa vitória
    }

    // Método disparado ao clicar no botão "+3" do Jogador 2
    fun btMaisTresJogador2OnClick(view: View) {
        placar2 += 3 // Aumenta 3 pontos
        if (etJogador1.text.toString().trim().isEmpty()) {
            etJogador1.error = getString(R.string.erro_nome_vazio)
            return
        }
        atualizarPlacar() // Reflete a mudança na tela e checa vitória
    }

    // Método disparado ao clicar no botão "+6" do Jogador 2
    fun btMaisSeisJogador2OnClick(view: View) {
        placar2 += 6 // Aumenta 6 pontos
        if (etJogador1.text.toString().trim().isEmpty()) {
            etJogador1.error = getString(R.string.erro_nome_vazio)
            return
        }
        atualizarPlacar() // Reflete a mudança na tela e checa vitória
    }

    // Método disparado ao clicar no botão "+12" do Jogador 2
    fun btMaisDozeJogador2OnClick(view: View) {
        placar2 += 12 // Aumenta 12 pontos (vitória instantânea)
        if (etJogador1.text.toString().trim().isEmpty()) {
            etJogador1.error = getString(R.string.erro_nome_vazio)
            return
        }
        atualizarPlacar() // Reflete a mudança na tela e checa vitória
    }

    // Método disparado ao clicar no botão de Histórico
    fun btHistoricoOnClick(view: View) {
        // Valida se os nomes foram preenchidos
        if (etJogador1.text.toString().trim().isEmpty()) {
            etJogador1.error = getString(R.string.erro_nome_vazio)
            return
        }
        if (etJogador2.text.toString().trim().isEmpty()) {
            etJogador2.error = getString(R.string.erro_nome_vazio)
            return
        }

        // Cria um objeto Intent para navegar até a HistoricoActivity
        val intent = Intent(this, HistoricoActivity::class.java)
        // Adiciona o nome do Jogador 1 para ser exibido no histórico
        intent.putExtra("NOME_JOGADOR_1", etJogador1.text.toString())
        // Adiciona o nome do Jogador 2 para ser exibido no histórico
        intent.putExtra("NOME_JOGADOR_2", etJogador2.text.toString())
        // Adiciona a contagem de vitórias do Jogador 1
        intent.putExtra("VITORIAS_1", vitorias1)
        // Adiciona a contagem de vitórias do Jogador 2
        intent.putExtra("VITORIAS_2", vitorias2)
        // Inicia a nova tela enviando todos esses dados
        startActivity(intent)
    }

    // Método disparado ao clicar no botão de Zerar Placar
    fun btZerarOnClick(view: View) {
        placar1 = 0 // Reinicia pontos da rodada do J1
        placar2 = 0 // Reinicia pontos da rodada do J2
        vitorias1 = 0 // Reinicia contador total de vitórias do J1
        vitorias2 = 0 // Reinicia contador total de vitórias do J2
        atualizarPlacar() // Atualiza a interface gráfica
    }

    // Método disparado ao clicar no botão de Informar Nomes
    fun btInformarNomes(view: View) {
        // Cria a Intent para abrir a tela de entrada de nomes
        val intent = Intent(this, NomesActivity::class.java)
        // Lança a atividade esperando que ela retorne um resultado (os nomes)
        getResult.launch(intent)
    }
}

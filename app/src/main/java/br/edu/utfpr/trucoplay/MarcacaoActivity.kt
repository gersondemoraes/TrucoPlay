    package br.edu.utfpr.trucoplay

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MarcacaoActivity : AppCompatActivity() {

    private lateinit var etJogador1: EditText
    private lateinit var etJogador2: EditText

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
    }

    fun btMaisUmOnClick(view: View) {}
    fun btMaisTresOnClick(view: View) {}
    fun btMaisSeisOnClick(view: View) {}
    fun btMaisDozeOnClick(view: View) {}
    fun btMaisUmJogador2OnClick(view: View) {}
    fun btMaisTresJogador2OnClick(view: View) {}
    fun btMaisSeisJogador2OnClick(view: View) {}
    fun btMaisDozeJogador2OnClick(view: View) {}
    fun btHistoricoOnClick(view: View) {}
    fun btZerarOnClick(view: View) {}
    fun btInformarNomes(view: View) {}
}
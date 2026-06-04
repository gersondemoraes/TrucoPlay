package br.edu.utfpr.trucoplay

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NomesActivity : AppCompatActivity() {

    private lateinit var etPlayerOne: EditText
    private lateinit var etPlayerTwo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nomes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etPlayerOne = findViewById(R.id.etPlayerOne)
        etPlayerTwo = findViewById(R.id.etPlayerTwo)

        etPlayerOne.requestFocus()
    }

    fun btConfirmarOnClick(view: View) {
        val intent = Intent()
        intent.putExtra("NOME_JOGADOR_1", etPlayerOne.text.toString())
        intent.putExtra("NOME_JOGADOR_2", etPlayerTwo.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}
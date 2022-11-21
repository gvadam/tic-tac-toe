package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var isPlayer1 = true
    var board = arrayOf<String>("", "", "", "", "", "", "", "", "")
    var x: String = "X"
    var o: String = "O"
    var player1Points = 0
    var player2Points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    fun init() {
        binding.apply {
            player1PtDisplay.text = player1Points.toString()
            player2PtDisplay.text = player2Points.toString()

            btn1.setOnClickListener{ addLetter(btn1, 0) }
            btn2.setOnClickListener{ addLetter(btn2, 1) }
            btn3.setOnClickListener{ addLetter(btn3, 2) }
            btn4.setOnClickListener{ addLetter(btn4, 3) }
            btn5.setOnClickListener{ addLetter(btn5, 4) }
            btn6.setOnClickListener{ addLetter(btn6, 5) }
            btn7.setOnClickListener{ addLetter(btn7, 6) }
            btn8.setOnClickListener{ addLetter(btn8, 7) }
            btn9.setOnClickListener{ addLetter(btn9, 8) }

            newGameBtn.visibility = View.INVISIBLE
            restartBtn.setOnClickListener { restartGame() }
        }
    }

    private fun addLetter(btn: Button, num: Int) {
        if(isPlayer1){
            btn.text = x
            btn.setTextColor(getColor(R.color.yellow))
            board[num] = x
            isPlayer1 = false
            checkWinner(x)
        } else {
            btn.text = o
            btn.setTextColor(getColor(R.color.aqua))
            board[num] = o
            isPlayer1 = true
            checkWinner(o)
        }
        btn.isEnabled = false
    }

    private fun checkWinner(str: String) {
        if (board[0] == str && board[1] == str && board[2] == str){
            addPoints(str)
        } else if (board[3] == str && board[4] == str && board[5] == str) {
            addPoints(str)
        } else if (board[6] == str && board[7] == str && board[8] == str) {
            addPoints(str)
        } else if (board[0] == str && board[4] == str && board[8] == str) {
            addPoints(str)
        } else if (board[2] == str && board[4] == str && board[6] == str) {
            addPoints(str)
        } else if (!board.contains("")) {
            Toast.makeText(this,"Tie", Toast.LENGTH_LONG).show()
            binding.newGameBtn.visibility = View.VISIBLE
        }
    }

    private fun addPoints(str: String) {
        if(str == x){
            player1Points++
            binding.player1PtDisplay.text = player1Points.toString()
            Toast.makeText(this,"Player 1 won!", Toast.LENGTH_LONG).show()
        } else {
            player2Points++
            binding.player2PtDisplay.text = player2Points.toString()
            Toast.makeText(this,"Player 2 won!", Toast.LENGTH_LONG).show()
        }
        binding.newGameBtn.visibility = View.VISIBLE
        binding.newGameBtn.setOnClickListener{
            newGame()
        }
    }

    fun newGame() {
        binding.apply {
            btn1.text = ""
            btn2.text = ""
            btn3.text = ""
            btn4.text = ""
            btn5.text = ""
            btn6.text = ""
            btn7.text = ""
            btn8.text = ""
            btn9.text = ""

            btn1.isEnabled = true
            btn2.isEnabled = true
            btn3.isEnabled = true
            btn4.isEnabled = true
            btn5.isEnabled = true
            btn6.isEnabled = true
            btn7.isEnabled = true
            btn8.isEnabled = true
            btn9.isEnabled = true

            newGameBtn.visibility = View.INVISIBLE
        }
        isPlayer1 = true
        board = arrayOf<String>("", "", "", "", "", "", "", "", "")
    }

    fun restartGame(){
        newGame()
        var player1Points = 0
        var player2Points = 0
        binding.player1PtDisplay.text = player1Points.toString()
        binding.player2PtDisplay.text = player2Points.toString()
    }

}
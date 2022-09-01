package com.example.thecontest

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LIFECYCLE", "onCreate")

        val mediaPlayer = MediaPlayer.create(this, R.raw.bugle_tune)

        val scoreDisplay = findViewById<TextView>(R.id.score)
        savedInstanceState?.let {
            score = it.getInt("SCORE")
        }

        scoreDisplay.text = score.toString()

        val scoreButton = findViewById<Button>(R.id.scoreButton)
        scoreButton.setOnClickListener {
            score = if (score in 0..14) score + 1 else score
            displayScoreWithColor(score, scoreDisplay)

            if (score == 15) {
                mediaPlayer.start()
            }
        }

        val stealButton = findViewById<Button>(R.id.stealButton)
        stealButton.setOnClickListener {
            score = if (score in 1..15) score-1 else score
            displayScoreWithColor(score, scoreDisplay)
        }

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            score = 0
            displayScoreWithColor(score, scoreDisplay)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SCORE", score)
        Log.i("LIFECYCLE", "savedInstanceState")
    }

    private fun displayScoreWithColor(score: Int, display: TextView) {
        display.text = score.toString()
        if (score in 5..9) {
            display.setTextColor(Color.parseColor("#0000FF"))
        } else if (score in 10..15) {
            display.setTextColor(Color.parseColor("#00FF00"))
        }
    }
}
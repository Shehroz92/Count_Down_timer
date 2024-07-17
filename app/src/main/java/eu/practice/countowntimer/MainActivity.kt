package eu.practice.countowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var countDownTimer: CountDownTimer? = null
    private var timerDuration: Long = 60000
    private var pauseOffset: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvTimer: TextView = findViewById(R.id.timer)
        val btnStart: TextView = findViewById(R.id.button_start)
        val btnPause: TextView = findViewById(R.id.button_pause)
        val btnReset: TextView = findViewById(R.id.button_reset)

        tvTimer.text = (timerDuration / 1000).toString()

        btnStart.setOnClickListener {
            startTimer(pauseOffset)
        }

        btnPause.setOnClickListener {
            pauseTimer()
        }

        btnReset.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer(pauseOffsetL: Long) {
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffsetL, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished
                val tvTimer: TextView = findViewById(R.id.timer)
                tvTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                pauseOffset = 0
                val tvTimer: TextView = findViewById(R.id.timer)
                tvTimer.text = "0"
            }
        }.start()
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
    }

    private fun resetTimer() {
        countDownTimer?.cancel()
        pauseOffset = 0
        val tvTimer: TextView = findViewById(R.id.timer)
        tvTimer.text = (timerDuration / 1000).toString()
    }
}

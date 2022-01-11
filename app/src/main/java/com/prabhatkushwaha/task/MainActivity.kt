package com.prabhatkushwaha.task

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Runnable as Runnable1


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    var current_pos:Double = 0.0
    var total_duration:Double = 0.0
    val MEDIA_PATH = "https://max-masti.com//video_urls//ID1550123456_1632398405606.mp4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val videoView = findViewById<View>(R.id.vdVw) as VideoView
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        val uri: Uri = Uri.parse(MEDIA_PATH)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val seekbar = findViewById<SeekBar>(R.id.seekbar)
        progressbar.visibility = View.VISIBLE
        videoView.setOnPreparedListener { mp ->
            mp.start()
            seekbar.max = (mp.duration)
            mp.setOnVideoSizeChangedListener { mp, arg1, arg2 -> // TODO Auto-generated method stub
                Log.e(TAG, "Changed")
                progressbar.visibility = View.GONE
                mp.start()
            }
            val handler = Handler()

            val runnable: Runnable1 = object : Runnable1 {
                override fun run() {
                    try {
                        current_pos = videoView.currentPosition.toDouble()
                        seekbar.progress = current_pos.toInt()
                        handler.postDelayed(this, 1000)
                    } catch (ed: IllegalStateException) {
                        ed.printStackTrace()
                    }
                }
            }
            handler.postDelayed(runnable, 1000)
            seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    current_pos = seekBar.progress.toDouble()
                    videoView.seekTo(current_pos.toInt())
                }
            })
        }

    }

    //time conversion
    fun timeConversion(value: Long): String? {
        val songTime: String
        val dur = value.toInt()
        val hrs = dur / 3600000
        val mns = dur / 60000 % 60000
        val scs = dur % 60000 / 1000
        songTime = if (hrs > 0) {
            String.format("%02d:%02d:%02d", hrs, mns, scs)
        } else {
            String.format("%02d:%02d", mns, scs)
        }
        return songTime
    }
}
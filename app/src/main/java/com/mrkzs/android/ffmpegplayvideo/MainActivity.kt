package com.mrkzs.android.ffmpegplayvideo

import android.os.Bundle
import android.text.TextUtils
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val urlEt = findViewById<EditText>(R.id.url)
        val playBtn = findViewById<Button>(R.id.play)
        val surfaceView = findViewById<SurfaceView>(R.id.surface)
        val holder : SurfaceHolder = surfaceView.holder
        playBtn.setOnClickListener { view ->
            if (view.id == R.id.play) {
                if(!TextUtils.isEmpty(urlEt.text.trim())) {
                    Thread(Runnable {
                        playVideo(urlEt.text.trim().toString(), holder.surface)
                    }).start()

                }
            }
        }
    }

    //本地文件，网络视频，流
    external fun playVideo(url : String, surface: Surface)

    companion object {
        init {
            System.loadLibrary("video")
        }
    }
}

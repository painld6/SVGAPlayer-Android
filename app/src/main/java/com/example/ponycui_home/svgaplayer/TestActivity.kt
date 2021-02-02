package com.example.ponycui_home.svgaplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity

class TestActivity : AppCompatActivity() {

    var entity: SVGAVideoEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        SVGAParser(this).decodeFromAssets("music_lost.svga", object : SVGAParser.ParseCompletion {
            override fun onComplete(videoItem: SVGAVideoEntity) {
                entity = videoItem
                setupSVGAImageView()
            }

            override fun onError() {
            }
        })
    }

    fun setupSVGAImageView() {
        for (i in 1..12) {
            val id = resources.getIdentifier("svga$i", "id", packageName)
            val svga = findViewById<SVGAImageView>(id)
            svga.setVideoItem(entity!!)
            svga.stepToFrame(2, false)
            svga.setOnClickListener {
                if (svga.isAnimating) {
                    svga.stopAnimation()
                } else {
                    svga.startAnimation()
                }
            }
            svga.loops = 1
            svga.clearsAfterDetached = false
            svga.clearsAfterStop = false
        }
    }
    
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, TestActivity::class.java)
        }
    }
}
package com.socket9.sunsilk.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.socket9.sunsilk.R
import com.socket9.sunsilk.extensions.toast
import kotlinx.android.synthetic.main.activity_video.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.share

class VideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    /** Variable zone **/
    val API_KEY: String = "AIzaSyDW_S3EcuMBH58q6h1sY05XtOf4jGoykAM"

    /** Lifecycle method zone **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        initInstance()
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        toast("Error")
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if (!wasRestored) {
            player?.loadVideo(intent.extras.getString("url"));
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    /** Method zone **/
    private fun initInstance() {
        videoView.initialize(API_KEY, this);
        btnBack.onClick {
            finish()
        }

        btnShare.onClick {
            share("ตอนนี้ \"Jennifer Wilson\" ปลดล็อกวีดีโอตัวใหม่จากการสะสมแต้มได้แล้วดาว์นโหลดแอปแล้วมาสะสมแต้มเพื่อปลดล็อกวีดีโอสุดเอ็กซ์คลูซีฟกันเถอะ", "Sunsilk video")
        }
    }

}

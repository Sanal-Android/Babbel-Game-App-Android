package com.team.smileapplication.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import com.team.smileapplication.R
import com.team.smileapplication.ui.login.LoginActivity


class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       this.window.statusBarColor = ContextCompat.getColor(this, R.color.exo_white)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed(Runnable {
            val i = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)

    }
}
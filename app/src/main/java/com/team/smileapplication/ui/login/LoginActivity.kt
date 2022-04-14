package com.team.smileapplication.ui.login

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.team.smileapplication.R
import com.team.smileapplication.ui.BaseActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class LoginActivity : BaseActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}

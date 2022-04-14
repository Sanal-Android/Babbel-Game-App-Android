package com.team.smileapplication


import android.app.Activity
import android.app.Application
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.squareup.leakcanary.LeakCanary
import com.team.smileapplication.di.AppInjector

import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class SmileApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>



    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
       // Log.setLogNode(LogWrapper())


        val config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build()

        Fresco.initialize(this, config)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}

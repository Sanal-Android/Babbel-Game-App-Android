/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.team.smileapplication.di

import android.app.Application
import androidx.room.Room
import com.team.smileapplication.common.LiveDataCallAdapterFactory
import com.team.smileapplication.data.api.WebService
import com.team.smileapplication.data.db.AppDb
import com.team.smileapplication.data.db.UMSDao
import com.team.smileapplication.utils.AppConst

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    private val BASE_URL = "https://gist.githubusercontent.com/DroidCoder/"



    @Singleton @Provides
    fun provideWebService(app: Application): WebService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)

                .build()


        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(WebService::class.java)
    }

    @Singleton @Provides
    fun provideDb(app: Application): AppDb {
        return Room
                .databaseBuilder(app, AppDb::class.java, AppConst.DATABASE)
                .fallbackToDestructiveMigration()
                .build()
    }


    @Singleton @Provides
    fun provideUMSDao(db: AppDb): UMSDao {
        return db.umsDao()
    }

    @Singleton @Provides
    fun getBaseUrl(): String {
        return BASE_URL
    }
}

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

package com.team.smileapplication.data.api

import androidx.lifecycle.LiveData
import com.team.smileapplication.data.model.Result
import com.team.smileapplication.utils.AppConst

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


/**
 * REST API access points
 */
interface WebService {

    @GET("7ac6cdb4bf5e032f4c737aaafe659b33/raw/baa9fe0d586082d85db71f346e2b039c580c5804/words.json")
    fun getInfo(): LiveData<ApiResponse<List<Result>>>


}

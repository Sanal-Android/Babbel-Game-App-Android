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

package com.team.smileapplication.repo

import androidx.lifecycle.LiveData

import com.team.smileapplication.AppExecutors
import com.team.smileapplication.data.api.Resource
import com.team.smileapplication.data.api.WebService
import com.team.smileapplication.data.db.AppDb
import com.team.smileapplication.data.db.UMSDao
import com.team.smileapplication.data.model.Result
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles User instances.
 *
 */
@Singleton
class UMSRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val db: AppDb,
        private val umsoDao: UMSDao,
        private val webService: WebService
) {
    //ApiResponse<BaseResponse<List<User>>>





    fun getInfo(): LiveData<Resource<List<Result>>> {
        return object : NetworkBoundResource<List<Result>>(appExecutors) {
            override fun createCall() = webService.getInfo(

            )

        }.asLiveData()
    }


}

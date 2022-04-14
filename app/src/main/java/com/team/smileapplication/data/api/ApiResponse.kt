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

import com.team.smileapplication.BuildConfig
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import java.util.regex.Pattern




/**
 * separate class for HTTP 204 resposes so that we can make ApiSuccessResponse's body non-null.
 */
@Suppress("unused") // T is used in extending classes
open class ApiResponse<T> {
    companion object {
        fun <T> create(throwable: Throwable): Responses.ApiErrorResponse<T> {

            var message = "Network error occured while connecting"

            if (throwable is SocketTimeoutException) {

                message = "Network error occured while connecting"
                if (!BuildConfig.DEBUG) {
                    if (throwable.localizedMessage != null) {
                        //  message = message + "\n" + throwable.localizedMessage
                    } else if (throwable.cause != null && throwable.cause!!.localizedMessage != null) {
                        //  message = message + "\n" + throwable.cause!!.localizedMessage
                    }
                } else {

                    if (throwable.cause != null) {
                        if (throwable.cause!!.message != null) {
                            //  message = throwable.cause!!.message!!
                        }
                    } else if (throwable.message != null) {
                        //   message = throwable.message!!
                    }
                }

            } else if (throwable is ConnectException) {

                message = "Network error occured while connecting"
                if (!BuildConfig.DEBUG) {
                    if (throwable.localizedMessage != null) {
                        //   message = message + "\n" + throwable.localizedMessage
                    } else if (throwable.cause != null && throwable.cause!!.localizedMessage != null) {
                        //  message = message + "\n" + throwable.cause!!.localizedMessage
                    }
                } else {
                    if (throwable.cause != null) {
                        if (throwable.cause!!.message != null) {
                            //   message = throwable.cause!!.message!!
                        }
                    } else if (throwable.message != null) {
                        //   message = throwable.message!!
                    }
                }

            } else if (throwable is UnknownServiceException) {

                message = "Network error occured while connecting"
                if (!BuildConfig.DEBUG) {
                    if (throwable.localizedMessage != null) {
                        //  message = message + "\n" + throwable.localizedMessage
                    } else if (throwable.cause != null && throwable.cause!!.localizedMessage != null) {
                        //  message = message + "\n" + throwable.cause!!.localizedMessage
                    }
                } else {
                    if (throwable.cause != null) {
                        if (throwable.cause!!.message != null) {
                            //    message = throwable.cause!!.message!!
                        }
                    } else if (throwable.message != null) {
                        //  message = throwable.message!!
                    }
                }

            } else if (throwable is UnknownHostException) {

                message = "Network error occured while connecting"
                if (!BuildConfig.DEBUG) {
                    if (throwable.localizedMessage != null) {
                        //   message = message + "\n" + throwable.localizedMessage
                    } else if (throwable.cause != null && throwable.cause!!.localizedMessage != null) {
                        //   message = message + "\n" + throwable.cause!!.localizedMessage
                    }
                } else {
                    if (throwable.cause != null) {
                        if (throwable.cause!!.message != null) {
                            //    message = throwable.cause!!.message!!
                        }
                    } else if (throwable.message != null) {
                        //  message = throwable.message!!
                    }
                }

            } else {

                if (!BuildConfig.DEBUG) {
                    if (throwable.localizedMessage != null) {
                        //   message = message + "\n" + throwable.localizedMessage
                    } else if (throwable.cause != null && throwable.cause!!.localizedMessage != null) {
                        //   message = message + "\n" + throwable.cause!!.localizedMessage
                    }
                } else {
                    if (throwable.cause != null) {
                        if (throwable.cause!!.message != null) {
                            //  message = throwable.cause!!.message!!
                        }
                    } else if (throwable.message != null) {
                        //  message = throwable.message!!
                    }
                }

            }

            return Responses.ApiErrorResponse(message)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    Responses.ApiEmptyResponse()
                } else {
                    Responses.ApiSuccessResponse(
                            body = body,
                            linkHeader = response.headers()?.get("link")
                    )
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                Responses.ApiErrorResponse(errorMsg ?: "Network error occured while connecting")
            }
        }
    }
}

package com.team.smileapplication.data.api



import com.google.gson.annotations.SerializedName


/**
 * Base Gson class structure of all api responses.
 */

data class BaseResponse<T>(

        @field:SerializedName("function")
        val function: String,

        @field:SerializedName("status")
        val status: Boolean,

        @field:SerializedName("result")
        val data: T?

) {
    fun isSuccess(): Boolean {

        return status
    }
}
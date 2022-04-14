package com.team.smileapplication.data.model

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class Result(

        @field:SerializedName("text_eng")
        var txt_eng: String? = "",

        @NonNull
        @field:SerializedName("text_spa")
        var txt_spa: String = ""

)
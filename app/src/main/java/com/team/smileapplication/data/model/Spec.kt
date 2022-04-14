package com.team.smileapplication.data.model

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class Spec(

        @field:SerializedName("score")
        var score: Float? ,

        @NonNull
        @field:SerializedName("species")
        var species: Species?


)
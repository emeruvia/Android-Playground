package dev.emg.mvi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogPost(

    @Expose
    @SerializedName("pk")
    val pk: Int? = null,

    @Expose
    @SerializedName("title")
    val title: Int? = null,

    @Expose
    @SerializedName("body")
    val body: Int? = null,

    @Expose
    @SerializedName("image")
    val image: Int? = null

) {
    override fun toString(): String {
        return "BlogPost(pk=$pk, title=$title, body=$body, image=$image)"
    }
}
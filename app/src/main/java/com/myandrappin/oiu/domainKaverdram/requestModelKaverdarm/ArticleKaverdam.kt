package com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm

import com.google.gson.annotations.SerializedName

data class ArticleKaverdam(
    @SerializedName("author")
    val authorKaverdam: String,
    @SerializedName("content")
    val contentKaverdam: String?,
    @SerializedName("description")
    val descriptionKaverdam: String?,
    @SerializedName("publishedAt")
    val publishedAtKaverdam: String,
    @SerializedName("source")
    val sourceKaverdam: SourceKaverdam,
    @SerializedName("title")
    val titleKaverdam: String,
    @SerializedName("url")
    val urlKaverdam: String,
    @SerializedName("urlToImage")
    val urlToImageKaverdam: String
)
package com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm

import com.google.gson.annotations.SerializedName

data class ResponceModelKaverdarm(
    @SerializedName("articles")
    val articlesKaverdarm: List<ArticleKaverdam>,
    @SerializedName("status")
    val statusKaverdarm: String,
    @SerializedName("totalResults")
    val totalResultsKaverdarm: Int
)
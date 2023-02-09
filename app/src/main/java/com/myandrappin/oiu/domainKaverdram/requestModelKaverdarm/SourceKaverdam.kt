package com.myandrappin.oiu.domainKaverdram.requestModelKaverdarm

import com.google.gson.annotations.SerializedName

data class SourceKaverdam(
    @SerializedName("id")
    var idKaverdam: Any,
    @SerializedName("name")
    val nameKaverdam: String
)
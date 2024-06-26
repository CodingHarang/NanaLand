package com.jeju.nanaland.domain.entity.nanapick

import com.google.gson.annotations.SerializedName

data class NanaPickContentData(
    @SerializedName("subHeading")
    val subHeading: String?,
    @SerializedName("heading")
    val heading: String?,
    @SerializedName("originUrl")
    val originUrl: String?,
    @SerializedName("notice")
    val notice: String?,
    @SerializedName("nanaDetails")
    val nanaDetails: List<NanaPickSubContentData>,
    @SerializedName("favorite")
    val favorite: Boolean
)

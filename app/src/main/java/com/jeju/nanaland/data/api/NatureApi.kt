package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.response.nature.GetNatureContentResponse
import com.jeju.nanaland.domain.response.nature.GetNatureListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NatureApi {

    // 7대 자연 상세 정보 조회
    @GET("nature/{contentId}")
    suspend fun getNatureContent(
        @Path("contentId") id: Long,
        @Query("isSearch") isSearch: Boolean
    ): Response<GetNatureContentResponse>

    // 7대 자연 리스트 조회
    @GET("nature/list")
    suspend fun getNatureList(
        @Query("addressFilterList") addressFilterList: List<String>,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetNatureListResponse>
}
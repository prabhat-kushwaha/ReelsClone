package com.prabhatkushwaha.task.framework.datasource.network.service

import com.prabhatkushwaha.task.framework.datasource.network.response.ReelsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ReelService {

    @GET("/testing.php")
    suspend fun getReelsData(
        @Query("page") page: String = "1",
        @Query("userid") userid: String = "ID1549716343",
        @Query("category") category: String = "hindi",
    ): ReelsResponse
}
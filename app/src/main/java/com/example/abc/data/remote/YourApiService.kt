package com.example.abc.data.remote

import com.example.abc.domain.model.YourRequestType
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface YourApiService {
    @POST("endpoint")
    suspend fun getSomething(@Body data: YourRequestType): Response<List<YourRequestType>>

    @POST("submit")
    suspend fun submitData(@Body data: YourRequestType): Response<List<YourRequestType>>

}

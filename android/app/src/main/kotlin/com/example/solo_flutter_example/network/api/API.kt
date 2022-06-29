package com.example.solo_flutter_example.network.api

import com.example.solo_flutter_example.network.model.Model
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface API {

    @GET("v1/user/context")
    suspend fun getQuotes(@HeaderMap headerMap: Map<String, String>): Response<Model>
}
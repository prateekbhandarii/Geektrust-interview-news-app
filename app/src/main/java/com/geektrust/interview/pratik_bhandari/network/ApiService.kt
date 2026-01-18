package com.geektrust.interview.pratik_bhandari.network

import retrofit2.Response
import retrofit2.http.*

/**
 * API Service interface for Retrofit
 * Define your API endpoints here
 */
interface ApiService {

    // Example GET request
    // @GET("endpoint")
    // suspend fun getData(): Response<YourDataModel>

    // Example POST request
    // @POST("endpoint")
    // suspend fun postData(@Body requestBody: YourRequestModel): Response<YourResponseModel>

    // Example GET request with path parameter
    // @GET("endpoint/{id}")
    // suspend fun getDataById(@Path("id") id: String): Response<YourDataModel>

    // Example GET request with query parameter
    // @GET("endpoint")
    // suspend fun getDataWithQuery(@Query("param") param: String): Response<YourDataModel>
}

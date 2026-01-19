package com.geektrust.interview.pratik_bhandari.network

import com.geektrust.interview.pratik_bhandari.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("X-API-KEY", BuildConfig.API_KEY)
            .build()

        return chain.proceed(newRequest)
    }
}

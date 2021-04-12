package com.example.mobillab.repo.network

import android.annotation.SuppressLint
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject

class CharacterInterceptor : Interceptor {
    @SuppressLint("CheckResult")
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.isSuccessful) {
            val reader = JsonReader.of(response.body()!!.source())
            val newResponse = response.newBuilder()

            reader.beginObject()

            //skip info object from received json
            reader.nextName()
            reader.skipValue()

            //get results array
            reader.nextName()
            val resultsSource = reader.nextSource()

            val results = ResponseBody.create(null,resultsSource.readByteArray())
            return newResponse.body(results).build()
        }
        return response
    }
}
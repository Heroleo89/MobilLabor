package com.example.mobillab.repo.network

import android.annotation.SuppressLint
import com.squareup.moshi.Json
import com.squareup.moshi.JsonReader
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.Reader
import java.lang.Exception

class CharacterInterceptor : Interceptor {
    @SuppressLint("CheckResult")
    override fun intercept(chain: Interceptor.Chain): Response {
        var url = chain.request().url().toString()

        url = enforceFormatForUrl(url)

        val newRequest = chain.request().newBuilder().url(url).build()
        val response = chain.proceed(newRequest)

        if (response.isSuccessful) {

            val reader = JsonReader.of(response.body()!!.source())

            if (shouldTrimInfo(reader.peekJson()))
                return trimInfo(response, reader.peekJson())
        }
        return response
    }

    @SuppressLint("CheckResult")
    private fun trimInfo(
        response: Response,
        reader: JsonReader
    ): Response {
        val newResponse = response.newBuilder()

        reader.beginObject()

        //skip info object from received json
        reader.nextName()
        reader.skipValue()

        //get results array
        reader.nextName()
        val resultsSource = reader.nextSource()

        val results = ResponseBody.create(null, resultsSource.readByteArray())
        return newResponse.body(results).build()
    }

    private fun enforceFormatForUrl(url: String): String {
        return url.replace("[", "")
            .replace("]", "")
            .replace("%20", "")
    }

    private fun shouldTrimInfo(reader: JsonReader): Boolean{
       try {
           if (reader.peek() == JsonReader.Token.BEGIN_OBJECT) {
               val tmpReader = reader.peekJson()
               tmpReader.beginObject()
               return tmpReader.nextName() == "info"
           }
       }catch (e : Exception){
            println(e.message)
       }
        return false
    }
}
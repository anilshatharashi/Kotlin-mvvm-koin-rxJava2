package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.di

import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.data.source.api.SampleRESTApi
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.jsoup.Jsoup
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

const val URL = "https://developer.android.com/guide/platform/"

object ApiServiceFactory {

    fun createSampleRESTApiService(isDebug: Boolean): SampleRESTApi {
        val okHttpClient = createOkHttpClient(createLoggingInterceptor(false))
        return createSampleRESTApiService(okHttpClient)
    }

    private fun createSampleRESTApiService(okHttpClient: OkHttpClient): SampleRESTApi {
        return Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(HtmlPageConverter.FACTORY)
                .client(okHttpClient)
                .build().create(SampleRESTApi::class.java)
    }

    private fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    internal class HtmlPageConverter : Converter<ResponseBody, String> {

        @Throws(IOException::class)
        override fun convert(responseBody: ResponseBody): String {
            val document = Jsoup.parse(responseBody.string())
            val elements = document.select("p")
            return elements.text()
        }

        companion object {
            val FACTORY: Converter.Factory = object : Converter.Factory() {
                override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
                    return if (type == String::class.java) HtmlPageConverter() else null
                }
            }
        }
    }

}
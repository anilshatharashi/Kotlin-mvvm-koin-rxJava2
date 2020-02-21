package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.data.source.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface SampleRESTApi {

    @GET("/")
    fun getContent(): Single<Response<String>>
}

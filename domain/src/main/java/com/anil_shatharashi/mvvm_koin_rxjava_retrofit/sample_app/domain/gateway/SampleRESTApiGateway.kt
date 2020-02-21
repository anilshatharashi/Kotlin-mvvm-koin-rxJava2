package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.gateway

import io.reactivex.Single

interface SampleRESTApiGateway {
    fun getTenthCharacterFromEndPoint(): Single<Char>
    fun getEveryTenthCharacterFromEndPoint(): Single<String>
    fun getContentAndCountWords(): Single<String>
}
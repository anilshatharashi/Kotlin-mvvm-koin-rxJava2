package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.data.repository

import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.data.source.api.SampleRESTApi
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.gateway.SampleRESTApiGateway
import io.reactivex.Single
import java.lang.Exception

class SampleRESTApiRepository(private val sampleRESTApi: SampleRESTApi) : SampleRESTApiGateway {

    override fun getTenthCharacterFromEndPoint(): Single<Char> {
        return sampleRESTApi.getContent().flatMap { resp ->
            Single.create<String> {
                if (resp.body() != null) {
                    it.onSuccess(resp.body()!!)
                } else {
                    it.onError(Exception())
                }
            }
        }.map { it[9] }
    }

    override fun getEveryTenthCharacterFromEndPoint(): Single<String> {
        return sampleRESTApi.getContent().flatMap { resp ->
            Single.create<String> {
                if (resp.body() != null) {
                    it.onSuccess(resp.body()!!)
                } else {
                    it.onError(Exception())
                }
            }
        }.map { addEveryTenthCharToList(it) }
    }

    private fun addEveryTenthCharToList(original: String): String {
        val everyTenthChar = StringBuilder("{")
        var i = 1
        var j = 10
        while (j <= original.length) {
            everyTenthChar.append(original[j - 1]).append(", ")
            i++
            j = i * 10
        }
        return everyTenthChar.append(" }").toString()
    }

    override fun getContentAndCountWords(): Single<String> {
        return sampleRESTApi.getContent().flatMap { resp ->
            Single.create<String> {
                if (resp.body() != null) {
                    it.onSuccess(resp.body()!!)
                } else {
                    it.onError(Exception())
                }
            }
        }.map { splitIntoWords(it) }
    }

    private fun splitIntoWords(original: String): String =
            original.split("\\s".toRegex(RegexOption.IGNORE_CASE)).groupingBy { it }.eachCount().toString()
}
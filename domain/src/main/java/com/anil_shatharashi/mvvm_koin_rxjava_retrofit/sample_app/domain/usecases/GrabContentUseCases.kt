package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases

import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.executor.SchedulerProvider
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.gateway.SampleRESTApiGateway
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.base.SingleUsecase
import io.reactivex.Single

class GetTenthCharacterUseCase (private val getSampleRESTApiGateway: SampleRESTApiGateway, scheduler: SchedulerProvider)
    : SingleUsecase<Any, Char>(scheduler) {
    override fun buildUseCase(params: Any): Single<Char> = getSampleRESTApiGateway.getTenthCharacterFromEndPoint()
}

class GetEveryTenthCharacterUseCase (private val getSampleRESTApiGateway: SampleRESTApiGateway, scheduler: SchedulerProvider)
    : SingleUsecase<Any, String>(scheduler) {
    override fun buildUseCase(params: Any): Single<String> = getSampleRESTApiGateway.getEveryTenthCharacterFromEndPoint()
}

class GetContentAndCountWordsUseCase (private val getSampleRESTApiGateway: SampleRESTApiGateway, scheduler: SchedulerProvider)
    : SingleUsecase<Any, String>(scheduler) {
    override fun buildUseCase(params: Any): Single<String> = getSampleRESTApiGateway.getContentAndCountWords()
}
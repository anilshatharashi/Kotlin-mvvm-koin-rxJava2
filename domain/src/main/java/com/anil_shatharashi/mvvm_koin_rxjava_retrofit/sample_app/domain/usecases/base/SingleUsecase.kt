package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.base

import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.executor.SchedulerProvider
import io.reactivex.Single

abstract class SingleUsecase<in PARAMS, RESULT> (private val scheduler: SchedulerProvider) {

    abstract fun buildUseCase(params: PARAMS): Single<RESULT>

    fun execute(params: PARAMS): Single<RESULT> {
        return this.buildUseCase(params)
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread())
    }
}

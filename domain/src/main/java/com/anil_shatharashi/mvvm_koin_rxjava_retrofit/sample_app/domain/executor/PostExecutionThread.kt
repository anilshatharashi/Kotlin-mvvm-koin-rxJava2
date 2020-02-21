package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}
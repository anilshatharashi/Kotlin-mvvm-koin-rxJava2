package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.data.executor

import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.executor.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ThreadSchedulerProvider : SchedulerProvider {

    override fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun backgroundThread(): Scheduler {
        return Schedulers.io()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }
}
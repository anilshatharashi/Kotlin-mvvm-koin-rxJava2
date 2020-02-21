package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class SampleAppJUnitRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestMyApplication::class.java.name, context)
    }
}

package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation

import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.MyApplication
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.viewmodel.MainViewModel
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.viewmodels.MockMainViewModel
import io.mockk.mockk
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class TestMyApplication : MyApplication() {

    private val mockViewModelModule = module(override = true) {
        viewModel<MainViewModel> { MockMainViewModel(mockk(), mockk(), mockk()) }
    }

    private val testAppComponent: MutableList<Module> = mutableListOf(mockViewModelModule)

    override fun onCreate() {
        appComponent.addAll(testAppComponent)
        super.onCreate()
    }
}
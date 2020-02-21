package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.di

import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.BuildConfig
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.data.executor.ThreadSchedulerProvider
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.data.repository.SampleRESTApiRepository
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.executor.SchedulerProvider
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.gateway.SampleRESTApiGateway
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.GetContentAndCountWordsUseCase
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.GetEveryTenthCharacterUseCase
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.GetTenthCharacterUseCase
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
}

val useCasesModule = module {
    factory { GetTenthCharacterUseCase(get(), get()) }
    factory { GetEveryTenthCharacterUseCase(get(), get()) }
    factory { GetContentAndCountWordsUseCase(get(), get()) }
}

val domainModule = module {
    single { ThreadSchedulerProvider() as SchedulerProvider }
}

val dataModule = module {
    single(named("SampleRESTApi")) { ApiServiceFactory.createSampleRESTApiService(BuildConfig.DEBUG) }

    single<SampleRESTApiGateway> { SampleRESTApiRepository(get(named("SampleRESTApi"))) }

}
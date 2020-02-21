package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.GetContentAndCountWordsUseCase
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.GetEveryTenthCharacterUseCase
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.GetTenthCharacterUseCase
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.model.Resource

open class MainViewModel(private val getTenthCharacterUseCase: GetTenthCharacterUseCase,
                         private val getEveryTenthCharacterUseCase: GetEveryTenthCharacterUseCase,
                         private val getContentAndWordsUseCase: GetContentAndCountWordsUseCase)
    : RxViewModel() {
    val tenthCharLiveData = MutableLiveData<Resource<Char>>()
    val everyThenCharListLiveData = MutableLiveData<Resource<String>>()
    val contentAndWordsLiveData = MutableLiveData<Resource<String>>()

    open fun getData() {
        compositeDisposable.add(getTenthCharacterUseCase.execute(Any())
                .subscribe({ char ->
                    tenthCharLiveData.value = Resource.success(char)
                }, { tenthCharLiveData.value = Resource.error("Error in fetching 10th Char") }))

        compositeDisposable.add(getEveryTenthCharacterUseCase.execute(Any())
                .subscribe({ chars ->
                    everyThenCharListLiveData.value = Resource.success(chars)
                }, { everyThenCharListLiveData.value = Resource.error("Error in fetching Every 10th Char") }))

        compositeDisposable.add(getContentAndWordsUseCase.execute(Any())
                .subscribe({ words ->
                    contentAndWordsLiveData.value = Resource.success(words)
                }, { contentAndWordsLiveData.value = Resource.error("Error in fetching Words") }))
    }
}
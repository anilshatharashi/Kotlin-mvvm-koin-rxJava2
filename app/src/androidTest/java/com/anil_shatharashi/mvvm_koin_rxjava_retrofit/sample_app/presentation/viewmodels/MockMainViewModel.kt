package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.viewmodels

import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.GetContentAndCountWordsUseCase
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.GetEveryTenthCharacterUseCase
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases.GetTenthCharacterUseCase
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.model.Resource
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.viewmodel.MainViewModel

class MockMainViewModel(getTenthCharacterUseCase: GetTenthCharacterUseCase,
                        getEveryTenthCharacterUseCase: GetEveryTenthCharacterUseCase,
                        getContentAndWordsUseCase: GetContentAndCountWordsUseCase)
    : MainViewModel(getTenthCharacterUseCase, getEveryTenthCharacterUseCase, getContentAndWordsUseCase) {

    override fun getData() {
        tenthCharLiveData.postValue(Resource.success('s'))
        everyThenCharListLiveData.postValue(Resource.success("s d e s e q q 8"))
        contentAndWordsLiveData.postValue(Resource.success("{Hello=1, how=2, are=8, you=3}"))
    }
}
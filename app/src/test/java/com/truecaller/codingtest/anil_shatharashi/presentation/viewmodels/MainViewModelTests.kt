package com.truecaller.codingtest.anil_shatharashi.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.truecaller.codingtest.anil_shatharashi.domain.usecases.GetContentAndCountWordsUseCase
import com.truecaller.codingtest.anil_shatharashi.domain.usecases.GetEveryTenthCharacterUseCase
import com.truecaller.codingtest.anil_shatharashi.domain.usecases.GetTenthCharacterUseCase
import com.truecaller.codingtest.anil_shatharashi.presentation.model.Resource
import com.truecaller.codingtest.anil_shatharashi.presentation.util.UIRxSchedulerRule
import com.truecaller.codingtest.anil_shatharashi.presentation.viewmodel.MainViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTests {

    private lateinit var viewModel: MainViewModel
    private lateinit var getTenthCharUseCase: GetTenthCharacterUseCase
    private lateinit var everyTenthCharacterUseCase : GetEveryTenthCharacterUseCase
    private lateinit var contentAndCountWordsUseCase: GetContentAndCountWordsUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = UIRxSchedulerRule()

    @Before
    fun setup() {
        getTenthCharUseCase = mockk(relaxed = true)
        everyTenthCharacterUseCase = mockk(relaxed = true)
        contentAndCountWordsUseCase = mockk(relaxed = true)
        viewModel = spyk(MainViewModel(getTenthCharUseCase, everyTenthCharacterUseCase, contentAndCountWordsUseCase))
    }

    @Test
    fun getData_calls_use_case() {
        viewModel.getData()

        verify(exactly = 1) { getTenthCharUseCase.execute(any()) }
        verify(exactly = 1) { everyTenthCharacterUseCase.execute(any()) }
        verify(exactly = 1) { contentAndCountWordsUseCase.execute(any()) }
    }

    @Test
    fun getData_success() {
        val tenthCharResponse = 'd'
        val everyTenthCharResponse = "s d e s e q q 8"
        val contentAndCountWordsResponse = "{Hello=1, how=2, are=8, you=3}"

        every { getTenthCharUseCase.execute(any()) } returns Single.just(tenthCharResponse)
        every { everyTenthCharacterUseCase.execute(any()) } returns Single.just(everyTenthCharResponse)
        every { contentAndCountWordsUseCase.execute(any()) } returns Single.just(contentAndCountWordsResponse)

        viewModel.getData()

        Assert.assertEquals(Resource.success(tenthCharResponse), viewModel.tenthCharLiveData.value)
        Assert.assertEquals(Resource.success(everyTenthCharResponse), viewModel.everyThenCharListLiveData.value)
        Assert.assertEquals(Resource.success(contentAndCountWordsResponse), viewModel.contentAndWordsLiveData.value)
    }

    @Test
    fun getPupilsRequiredManualMarking_error() {
        val response = Exception()

        every { getTenthCharUseCase.execute(any()) } returns Single.error(response)
        every { everyTenthCharacterUseCase.execute(any()) } returns Single.error(response)
        every { contentAndCountWordsUseCase.execute(any()) } returns Single.error(response)

        viewModel.getData()

        Assert.assertEquals(Resource.error<Exception>("Error in fetching 10th Char"), viewModel.tenthCharLiveData.value)
        Assert.assertEquals(Resource.error<Exception>("Error in fetching Every 10th Char"), viewModel.everyThenCharListLiveData.value)
        Assert.assertEquals(Resource.error<Exception>("Error in fetching Words"), viewModel.contentAndWordsLiveData.value)
    }

}
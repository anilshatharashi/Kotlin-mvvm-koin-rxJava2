package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.data.repository

import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.data.util.UIRxSchedulerRule
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.data.source.api.SampleRESTApi
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.gateway.SampleRESTApiGateway
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class SampleRESTApiRepositoryTest {
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var sampleRESTApiGateway: SampleRESTApiGateway
    private lateinit var sampleRESTApi: SampleRESTApi

    @Rule
    @JvmField
    var testSchedulerRule = UIRxSchedulerRule()

    @Before
    fun setup() {
        compositeDisposable = CompositeDisposable()
        sampleRESTApi = mockk(relaxed = true)
        sampleRESTApiGateway = spyk(SampleRESTApiRepository(sampleRESTApi))
    }

    @After
    fun tearDown() {
        compositeDisposable.dispose()
    }

    @Test
    fun getTenthCharacterFromEndPoint_call() {
        val testObserver = sampleRESTApiGateway.getTenthCharacterFromEndPoint()
        testObserver.test()

        verify(exactly = 1) { sampleRESTApiGateway.getTenthCharacterFromEndPoint() }
    }

    @Test
    fun getEveryTenthCharacterFromEndPoint_call() {
        val testObserver = sampleRESTApiGateway.getEveryTenthCharacterFromEndPoint()
        testObserver.test()

        verify(exactly = 1) { sampleRESTApiGateway.getEveryTenthCharacterFromEndPoint() }
    }

    @Test
    fun getContentEndPointAndCountWords_call() {
        val testObserver = sampleRESTApiGateway.getContentAndCountWords()
        testObserver.test()

        verify(exactly = 1) { sampleRESTApiGateway.getContentAndCountWords() }
    }

    @Test
    fun getTenthCharacterFromEndPoint_success() {
        val responseObservable = Single.just('d')

        val responseObservableTest = responseObservable.test()
        val testObserver = sampleRESTApiGateway.getTenthCharacterFromEndPoint().subscribe()
        compositeDisposable.add(testObserver)
        verify(exactly = 1) { sampleRESTApi.getContent()}

        responseObservableTest.assertComplete()
        responseObservableTest.assertNoErrors()
        responseObservableTest.assertValueCount(1)
    }

    @Test
    fun getTenthCharacterFromEndPoint_error() {
        val exception = Exception()
        val errorResponse = Single.error<Response<Char>>(exception)
        val responseObservableTest = errorResponse.test()

        val testObserver = sampleRESTApiGateway.getTenthCharacterFromEndPoint().subscribe()
        compositeDisposable.add(testObserver)

        responseObservableTest.assertValueCount(0)
        responseObservableTest.assertError(exception)
    }

    @Test
    fun getEveryTenthCharacterFromEndPoint_success() {
        val responseObservable = Single.just("s d e e s d e")

        val responseObservableTest = responseObservable.test()
        val testObserver = sampleRESTApiGateway.getEveryTenthCharacterFromEndPoint().subscribe()
        compositeDisposable.add(testObserver)
        verify(exactly = 1) { sampleRESTApi.getContent()}

        responseObservableTest.assertComplete()
        responseObservableTest.assertNoErrors()
        responseObservableTest.assertValueCount(1)
    }

    @Test
    fun getEveryTenthCharacterFromEndPoint_error() {
        val exception = Exception()
        val errorResponse = Single.error<Response<String>>(exception)
        val responseObservableTest = errorResponse.test()

        val testObserver = sampleRESTApiGateway.getEveryTenthCharacterFromEndPoint().subscribe()
        compositeDisposable.add(testObserver)

        responseObservableTest.assertValueCount(0)
        responseObservableTest.assertError(exception)
    }

    @Test
    fun getContentEndPointAndCountWords_success() {
        val responseObservable = Single.just("{Hello=1, how=2, are=8, you=3}")

        val responseObservableTest = responseObservable.test()
        val testObserver = sampleRESTApiGateway.getContentAndCountWords().subscribe()
        compositeDisposable.add(testObserver)
        verify(exactly = 1) { sampleRESTApi.getContent()}

        responseObservableTest.assertComplete()
        responseObservableTest.assertNoErrors()
        responseObservableTest.assertValueCount(1)
    }

    @Test
    fun getContentEndPointAndCountWords_error() {
        val exception = Exception()
        val errorResponse = Single.error<Response<String>>(exception)
        val responseObservableTest = errorResponse.test()

        val testObserver = sampleRESTApiGateway.getContentAndCountWords().subscribe()
        compositeDisposable.add(testObserver)

        responseObservableTest.assertValueCount(0)
        responseObservableTest.assertError(exception)
    }
}
package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.usecases

import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.executor.SchedulerProvider
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.gateway.SampleRESTApiGateway
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.domain.util.RxSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Matchers.any

class GetTenthCharacterUseCaseTest {
    private lateinit var gateway: SampleRESTApiGateway
    private lateinit var scheduler: SchedulerProvider
    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulerRule()

    private lateinit var useCase: GetTenthCharacterUseCase

    @Before
    fun setup() {
        gateway = mockk(relaxed = true)
        scheduler = mockk(relaxed = true)
        useCase = GetTenthCharacterUseCase(gateway, scheduler)
    }

    @Test
    fun execute_success() {
        every { gateway.getTenthCharacterFromEndPoint() } returns Single.just('s')

        val testObserver =  useCase.execute(any()).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue('s')
        verify(exactly = 1) { gateway.getTenthCharacterFromEndPoint() }
    }

    @Test
    fun execute_error() {
        val exception = Exception()
        every { gateway.getTenthCharacterFromEndPoint() } returns Single.error(exception)

        val testObserver =  useCase.execute(any()).test()
        testObserver.assertNotComplete()
        testObserver.assertError(exception)
        verify(exactly = 1) { gateway.getTenthCharacterFromEndPoint() }
    }
}
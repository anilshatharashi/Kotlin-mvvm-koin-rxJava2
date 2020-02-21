package com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.R
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.model.Status
import com.anil_shatharashi.mvvm_koin_rxjava_retrofit.sample_app.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.fetch_data_button
import kotlinx.android.synthetic.main.activity_main.tenth_char_array_tv
import kotlinx.android.synthetic.main.activity_main.tenth_char_tv
import kotlinx.android.synthetic.main.activity_main.total_words_count_tv
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.tenthCharLiveData.observe(this, Observer { response ->
            when {
                response.status == Status.SUCCESS -> response.data?.let { handleTenthCharResponse(it.toString()) }
                response.status == Status.ERROR -> handleTenthCharResponse(response.errorMessage)
            }
        })
        mainViewModel.everyThenCharListLiveData.observe(this, Observer { response ->
            when {
                response.status == Status.SUCCESS -> response.data?.let { handleEveryThenCharResponse(it) }
                response.status == Status.ERROR -> handleEveryThenCharResponse(response.errorMessage)
            }
        })
        mainViewModel.contentAndWordsLiveData.observe(this, Observer { response ->
            when {
                response.status == Status.SUCCESS -> response.data?.let { handleWordsResponse(it) }
                response.status == Status.ERROR -> handleWordsResponse(response.errorMessage)
            }
        })

        fetch_data_button.setOnClickListener { mainViewModel.getData() }
    }

    private fun handleTenthCharResponse(tenthChar: String?) {
        tenth_char_tv.text = resources.getString(R.string.tenth_char, tenthChar)
    }

    private fun handleEveryThenCharResponse(everyTenthChar: String?) {
        tenth_char_array_tv.text = resources.getString(R.string.every_tenth_char, everyTenthChar)
    }

    private fun handleWordsResponse(words: String?) {
        total_words_count_tv.text = resources.getString(R.string.words_count, words)
    }
}

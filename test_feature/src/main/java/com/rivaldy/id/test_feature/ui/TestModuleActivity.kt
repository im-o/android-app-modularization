package com.rivaldy.id.test_feature.ui

import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.network.okHttpClient
import com.rivaldy.id.base.base.BaseActivity
import com.rivaldy.id.base.data.network.DataResource
import com.rivaldy.id.base.util.UtilExtensions.isAreVisible
import com.rivaldy.id.base.util.UtilFunctions.loge
import com.rivaldy.id.core.BuildConfig
import com.rivaldy.id.core.CharacterListQuery
import com.rivaldy.id.test_feature.databinding.ActivityTestModuleBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class TestModuleActivity : BaseActivity<ActivityTestModuleBinding>() {
    private val loggingInterceptor by lazy {
        if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://rickandmortyapi.com/graphql")
        .okHttpClient(okHttpClient)
        .build()

    override fun getViewBinding() = ActivityTestModuleBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initObservers() {
        binding.loadingPB.isAreVisible(true)
        lifecycleScope.launchWhenResumed {
            var strName = ""
            val response = try {
                apolloClient.query(CharacterListQuery()).execute()
            } catch (e: ApolloException) {
                loge("LaunchList Failure : $e")
                null
            }
            for (data in response?.data?.characters?.results ?: return@launchWhenResumed) strName += "${data?.name}\n"
            binding.nameTV.text = strName
            binding.loadingPB.isAreVisible(false)
        }
    }

    override fun showFailure(failure: DataResource.Failure) {
    }
}
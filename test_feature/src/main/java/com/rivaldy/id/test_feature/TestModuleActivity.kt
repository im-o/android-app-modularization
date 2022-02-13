package com.rivaldy.id.test_feature

import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import com.rivaldy.id.base.BuildConfig
import com.rivaldy.id.base.base.BaseActivity
import com.rivaldy.id.base.data.network.DataResource
import com.rivaldy.id.base.util.UtilFunctions.loge
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
        .build()

    override fun getViewBinding() = ActivityTestModuleBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initObservers() {
        lifecycleScope.launchWhenResumed {
            try {
                val response = apolloClient.query(CharacterListQuery()).execute()
                loge("APOLLO : ${response.data?.characters?.results}")
            } catch (e: Exception) {
                loge("ERROR APOLLO : ${e}")
            }
        }
    }

    override fun showFailure(failure: DataResource.Failure) {
    }
}
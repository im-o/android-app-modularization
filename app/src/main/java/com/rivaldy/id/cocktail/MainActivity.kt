package com.rivaldy.id.cocktail

import com.rivaldy.id.base.base.BaseActivity
import com.rivaldy.id.base.data.network.DataResource
import com.rivaldy.id.cocktail.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initObservers() {
    }

    override fun showFailure(failure: DataResource.Failure) {
    }
}
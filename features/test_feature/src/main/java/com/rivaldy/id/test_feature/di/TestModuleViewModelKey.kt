package com.rivaldy.id.test_feature.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/** Created by github.com/im-o on 9/24/2022. */

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class TestModuleViewModelKey(val value: KClass<out ViewModel>)
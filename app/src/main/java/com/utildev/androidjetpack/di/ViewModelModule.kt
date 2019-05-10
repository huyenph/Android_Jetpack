package com.utildev.androidjetpack.di

import com.utildev.androidjetpack.presentation.activity.MainViewModel
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel<MainViewModel>()
}
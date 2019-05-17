package com.utildev.androidjetpack.di

import com.utildev.androidjetpack.presentation.activity.MainViewModel
import com.utildev.androidjetpack.presentation.fragment.other.NotConnectionViewModel
import com.utildev.androidjetpack.presentation.fragment.question.QuestionViewModel
import com.utildev.androidjetpack.presentation.fragment.tag.TagViewModel
import com.utildev.androidjetpack.presentation.fragment.user.UserViewModel
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel<MainViewModel>()
    viewModel<QuestionViewModel>()
    viewModel<TagViewModel>()
    viewModel<UserViewModel>()
    viewModel<NotConnectionViewModel>()
}
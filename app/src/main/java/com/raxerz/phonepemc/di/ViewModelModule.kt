package com.raxerz.phonepemc.di

import com.raxerz.phonepemc.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}
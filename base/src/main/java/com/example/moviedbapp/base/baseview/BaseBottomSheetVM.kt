package com.example.moviedbapp.base.baseview

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseBottomSheetVM<VB : ViewBinding, VM : BaseViewModel>(clazz: KClass<VM>) : BaseBottomSheet<VB>() {

    private val baseViewModel: VM by viewModel(clazz = clazz)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel(baseViewModel)
    }

    abstract fun observeViewModel(viewModel: VM)
}

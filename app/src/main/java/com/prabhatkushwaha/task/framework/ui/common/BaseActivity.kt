package com.prabhatkushwaha.task.framework.ui.common

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM : ViewModel, VB : ViewDataBinding>(private val layoutRes: Int) :
    AppCompatActivity() {

    lateinit var binding: VB
    abstract val bindingVariable: Int
    abstract val viewModel: VM
    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@BaseActivity, layoutRes)
        mContext = this
        binding.lifecycleOwner = this
        binding.setVariable(bindingVariable, viewModel)
        binding.executePendingBindings()
        initUI()
        initListeners()
        initObservers()
    }

    abstract fun initUI()

    abstract fun initObservers()

    abstract fun initListeners()


}
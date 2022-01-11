package com.prabhatkushwaha.task.framework.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.prabhatkushwaha.task.BR
import com.prabhatkushwaha.task.R
import com.prabhatkushwaha.task.databinding.ActivitySplashBinding
import com.prabhatkushwaha.task.framework.ui.common.BaseActivity
import com.prabhatkushwaha.task.framework.ui.reels.ReelsActivity
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class SplashActivity :
    BaseActivity<SplashViewModel, ActivitySplashBinding>(R.layout.activity_splash) {
    override var bindingVariable: Int = BR.splashViewModel
    override val viewModel: SplashViewModel by viewModels()


    override fun initUI() {

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, ReelsActivity::class.java))
            finish()
        }, 2000)
    }

    override fun initObservers() {

    }

    override fun initListeners() {

    }

}
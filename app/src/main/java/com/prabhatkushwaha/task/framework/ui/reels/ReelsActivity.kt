package com.prabhatkushwaha.task.framework.ui.reels

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.prabhatkushwaha.task.BR
import com.prabhatkushwaha.task.R
import com.prabhatkushwaha.task.business.domain.util.ApiResult
import com.prabhatkushwaha.task.business.interactors.reels.GetReelsInteractor
import com.prabhatkushwaha.task.databinding.ActivityReelsBinding
import com.prabhatkushwaha.task.framework.ui.common.BaseActivity
import com.prabhatkushwaha.task.framework.ui.view.isNetworkAvailable
import com.prabhatkushwaha.task.framework.ui.view.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject


@InternalCoroutinesApi
@AndroidEntryPoint
class ReelsActivity : BaseActivity<ReelsViewModel, ActivityReelsBinding>(R.layout.activity_reels) {
    override val bindingVariable: Int = BR.reelViewModel
    override val viewModel: ReelsViewModel by viewModels()

    @Inject
    lateinit var reelService: GetReelsInteractor

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private val snapHelper = PagerSnapHelper()
    override fun initUI() {
        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(mContext)
            it.adapter = groupAdapter
            snapHelper.attachToRecyclerView(it)
        }
        if (isNetworkAvailable())
            viewModel.getReelsData()
        else
            showToast("No internet connection")
    }

    override fun initObservers() {

        viewModel.reelsData.observe(this) {
            when (it) {
                is ApiResult.GenericError -> {
                    showToast(it.message)
                    binding.pbReels.visibility = View.GONE
                }
                is ApiResult.Loading -> {
                    binding.pbReels.visibility = View.VISIBLE
                }
                is ApiResult.NetworkError -> {
                    showToast(it.message)
                    binding.pbReels.visibility = View.GONE
                }
                is ApiResult.Success -> {
                    binding.pbReels.visibility = View.GONE
                    val lifecycleOwner = this
                    val listOfGroup = it.data?.map { reelVideo ->
                        val largeVideoGroup = LargeVideoGroup(
                            scope = lifecycleScope,
                            lifecycleOwner = lifecycleOwner,
                            reelModel = reelVideo,
                            onPersonIconClicked = { _ ->
                            },
                            onVideoEnded = {
                                scrollDownToNextVideo(groupAdapter.getAdapterPosition(it))
                            },
                        )
                        largeVideoGroup
                    } ?: listOf()
                    groupAdapter.addAll(listOfGroup)

                }
            }
        }
    }

    override fun initListeners() {
        binding.ivBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun scrollDownToNextVideo(currentPosition: Int) {
        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        layoutManager.scrollToPosition(currentPosition + 1)
    }

}



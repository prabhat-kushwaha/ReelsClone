package com.prabhatkushwaha.task.framework.ui.reels

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.prabhatkushwaha.task.R
import com.prabhatkushwaha.task.business.domain.models.ReelsModel
import com.prabhatkushwaha.task.databinding.ItemVideoViewLayoutBinding
import com.xwray.groupie.viewbinding.BindableItem
import kotlinx.coroutines.CoroutineScope


class LargeVideoGroup(
    private val scope: CoroutineScope,
    private val lifecycleOwner: LifecycleOwner,
    private val onPersonIconClicked: (String) -> Unit,
    private val onVideoEnded: (LargeVideoGroup) -> Unit,
    private val reelModel: ReelsModel
) : BindableItem<ItemVideoViewLayoutBinding>() {

    private lateinit var mainLargeVideo: MainLargeVideo

    override fun bind(binding: ItemVideoViewLayoutBinding, position: Int) {
        mainLargeVideo = MainLargeVideo(
            scope = scope,
            lifecycle = lifecycleOwner.lifecycle,
            binding = binding,
            onPersonIconClicked = onPersonIconClicked,
            onVideoEnded = { onVideoEnded(this) }
        )

        binding.lifecycleOwner = lifecycleOwner
    }

    override fun onViewAttachedToWindow(viewHolder: com.xwray.groupie.viewbinding.GroupieViewHolder<ItemVideoViewLayoutBinding>) {
        super.onViewAttachedToWindow(viewHolder)
        mainLargeVideo.init(reelModel)
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
    }

    override fun onViewDetachedFromWindow(viewHolder: com.xwray.groupie.viewbinding.GroupieViewHolder<ItemVideoViewLayoutBinding>) {
        super.onViewDetachedFromWindow(viewHolder)
        mainLargeVideo.destroy()
        lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
    }

    override fun initializeViewBinding(view: View) =
        ItemVideoViewLayoutBinding.bind(view)

    override fun getLayout() = R.layout.item_video_view_layout

    private val lifecycleObserver = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() {
            mainLargeVideo.player?.pausePlayer()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {
            mainLargeVideo.player?.pausePlayer()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            mainLargeVideo.destroy()
        }
    }
}
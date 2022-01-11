package com.prabhatkushwaha.task.framework.ui.reels

import android.annotation.SuppressLint
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.lifecycle.Lifecycle
import com.prabhatkushwaha.task.business.domain.models.ReelsModel
import com.prabhatkushwaha.task.databinding.ItemVideoViewLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainLargeVideo(
    private val scope: CoroutineScope, private val lifecycle: Lifecycle,
    private val binding: ItemVideoViewLayoutBinding,
    private val onPersonIconClicked: (String) -> Unit,
    private val onVideoEnded: (Player) -> Unit
) {
    var player: Player? = null

    fun init(ReelsModel: ReelsModel) {
        scope.launch {
            createPlayer(ReelsModel)
            createVideoInfo(ReelsModel)
            enableDoubleTap(ReelsModel)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun createVideoInfo(reelsModel: ReelsModel) {
        binding.tvLikeCount.text = reelsModel.like_count
        binding.tvShareCount.text = reelsModel.share_count
        binding.tvCmtCount.text = reelsModel.comment_count
        binding.tvUserName.text = "@${reelsModel.username}".replace("_"," ")
        binding.tvHashTag.text =reelsModel.hash_tag
    }


    private fun createPlayer(ReelsModel: ReelsModel) {
        player = Player(
            simpleExoplayerView = binding.vdVw,
            playBtn = binding.btPlay,
            context = binding.root.context,
            url = ReelsModel.video_url,
            onVideoEnded = { player -> onVideoEnded(player) }
        )

        // Playback control view.

        lifecycle.addObserver(player!!)
        player?.init()
    }


    private fun enableDoubleTap(ReelsModel: ReelsModel) {
        val gd = GestureDetector(
            binding.root.context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    player?.changePlayerState()
                    return true
                }

                override fun onDoubleTap(e: MotionEvent?): Boolean {
                    return true
                }

                override fun onDoubleTapEvent(e: MotionEvent?) = true
            })
        binding.vdVw.setOnTouchListener { view, event ->
            view.performClick()
            return@setOnTouchListener gd.onTouchEvent(event)
        }
    }


    fun destroy() {
        player?.let {
            it.stopPlayer()
            lifecycle.removeObserver(it)
            player = null
        }
    }
}
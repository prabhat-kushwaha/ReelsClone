package com.prabhatkushwaha.task.business.data.network.abs

import com.prabhatkushwaha.task.business.domain.models.ReelsModel
import javax.inject.Singleton

@Singleton
interface ReelsNetworkDataSource {
    suspend fun getReelList(userId:String,page:String,category:String):List<ReelsModel>
}
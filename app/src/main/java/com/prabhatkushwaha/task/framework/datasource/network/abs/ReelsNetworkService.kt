package com.prabhatkushwaha.task.framework.datasource.network.abs

import com.prabhatkushwaha.task.business.domain.models.ReelsModel
import javax.inject.Singleton

@Singleton
interface ReelsNetworkService {
    suspend fun getReelList(userId:String,page:String,category:String):List<ReelsModel>
}
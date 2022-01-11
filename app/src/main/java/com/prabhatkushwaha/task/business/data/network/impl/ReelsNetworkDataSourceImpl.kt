package com.prabhatkushwaha.task.business.data.network.impl

import com.prabhatkushwaha.task.business.data.network.abs.ReelsNetworkDataSource
import com.prabhatkushwaha.task.business.domain.models.ReelsModel
import com.prabhatkushwaha.task.framework.datasource.network.abs.ReelsNetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReelsNetworkDataSourceImpl @Inject constructor(private val service: ReelsNetworkService) : ReelsNetworkDataSource {
    override suspend fun getReelList(
        userId: String,
        page: String,
        category: String
    ): List<ReelsModel> {
       return service.getReelList(userId,page,category)
    }
}
package com.prabhatkushwaha.task.framework.datasource.network.impl

import com.prabhatkushwaha.task.business.domain.models.ReelsModel
import com.prabhatkushwaha.task.framework.datasource.network.abs.ReelsNetworkService
import com.prabhatkushwaha.task.framework.datasource.network.service.ReelService
import com.prabhatkushwaha.task.framework.datasource.network.utils.ReelsMapper

class ReelsNetworkServiceImpl(
    private val service: ReelService,
    private val entityMapper: ReelsMapper
) : ReelsNetworkService {
    override suspend fun getReelList(
        userId: String,
        page: String,
        category: String
    ): List<ReelsModel> {
        val res = service.getReelsData(page = page, userid = userId, category = category)
        return res.product.map {
            entityMapper.toEntity(it)
        }
    }
}
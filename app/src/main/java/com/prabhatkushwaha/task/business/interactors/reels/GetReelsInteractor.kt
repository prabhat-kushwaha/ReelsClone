package com.prabhatkushwaha.task.business.interactors.reels

import com.prabhatkushwaha.task.business.data.network.abs.ReelsNetworkDataSource
import com.prabhatkushwaha.task.business.domain.models.ReelsModel
import com.prabhatkushwaha.task.business.domain.util.ApiResult
import com.prabhatkushwaha.task.business.domain.util.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetReelsInteractor @Inject constructor(
    private val reelsNetworkDataSource: ReelsNetworkDataSource
) {


    fun getReelsList(userId: String, page: String, category: String): Flow<ApiResult<List<ReelsModel>?>> {
        return flow {
            val res =
                safeApiCall(Dispatchers.IO) {
                    reelsNetworkDataSource.getReelList(userId, page, category)
                }
            emit (res)
        }
    }
}
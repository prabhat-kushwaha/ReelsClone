package com.prabhatkushwaha.task.framework.datasource.network.utils

import com.prabhatkushwaha.task.business.domain.models.ReelsModel
import com.prabhatkushwaha.task.business.domain.util.EntityMapper
import com.prabhatkushwaha.task.framework.datasource.network.response.Product

class ReelsMapper : EntityMapper<ReelsModel, Product> {
    override fun toEntity(domain: Product): ReelsModel {
        return ReelsModel(
            like_count = domain.like_count,
            share_count = domain.share_count,
            comment_count = domain.cmt_count,
            username = domain.user_name,
            hash_tag = domain.hash_tag,
            id = domain.id,
            video_thumb = domain.video_thumb
        , video_url = domain.video_name
        )
    }

    override fun fromEntity(entity: ReelsModel): Product {
        TODO("Not yet implemented")
    }

}
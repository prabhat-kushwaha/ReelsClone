package com.prabhatkushwaha.task.business.domain.util

interface EntityMapper<E, D> {
    fun toEntity(domain: D): E
    fun fromEntity(entity: E): D
}
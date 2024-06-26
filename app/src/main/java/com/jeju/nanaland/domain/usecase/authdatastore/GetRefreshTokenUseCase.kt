package com.jeju.nanaland.domain.usecase.authdatastore

import com.jeju.nanaland.domain.repository.AuthDataStoreRepository

class GetRefreshTokenUseCase(
    private val repository: AuthDataStoreRepository
) {
    operator fun invoke() = repository.getRefreshToken()
}
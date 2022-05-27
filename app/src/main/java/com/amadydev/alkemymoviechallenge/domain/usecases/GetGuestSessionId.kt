package com.amadydev.alkemymoviechallenge.domain.usecases

import com.amadydev.alkemymoviechallenge.data.remote.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetGuestSessionId @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): String =
        repository.getGuestSessionId()
}
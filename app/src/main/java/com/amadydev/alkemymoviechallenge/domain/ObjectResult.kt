package com.amadydev.alkemymoviechallenge.domain

import java.lang.Exception

sealed class ObjectResult<out T>{
    data class Success<T>(val data: T): ObjectResult<T>()
    data class Failure(val exception: Exception): ObjectResult<Nothing>()
}
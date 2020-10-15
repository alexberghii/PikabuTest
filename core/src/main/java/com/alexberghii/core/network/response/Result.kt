package com.alexberghii.core.network.response

import androidx.annotation.StringRes


sealed class Result<T> {

    data class Success<T>(val value: T) : Result<T>()

    data class Error<T>(@StringRes val stringResId: Int) : Result<T>()
}
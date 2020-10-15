package com.alexberghii.core.network.extension

import com.alexberghii.core.R
import retrofit2.Response
import com.alexberghii.core.network.response.Result


fun <T> Response<T>.getResult(): Result<T> {
    return if (isSuccessful) {
        val body = body()
        if (body != null){
            Result.Success(body)
        } else {
            Result.Error(R.string.error_message)
        }
    } else {
        Result.Error(R.string.error_message)
    }
}
package com.rivaldy.id.core.utils

import android.app.Activity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rivaldy.id.core.R
import com.rivaldy.id.core.data.model.api.ErrorResponse
import com.rivaldy.id.core.data.network.DataResource
import com.rivaldy.id.core.utils.UtilConstants.OTHER_ERROR
import com.rivaldy.id.core.utils.UtilExtensions.showSnackBar
import com.rivaldy.id.core.utils.UtilFunctions.loge
import java.io.IOException

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

object UtilExceptions {
    class NoInternetException(message: String) : IOException(message)

    fun Activity.handleApiError(
        failure: DataResource.Failure,
        retry: (() -> Unit)? = null
    ) {
        loge("NoInternetException : $failure")
        if (failure.isNetworkError) {
            if (failure.errorCode == OTHER_ERROR) window.decorView.rootView.showSnackBar(failure.otherMessage.toString(), retry)
            else window.decorView.rootView.showSnackBar(getString(R.string.no_internet), retry)
        } else {
            try {
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                val errorResponse: ErrorResponse? = gson.fromJson(failure.errorBody?.charStream(), type)
                if (failure.errorCode == 401) {
                    window.decorView.rootView.showSnackBar(errorResponse?.statusMessage ?: getString(R.string.fetch_failed), retry)
                } else window.decorView.rootView.showSnackBar(errorResponse?.statusMessage ?: getString(R.string.some_error))
                loge("ErrorResponse NoInternetException: $errorResponse")
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
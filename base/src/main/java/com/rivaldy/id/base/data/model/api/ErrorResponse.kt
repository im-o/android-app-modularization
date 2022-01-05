package com.rivaldy.id.base.data.model.api

import com.google.gson.annotations.SerializedName

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

data class ErrorResponse(

    /**
     * TODO-CHANGE
     * CHANGE THIS DATA CLASS WITH YOUR ERROR RESPONSE API
     **/

    @SerializedName("status_code")
    var statusCode: Int? = null,
    @SerializedName("status_message")
    var statusMessage: String? = null,
    @SerializedName("success")
    var success: Boolean? = null
)

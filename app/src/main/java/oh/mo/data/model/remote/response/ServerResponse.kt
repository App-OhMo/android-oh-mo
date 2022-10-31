package oh.mo.data.model.remote.response

data class ServerResponse(
    val statusCode: Int,
    val responseMessage: String,
    val data: String?,
)

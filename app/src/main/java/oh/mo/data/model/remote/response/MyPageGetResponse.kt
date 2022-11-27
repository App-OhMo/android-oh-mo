package oh.mo.data.model.remote.response

data class MyPageGetResponse(
    val statusCode: Int?,
    val responseMessage: String?,
    val `data`: Data?
) {
    data class Data(
        val id: Int?,
        val nickName: String?,
        val userEmail: String?,
        val profileUrl: String?
    )
}
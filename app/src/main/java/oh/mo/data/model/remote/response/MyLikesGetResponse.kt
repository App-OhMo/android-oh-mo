package oh.mo.data.model.remote.response

data class MyLikesGetResponse(
    val statusCode: Int?,
    val responseMessage: String?,
    val `data`: List<Data?>?
) {
    data class Data(
        val id: Int?,
        val img: String?,
        val memberName: String?,
        val boardId: Int?
    )
}
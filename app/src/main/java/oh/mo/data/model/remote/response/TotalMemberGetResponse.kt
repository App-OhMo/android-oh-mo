package oh.mo.data.model.remote.response

data class TotalMemberGetResponse(
    val statusCode: Int?,
    val responseMessage: String?,
    val `data`: List<Data?>?
) {
    data class Data(
        val id: Int?,
        val nickName: String?,
        val userEmail: String?,
        val pwd: String?,
        val roles: List<String?>?,
        val profileUrl: String?,
        val replies: Any?,
        val username: String?,
        val enabled: Boolean?,
        val authorities: List<Authority?>?,
        val password: String?,
        val accountNonExpired: Boolean?,
        val accountNonLocked: Boolean?,
        val credentialsNonExpired: Boolean?
    ) {
        data class Authority(
            val authority: String?
        )
    }
}
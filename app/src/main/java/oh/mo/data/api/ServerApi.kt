package oh.mo.data.api

import oh.mo.data.model.remote.request.EmailDuplicateCheckRequest
import oh.mo.data.model.remote.request.NicknameDuplicateCheckRequest
import oh.mo.data.model.remote.request.SignInRequest
import oh.mo.data.model.remote.request.SignUpRequest
import oh.mo.data.model.remote.response.ServerResponse
import retrofit2.http.*

interface ServerApi {

    @POST("user/join")
    suspend fun postUserSignUp(
        @Body data: SignUpRequest,
    ): ServerResponse

    @POST("user/login")
    suspend fun postUserSignIn(
        @Body data: SignInRequest
    ): ServerResponse

    @POST("check/nick_name")
    suspend fun postIsNicknameDuplicated(
        @Body data: NicknameDuplicateCheckRequest,
    ): Boolean

    @POST("check/user_email")
    suspend fun postIsEmailDuplicated(
        @Body data: EmailDuplicateCheckRequest,
    ): Boolean
}


package ms.gamehouse.zicare.data.remote

import ms.gamehouse.zicare.data.model.CredentialEntity
import ms.gamehouse.zicare.data.model.TokenEntity
import ms.gamehouse.zicare.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface APIService {
    @POST("api/v1/auth/login")
    suspend fun getUser(token: String): User

    @POST("api/v1/auth/singup")
    suspend fun login(@Body credential: CredentialEntity): TokenEntity

    @POST("api/v1/auth/logout")
    suspend fun logout()

    @GET("api/v1/users/me")
    suspend fun getProfile(@Header("Authorization") token: String): User
}
package ms.gamehouse.zicare.data.source.remote

import kotlinx.coroutines.flow.Flow
import ms.gamehouse.zicare.data.model.CredentialEntity
import ms.gamehouse.zicare.data.model.RegistrationEntity
import ms.gamehouse.zicare.data.model.TokenEntity
import ms.gamehouse.zicare.data.model.User
import ms.gamehouse.zicare.utils.NetworkResponse
import ms.gamehouse.zicare.utils.Response


interface RemoteDataSource {
    fun doLoginRemoteRepository(credential: CredentialEntity): Flow<NetworkResponse<TokenEntity>>
    fun doSignupRemoteRepository(data: RegistrationEntity): Flow<Response<User>>
    fun getUserRemoteRepository(userName: String): Flow<Response<User>>
}
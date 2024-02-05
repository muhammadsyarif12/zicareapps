package ms.gamehouse.zicare.data.source.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ms.gamehouse.zicare.data.model.CredentialEntity
import ms.gamehouse.zicare.data.model.RegistrationEntity
import ms.gamehouse.zicare.data.model.TokenEntity
import ms.gamehouse.zicare.data.model.User
import ms.gamehouse.zicare.data.remote.APIService
import ms.gamehouse.zicare.utils.NetworkResponse
import ms.gamehouse.zicare.utils.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: APIService
): RemoteDataSource{
    override fun doLoginRemoteRepository(credential: CredentialEntity): Flow<NetworkResponse<TokenEntity>> {
        return flow {
            emit(NetworkResponse.Loading)
            try {
                println("================try===================")
                println("user : ${credential.username}")
                println("pass : ${credential.password}")
                val response = apiService.login(credential)
                println("=================${response.tokenAccess}==================")
                emit(NetworkResponse.Success(response))
            }catch (e: Exception){
                emit(NetworkResponse.Error(e))
            }
        }
    }

    override fun doSignupRemoteRepository(data: RegistrationEntity): Flow<Response<User>> {
        TODO("Not yet implemented")
    }

    override fun getUserRemoteRepository(userName: String): Flow<Response<User>> {
        TODO("Not yet implemented")
    }
}
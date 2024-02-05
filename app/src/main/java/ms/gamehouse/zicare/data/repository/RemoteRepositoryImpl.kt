package ms.gamehouse.zicare.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ms.gamehouse.zicare.data.model.CredentialEntity
import ms.gamehouse.zicare.data.model.RemoteRepository
import ms.gamehouse.zicare.data.model.TokenEntity
import ms.gamehouse.zicare.data.source.remote.RemoteDataSource
import ms.gamehouse.zicare.di.IoDispatcher
import ms.gamehouse.zicare.utils.NetworkResponse
import ms.gamehouse.zicare.utils.Response
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): RemoteRepository {

    override fun doLoginRemoteRepository(credential: CredentialEntity): Flow<NetworkResponse<TokenEntity>> {
        return remoteDataSource.doLoginRemoteRepository(credential).map {
            when(it){
                is NetworkResponse.Loading -> NetworkResponse.Loading
                is NetworkResponse.Success -> NetworkResponse.Success(it.result)
                is NetworkResponse.Error -> NetworkResponse.Error(it.exception)
            }
        }.flowOn(ioDispatcher)
    }

}
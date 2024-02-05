package ms.gamehouse.zicare.data.model

import kotlinx.coroutines.flow.Flow
import ms.gamehouse.zicare.utils.NetworkResponse
import ms.gamehouse.zicare.utils.Response

interface RemoteRepository {

    fun doLoginRemoteRepository(credential: CredentialEntity): Flow<NetworkResponse<TokenEntity>>

}
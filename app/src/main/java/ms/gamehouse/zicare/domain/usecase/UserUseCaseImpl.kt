package ms.gamehouse.zicare.domain.usecase

import kotlinx.coroutines.flow.Flow
import ms.gamehouse.zicare.data.model.CredentialEntity
import ms.gamehouse.zicare.data.model.RemoteRepository
import ms.gamehouse.zicare.data.model.TokenEntity
import ms.gamehouse.zicare.utils.NetworkResponse
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
): UserUseCase {
    override fun invoke(user: CredentialEntity): Flow<NetworkResponse<TokenEntity>> {
        return remoteRepository.doLoginRemoteRepository(user)
    }
}
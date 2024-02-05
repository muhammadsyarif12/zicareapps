package ms.gamehouse.zicare.domain.usecase

import kotlinx.coroutines.flow.Flow
import ms.gamehouse.zicare.data.model.CredentialEntity
import ms.gamehouse.zicare.data.model.TokenEntity
import ms.gamehouse.zicare.utils.NetworkResponse

interface UserUseCase {
    operator fun invoke(user: CredentialEntity): Flow<NetworkResponse<TokenEntity>>
}
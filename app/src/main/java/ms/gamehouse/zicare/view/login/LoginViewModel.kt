package ms.gamehouse.zicare.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ms.gamehouse.zicare.data.model.CredentialEntity
import ms.gamehouse.zicare.data.model.TokenEntity
import ms.gamehouse.zicare.domain.usecase.UserUseCase
import ms.gamehouse.zicare.utils.NetworkResponse
import ms.gamehouse.zicare.utils.Resource
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) :ViewModel() {
    private var _loginState = MutableLiveData<Resource<TokenEntity>>()
    val loginState : LiveData<Resource<TokenEntity>>
        get() = _loginState

    fun doLogin(userCredential: CredentialEntity){
        userUseCase(userCredential).onEach{
            when(it){
                is NetworkResponse.Loading ->  _loginState.postValue(Resource.Loading())
                is NetworkResponse.Success -> _loginState.postValue(Resource.Success(it.result))
                is NetworkResponse.Error -> _loginState.postValue(Resource.Error(it.exception.message.toString()))
            }
        }.launchIn(viewModelScope)
    }

}
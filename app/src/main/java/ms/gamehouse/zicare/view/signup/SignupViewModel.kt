package ms.gamehouse.zicare.view.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ms.gamehouse.zicare.data.model.RegistrationEntity
import ms.gamehouse.zicare.utils.Resource
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(): ViewModel(){
    private var _signup = MutableLiveData<Resource<RegistrationEntity>>()
    val signup : LiveData<Resource<RegistrationEntity>>
        get() = _signup

    fun doSignUp(data: RegistrationEntity){
        _signup.value = Resource.Loading()
        viewModelScope.launch {

        }
    }
}
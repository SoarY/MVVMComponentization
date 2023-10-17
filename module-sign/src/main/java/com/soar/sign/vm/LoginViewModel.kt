package com.soar.sign.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.soar.mvvm.base.BaseViewModel
import com.soar.network.bean.Resource
import com.soar.network.bean.request.LoginRequest
import com.soar.network.bean.response.LoginResponse
import com.soar.sign.repository.LoginRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * NAME：YONG_
 * Created at: 2023/8/24 17
 * Describe:
 */
class LoginViewModel : BaseViewModel(){

    val loginLiveData = MutableLiveData<Resource<LoginResponse>>()

    fun doLogin(userName: String, passWord: String) {
        viewModelScope.launch {
            loginLiveData.value = Resource.Loading()
            LoginRepository.doLogin(loginRequest = LoginRequest(userName, passWord)).collect {
                loginLiveData.value = it
            }
        }
    }
}
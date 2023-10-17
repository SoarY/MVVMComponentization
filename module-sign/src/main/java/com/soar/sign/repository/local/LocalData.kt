package com.soar.sign.repository.local

import com.soar.network.bean.Resource
import com.soar.network.bean.request.LoginRequest
import com.soar.network.bean.response.LoginResponse

object LocalData{

    fun doLogin(loginRequest: LoginRequest): Resource<LoginResponse> {
        if (loginRequest == LoginRequest("ahmed@ahmed.ahmed", "ahmed")) {
            return Resource.Success(LoginResponse("123", "Ahmed", "Mahmoud",
                    "FrunkfurterAlle", "77", "12000", "Berlin",
                    "Germany", "ahmed@ahmed.ahmed"))
        }
        return Resource.DataError(-101)
    }
}


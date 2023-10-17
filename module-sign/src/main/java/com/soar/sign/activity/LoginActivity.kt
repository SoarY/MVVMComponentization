package com.soar.sign.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.soar.common.router.RouteConstants
import com.soar.mvvm.base.BaseActivity
import com.soar.network.bean.Resource
import com.soar.network.bean.response.LoginResponse
import com.soar.sign.databinding.SignActivityLoginBinding
import com.soar.sign.vm.LoginViewModel

/**
 * NAME：YONG_
 * Created at: 2023/8/24 17
 * Describe:
 */
@Route(path = RouteConstants.Sign.SIGN_LOGIN)
class LoginActivity : BaseActivity<SignActivityLoginBinding>(){

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        observeViewModel()
    }

    private fun initView() {
        binding.login.setOnClickListener { doLogin() }
    }

    private fun doLogin() {
        loginViewModel.doLogin(
            binding.username.text.trim().toString(),
            binding.password.text.toString()
        )
    }

    fun observeViewModel() {
        loginViewModel.loginLiveData.observe(this, ::handleLoginResult)
    }

    private fun handleLoginResult(status: Resource<LoginResponse>) {
        Log.i(TAG, "handleLoginResult: $status")
        when (status) {
            is Resource.Loading -> binding.loaderView.visibility= View.VISIBLE
            is Resource.Success -> status.data?.let {
                binding.loaderView.visibility= View.GONE
                ARouter.getInstance()
                    .build(RouteConstants.Delicacy.DELICACY_RECIPES)
                    .navigation(this)
                finish()
            }
            is Resource.DataError -> {
                binding.loaderView.visibility= View.GONE
                status.errorCode?.let {
                    Log.i(TAG, "handleLoginResult: $status.errorCode")
                }
            }
        }
    }
}
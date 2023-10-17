package com.soar.delicacy.repository

import com.soar.common.base.BaseApplication
import com.soar.network.bean.Resource
import com.soar.network.bean.response.Recipes
import com.soar.network.bean.response.RecipesItem
import com.soar.network.constant.APIMain
import com.soar.network.retrofit.RetrofitClient
import com.soar.network.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * NAME：YONG_
 * Created at: 2023/9/6 10
 * Describe:
 */
object RecipesListRepository {

    fun requestRecipes(): Flow<Resource<Recipes>> {
        return flow {
            emit(exRequestRecipes())
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun exRequestRecipes(): Resource<Recipes> {
        if (!NetworkUtils.isNetworkConnected(BaseApplication.context))
            return Resource.DataError(-1)

        val fetchRecipes = RetrofitClient.getApi(APIMain.API_HF)!!.fetchRecipes()
        if (fetchRecipes.isSuccessful)
            return Resource.Success(Recipes(fetchRecipes.body() as ArrayList<RecipesItem>))
        else
            return Resource.DataError(fetchRecipes.code())
    }
}
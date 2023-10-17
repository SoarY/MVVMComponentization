package com.soar.delicacy.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.soar.delicacy.repository.RecipesListRepository
import com.soar.mvvm.base.BaseViewModel
import com.soar.network.bean.Resource
import com.soar.network.bean.response.Recipes
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * NAME：YONG_
 * Created at: 2023/9/5 14
 * Describe:
 */
class RecipesListViewModel : BaseViewModel(){

    val recipesLiveData = MutableLiveData<Resource<Recipes>>()

    fun getRecipes() {
        viewModelScope.launch {
            recipesLiveData.value = Resource.Loading()
            RecipesListRepository.requestRecipes().collect {
                recipesLiveData.value = it
            }
        }
    }
}
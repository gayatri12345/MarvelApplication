package com.sample.marvelapplication.presentation.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter
import com.sample.domain.usecase.characterlist.GetCharacterListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model class for Character
 */
class CharacterListViewModel @Inject constructor(
    private val geCharacterListUseCase: GetCharacterListUseCase
) : ViewModel() {

    val characterList: MutableLiveData<List<MarvelCharacter>> = MutableLiveData(listOf())
    val isLoad = MutableLiveData(true)
    val showErrorMessage = MutableLiveData(false)

    /**
     * This method gets character list
     */
    internal fun getCharacterList() {
        viewModelScope.launch {
            when (val characterListResult = geCharacterListUseCase.invoke()) {
                is Result.Success -> {
                    isLoad.postValue(false)
                    characterList.value = characterListResult.data
                    showErrorMessage.postValue(false)
                }
                is Result.Error -> {
                    isLoad.postValue(false)
                    characterList.value = null
                    showErrorMessage.postValue(true)

                }
            }
        }
    }
}

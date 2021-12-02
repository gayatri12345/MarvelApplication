package com.sample.marvelapplication.presentation.characterdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter
import com.sample.domain.usecase.characterdetails.GetCharacterDetailsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model class for Character
 */
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {

    val characterDetails: MutableLiveData<MarvelCharacter> = MutableLiveData()
    val isLoad = MutableLiveData(false)
    val showErrorMessage = MutableLiveData(false)

    /**
     * This method gets character details
     * @param characterId
     */
    internal fun getCharacterDetails(characterId: Int) {
        viewModelScope.launch {
            isLoad.postValue(true)
            getCharacterDetailsUseCase.invoke(characterId)
            when (val characterDetailResult = getCharacterDetailsUseCase.invoke(characterId)) {
                is Result.Success -> {
                    isLoad.postValue(false)
                    characterDetails.postValue(characterDetailResult.data)
                    showErrorMessage.postValue(false)
                }
                is Result.Error -> {
                    isLoad.postValue(false)
                    characterDetails.postValue(null)
                    showErrorMessage.postValue(true)
                }
            }
        }
    }
}

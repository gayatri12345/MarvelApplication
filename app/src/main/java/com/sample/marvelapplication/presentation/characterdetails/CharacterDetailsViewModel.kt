package com.sample.marvelapplication.presentation.characterdetails

import androidx.lifecycle.*
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
    val errorMessage = MutableLiveData<String>()

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
                    characterDetails.value = characterDetailResult.data
                }
                is Result.Error -> {
                    isLoad.postValue(false)
                    characterDetails.value = null
                    errorMessage.postValue(characterDetailResult.exception.message)
                }
            }
        }
    }
}

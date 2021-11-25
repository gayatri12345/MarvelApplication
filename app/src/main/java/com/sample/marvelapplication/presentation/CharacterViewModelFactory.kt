package com.sample.marvelapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.domain.usecase.characterdetails.GetCharacterDetailsUseCase
import com.sample.domain.usecase.characterlist.GetCharacterListUseCase
import com.sample.marvelapplication.presentation.characterdetails.CharacterDetailsViewModel
import com.sample.marvelapplication.presentation.characterlist.CharacterListViewModel

/**
 * ViewModelFactory
 * @param geCharacterListUseCase
 * @param getCharacterDetailsUseCase
 */

class CharacterViewModelFactory(
    private val geCharacterListUseCase: GetCharacterListUseCase,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            return CharacterDetailsViewModel(getCharacterDetailsUseCase) as T
        } else {
            return CharacterListViewModel(geCharacterListUseCase) as T
        }
    }
}

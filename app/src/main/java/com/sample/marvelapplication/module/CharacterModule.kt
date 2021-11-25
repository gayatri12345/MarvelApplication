package com.sample.marvelapplication.module

import com.sample.data.di.DataModule
import com.sample.domain.repository.CharacterDetailsRepository
import com.sample.domain.repository.CharacterListRepository
import com.sample.domain.usecase.characterdetails.GetCharacterDetailsUseCase
import com.sample.domain.usecase.characterdetails.GetCharacterDetailsUseCaseImpl
import com.sample.domain.usecase.characterlist.GetCharacterListUseCase
import com.sample.domain.usecase.characterlist.GetCharacterListUseCaseImpl

/**
 * Get UseCase Data
 */
object CharacterModule {
    private val characterListRepository: CharacterListRepository
        get() = DataModule.provideCharacterListRepository()

    private val characterDetailsRepository: CharacterDetailsRepository
        get() = DataModule.provideCharacterDetailsRepository()

    val getCharacterListUseCase: GetCharacterListUseCase
        get() = GetCharacterListUseCaseImpl(characterListRepository)

    val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
        get() = GetCharacterDetailsUseCaseImpl(characterDetailsRepository)
}

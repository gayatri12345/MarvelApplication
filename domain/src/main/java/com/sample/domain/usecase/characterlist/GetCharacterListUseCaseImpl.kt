package com.sample.domain.usecase.characterlist

import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter
import com.sample.domain.repository.CharacterListRepository
import javax.inject.Inject

/**
 * CharacterListUseCase implementation
 */
class GetCharacterListUseCaseImpl @Inject constructor(private val characterListRepository: CharacterListRepository) :
    GetCharacterListUseCase {
    override suspend fun invoke(): Result<List<MarvelCharacter>> =
        characterListRepository.getCharacters()
}

package com.sample.domain.usecase.characterdetails

import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter
import com.sample.domain.repository.CharacterDetailsRepository
import javax.inject.Inject

/**
 * CharacterDetailsUseCase implementation
 */
class GetCharacterDetailsUseCaseImpl @Inject constructor(private val characterDetailsRepository: CharacterDetailsRepository) :
    GetCharacterDetailsUseCase {
    override suspend fun invoke(characterId: Int): Result<MarvelCharacter> =
        characterDetailsRepository.getCharacterDetails(characterId)
}

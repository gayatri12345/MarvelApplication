package com.sample.domain.usecase.characterdetails

import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter

/**
 * This is usecase interface for character details
 */
interface GetCharacterDetailsUseCase {
    suspend fun invoke(characterId: Int): Result<MarvelCharacter>
}

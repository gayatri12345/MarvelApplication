package com.sample.domain.repository

import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter

/**
 * This is CharacterDetails repository interface
 */
interface CharacterDetailsRepository {
    suspend fun getCharacterDetails(characterId: Int): Result<MarvelCharacter>
}

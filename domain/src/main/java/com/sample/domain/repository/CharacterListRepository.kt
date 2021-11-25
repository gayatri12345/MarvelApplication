package com.sample.domain.repository

import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter

/**
 * This is CharacterList repository interface
 */
interface CharacterListRepository {
    suspend fun getCharacters(): Result<List<MarvelCharacter>>
}
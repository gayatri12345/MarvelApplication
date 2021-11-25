package com.sample.data.source.characterlist

import com.sample.domain.common.Result
import com.sample.data.entities.Results
import com.sample.domain.model.MarvelCharacter

/**
 * Interface for CharacterListDataSource
 */
interface CharacterListDataSource {
    suspend fun getCharacters(): Result<List<MarvelCharacter>>
}

package com.sample.data.datasource.source.characterlist

import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter

/**
 * Interface for CharacterListDataSource
 */
interface CharacterListDataSource {
    suspend fun getCharacters(): Result<List<MarvelCharacter>>
}

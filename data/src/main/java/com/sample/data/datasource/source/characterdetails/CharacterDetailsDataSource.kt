package com.sample.data.datasource.source.characterdetails

import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter

/**
 * Interface for CharacterDetailsDataSource
 */
interface CharacterDetailsDataSource {
    suspend fun getCharacterDetails(characterId: Int): Result<MarvelCharacter>
}

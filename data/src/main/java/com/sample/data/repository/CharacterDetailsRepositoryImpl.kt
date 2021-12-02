package com.sample.data.repository

import com.sample.data.datasource.source.characterdetails.CharacterDetailsDataSource
import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter
import com.sample.domain.repository.CharacterDetailsRepository
import javax.inject.Inject

/**
 * This class is CharacterDetailsRepository implementation
 */
class CharacterDetailsRepositoryImpl @Inject constructor(
    private val characterDetailsDataSource: CharacterDetailsDataSource
) : CharacterDetailsRepository {
    override suspend fun getCharacterDetails(characterId: Int): Result<MarvelCharacter> =
        characterDetailsDataSource.getCharacterDetails(characterId)
}

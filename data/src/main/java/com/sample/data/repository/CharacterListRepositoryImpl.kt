package com.sample.data.repository

import com.sample.data.source.characterlist.CharacterListDataSource
import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter
import com.sample.domain.repository.CharacterListRepository
import javax.inject.Inject

/**
 * This class is CharacterListRepository implementation
 */
class CharacterListRepositoryImpl @Inject constructor(
    private val characterListDataSource: CharacterListDataSource
) : CharacterListRepository {
    override suspend fun getCharacters(): Result<List<MarvelCharacter>> =
        characterListDataSource.getCharacters()
}

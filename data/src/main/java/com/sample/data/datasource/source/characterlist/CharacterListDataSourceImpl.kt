package com.sample.data.datasource.source.characterlist

import com.sample.data.datasource.api.CharacterApi
import com.sample.data.datasource.mapper.charcterlist.CharacterListResponseMapper
import com.sample.domain.model.MarvelCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.sample.domain.common.Result

/**
 * This class is CharacterListDataSource implementation
 */
class CharacterListDataSourceImpl @Inject constructor(
    private val service: CharacterApi,
    private val mapper: CharacterListResponseMapper
) : CharacterListDataSource {
    override suspend fun getCharacters(): Result<List<MarvelCharacter>> =
        withContext(
            Dispatchers.IO
        ) {
            try {
                val response = service.getCharacterList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        return@withContext Result.Success(
                            mapper.toCharacterList(it)
                        )
                    } ?: return@withContext Result.Error(Exception())

                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}

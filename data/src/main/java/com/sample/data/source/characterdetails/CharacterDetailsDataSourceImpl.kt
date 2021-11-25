package com.sample.data.source.characterdetails

import com.sample.data.api.CharacterApi
import com.sample.data.mapper.characterdetails.CharacterDetailsResponseMapper
import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This class is CharacterDeatilsDataSource implementation
 */
class CharacterDetailsDataSourceImpl @Inject constructor(
    private val service: CharacterApi,
    private val mapper: CharacterDetailsResponseMapper
) : CharacterDetailsDataSource {
    override suspend fun getCharacterDetails(characterId: Int): Result<MarvelCharacter> =
        withContext(
            Dispatchers.IO
        ) {
            try {
                val response = service.getCharacterDetail(characterId = characterId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        return@withContext Result.Success(mapper.toCharacterDetails(it))
                    } ?: return@withContext Result.Error(Exception())
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}

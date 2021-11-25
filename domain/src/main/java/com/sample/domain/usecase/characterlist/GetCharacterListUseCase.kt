package com.sample.domain.usecase.characterlist

import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter

/**
 * This is usecase interface for character list
 */
interface GetCharacterListUseCase{
    suspend fun invoke() : Result<List<MarvelCharacter>>
}

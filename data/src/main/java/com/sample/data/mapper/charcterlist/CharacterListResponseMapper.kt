package com.sample.data.mapper.charcterlist

import com.sample.data.entities.CharacterListResponse
import com.sample.domain.model.MarvelCharacter

/**
 * This interface is for CharacterListResponseMapper
 */
interface CharacterListResponseMapper {
    fun toCharacterList(response: CharacterListResponse): List<MarvelCharacter>
}

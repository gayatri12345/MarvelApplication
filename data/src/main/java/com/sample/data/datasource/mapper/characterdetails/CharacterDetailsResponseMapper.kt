package com.sample.data.datasource.mapper.characterdetails

import com.sample.data.entities.CharacterListResponse
import com.sample.domain.model.MarvelCharacter

/**
 * This interface is for CharacterDetailsResponseMapper
 */
interface CharacterDetailsResponseMapper {
    fun toCharacterDetails(response: CharacterListResponse): MarvelCharacter
}

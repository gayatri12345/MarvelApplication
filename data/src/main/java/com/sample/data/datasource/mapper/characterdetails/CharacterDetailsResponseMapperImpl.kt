package com.sample.data.datasource.mapper.characterdetails

import com.sample.data.entities.CharacterListResponse
import com.sample.domain.model.MarvelCharacter

/**
 * This class is for CharacterDetailsResponseMapper implementation
 */
class CharacterDetailsResponseMapperImpl : CharacterDetailsResponseMapper {
    override fun toCharacterDetails(response: CharacterListResponse): MarvelCharacter =
        response.data.results.first().let {
            MarvelCharacter(
                id = it.id,
                name = it.name,
                description = it.description,
                modified = it.modified,
                thumbnail = it.thumbnail.path + "." + it.thumbnail.extension
            )
        }
}

package com.sample.data.mapper.characterdetails

import com.sample.data.entities.CharacterListResponse
import com.sample.domain.model.CharacterThumbnail
import com.sample.domain.model.MarvelCharacter

/**
 * This class is for CharacterDetailsResponseMapper implementation
 */
class CharacterDetailsResponseMapperImpl : CharacterDetailsResponseMapper {
    override fun toCharacterDetails(response: CharacterListResponse): MarvelCharacter =
        response.data.results[0].let {
            MarvelCharacter(
                id = it.id,
                name = it.name,
                description = it.description,
                modified = it.modified,
                thumbnail = CharacterThumbnail(
                    path = it.thumbnail.path,
                    extension = it.thumbnail.extension
                )
            )
        }
}

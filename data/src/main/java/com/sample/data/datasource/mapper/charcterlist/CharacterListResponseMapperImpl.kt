package com.sample.data.datasource.mapper.charcterlist

import com.sample.data.entities.CharacterListResponse
import com.sample.domain.model.MarvelCharacter

/**
 * This class is for CharacterListResponseMapper implementation
 */
class CharacterListResponseMapperImpl : CharacterListResponseMapper {
    override fun toCharacterList(response: CharacterListResponse): List<MarvelCharacter> =
        response.data.results.map {
            MarvelCharacter(
                id = it.id,
                name = it.name,
                description = it.description,
                modified = it.modified,
                thumbnail = it.thumbnail.path + "." + it.thumbnail.extension
            )
        }
}

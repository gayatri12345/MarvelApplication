package com.sample.data.mapper.characterdetails

import com.sample.data.entities.CharacterListResponse
import com.sample.data.entities.Data
import com.sample.data.entities.Results
import com.sample.data.entities.Thumbnail
import com.sample.domain.model.CharacterThumbnail
import com.sample.domain.model.MarvelCharacter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert

@RunWith(JUnit4::class)
class CharacterDetailsResponseMapperImplTest {
    private lateinit var characterListResponse: CharacterListResponse
    private lateinit var characterDetailsResponseMapper: CharacterDetailsResponseMapper
    private lateinit var marvelCharacter: MarvelCharacter

    @Before
    fun setUp() {
        characterListResponse = CharacterListResponse(
            data = Data(
                listOf(
                    Results(
                        1017100, "BOB", "Test",
                        "Test",
                        Thumbnail("Test", "Test")
                    )
                )
            )
        )
        characterDetailsResponseMapper = CharacterDetailsResponseMapperImpl()
        marvelCharacter =
            MarvelCharacter(
                1017100, "BOB", "Test",
                "Test",
                CharacterThumbnail("Test", "Test")
            )
    }

    @Test
    fun toCharacterDetailsTest() {
        val result = characterDetailsResponseMapper.toCharacterDetails(characterListResponse)
        Assert.assertEquals(result, marvelCharacter)
    }
}

package com.sample.data.datasource.mapper.characterlist

import com.sample.data.entities.CharacterListResponse
import com.sample.data.entities.Data
import com.sample.data.entities.Results
import com.sample.data.entities.Thumbnail
import com.sample.data.datasource.mapper.charcterlist.CharacterListResponseMapper
import com.sample.data.datasource.mapper.charcterlist.CharacterListResponseMapperImpl
import com.sample.domain.model.MarvelCharacter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert


@RunWith(JUnit4::class)
class CharacterListResponseMapperImplTest {
    private lateinit var characterListResponse: CharacterListResponse
    private lateinit var characterListResponseMapper: CharacterListResponseMapper
    private lateinit var list: List<MarvelCharacter>

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
        characterListResponseMapper = CharacterListResponseMapperImpl()
        list = listOf(
            MarvelCharacter(
                1017100, "BOB", "Test",
                "Test","Test.Test"
            )
        )
    }

    @Test
    fun toCharacterListTest() {
        val result = characterListResponseMapper.toCharacterList(characterListResponse)
        Assert.assertEquals(result, list)
        Assert.assertTrue(result.containsAll(list))
    }
}

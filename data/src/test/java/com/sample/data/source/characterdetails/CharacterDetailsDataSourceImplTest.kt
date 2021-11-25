package com.sample.data.source.characterdetails

import com.nhaarman.mockitokotlin2.whenever
import com.sample.data.api.CharacterApi
import com.sample.data.entities.CharacterListResponse
import com.sample.data.entities.Data
import com.sample.data.entities.Results
import com.sample.data.entities.Thumbnail
import com.sample.data.mapper.characterdetails.CharacterDetailsResponseMapper
import com.sample.data.mapper.characterdetails.CharacterDetailsResponseMapperImpl
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CharacterDetailsDataSourceImplTest {

    @MockK
    lateinit var characterApi: CharacterApi

    private lateinit var characterDetailsResponseMapper: CharacterDetailsResponseMapper
    private lateinit var characterDetailsDataSource: CharacterDetailsDataSource

    @Before
    fun setUp() {
        //Used for initiation of Mockk
        characterApi = Mockito.mock(CharacterApi::class.java)
        characterDetailsResponseMapper = CharacterDetailsResponseMapperImpl()
        characterDetailsDataSource =
            CharacterDetailsDataSourceImpl(characterApi, characterDetailsResponseMapper)
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetCharactersDetails() = runBlocking {
        whenever(characterApi.getCharacterDetail(1017100)).thenReturn(
            Response.success(
                CharacterListResponse(
                    Data(
                        listOf(
                            Results(
                                1017100, "Test", "Test", "Test",
                                Thumbnail("Test", "test")
                            )
                        )
                    )
                )
            )
        )
        val serviceResult = characterApi.getCharacterDetail(1017100)
        whenever(serviceResult.isSuccessful).thenReturn(
            true
        )
        val result = characterDetailsDataSource.getCharacterDetails(1017100)
        Assert.assertEquals(serviceResult.isSuccessful, true)
        Assert.assertNotNull(result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetCharacterDetailsError() = runBlocking {
        whenever(characterApi.getCharacterDetail(1017100)).thenReturn(
            Response.success(
                CharacterListResponse(
                    Data(
                        listOf(
                            Results(
                                1017100, "Test", "Test", "Test",
                                Thumbnail("Test", "test")
                            )
                        )
                    )
                )
            )
        )
        val serviceResult = characterApi.getCharacterDetail(1017100)
        whenever(serviceResult.isSuccessful).thenReturn(
            false
        )
        val result = characterDetailsDataSource.getCharacterDetails(1017100)
        Assert.assertEquals(!serviceResult.isSuccessful, false)
        Assert.assertNotNull(result)
    }
}

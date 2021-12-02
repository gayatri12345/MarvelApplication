package com.sample.data.datasource.source.characterlist

import com.nhaarman.mockitokotlin2.whenever
import com.sample.data.datasource.api.CharacterApi
import com.sample.data.entities.CharacterListResponse
import com.sample.data.entities.Data
import com.sample.data.entities.Results
import com.sample.data.entities.Thumbnail
import com.sample.data.datasource.mapper.charcterlist.CharacterListResponseMapper
import com.sample.data.datasource.mapper.charcterlist.CharacterListResponseMapperImpl
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
class CharacterListDataSourceImplTest {

    @MockK
    lateinit var characterApi: CharacterApi

    @MockK
    lateinit var characterListResponseMapper: CharacterListResponseMapper
    private lateinit var characterListDataSource: CharacterListDataSourceImpl

    @Before
    fun setUp() {
        //Used for initiation of Mockk
        characterApi = Mockito.mock(CharacterApi::class.java)
        characterListResponseMapper = CharacterListResponseMapperImpl()
        MockitoAnnotations.initMocks(this)
        characterListDataSource =
            CharacterListDataSourceImpl(characterApi, characterListResponseMapper)
    }

    @Test
    fun testGetCharacters() = runBlocking {
        whenever(characterApi.getCharacterList()).thenReturn(
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
        val serviceResult = characterApi.getCharacterList()
        whenever(serviceResult.isSuccessful).thenReturn(
            true
        )
        val result = characterListDataSource.getCharacters()
        Assert.assertEquals(serviceResult.isSuccessful, true)
        Assert.assertNotNull(result)
    }

    @Test
    fun testGetCharactersError() = runBlocking {
        whenever(characterApi.getCharacterList()).thenReturn(
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
        val serviceResult = characterApi.getCharacterList()
        whenever(serviceResult.isSuccessful).thenReturn(
            false
        )
        val result = characterListDataSource.getCharacters()
        Assert.assertEquals(!serviceResult.isSuccessful, false)
        Assert.assertNotNull(result)
    }
}

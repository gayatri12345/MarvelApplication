package com.sample.data.repository

import com.nhaarman.mockitokotlin2.whenever
import com.sample.data.datasource.source.characterlist.CharacterListDataSource
import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CharacterListRepositoryImplTest {
    @MockK
    lateinit var characterListDataSource: CharacterListDataSource
    private lateinit var characterListRepository: CharacterListRepositoryImpl

    @Before
    fun setUp() {
        //Used for initiation of Mockk
        characterListDataSource = Mockito.mock(CharacterListDataSource::class.java)
        MockitoAnnotations.initMocks(this)
        characterListRepository = CharacterListRepositoryImpl(characterListDataSource)
    }

    @Test
    fun getCharactersTest() = runBlockingTest {
        whenever(characterListDataSource.getCharacters()).thenReturn(
            Result.Success(
                listOf(
                    MarvelCharacter(
                        1017100, "BOB", "Test",
                        "Test", "Test.Test"
                    )
                )
            )
        )
        val result = characterListRepository.getCharacters()
        Assert.assertTrue(result is Result.Success)
    }

    @Test
    fun getCharactersErrorTest() = runBlockingTest {
        whenever(characterListDataSource.getCharacters()).thenReturn(
            Result.Error(
                Exception("401 Unauthorized")
            )
        )
        val result = characterListRepository.getCharacters()
        Assert.assertTrue(result is Result.Error)
    }
}

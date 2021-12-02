package com.sample.data.repository

import com.nhaarman.mockitokotlin2.whenever
import com.sample.data.datasource.source.characterdetails.CharacterDetailsDataSource
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
class CharacterDetailsRepositoryImplTest {
    @MockK
    lateinit var characterDetailsDataSource: CharacterDetailsDataSource
    private lateinit var characterDetailsRepository: CharacterDetailsRepositoryImpl

    @Before
    fun setUp() {
        //Used for initiation of Mockk
        characterDetailsDataSource = Mockito.mock(CharacterDetailsDataSource::class.java)
        MockitoAnnotations.initMocks(this)
        characterDetailsRepository = CharacterDetailsRepositoryImpl(characterDetailsDataSource)
    }

    @Test
    fun getCharactersTest() = runBlockingTest {
        whenever(characterDetailsDataSource.getCharacterDetails(1017100)).thenReturn(
            Result.Success(
                    MarvelCharacter(
                        1017100, "BOB", "Test",
                        "Test","Test.Test"
                    )
            )
        )
        val result = characterDetailsRepository.getCharacterDetails(1017100)
        Assert.assertTrue(result is Result.Success)
    }

    @Test
    fun getCharactersTestError() = runBlockingTest {
        whenever(characterDetailsDataSource.getCharacterDetails(1017100)).thenReturn(
            Result.Error(
                Exception("401 Unauthorized")
            )
        )
        val result = characterDetailsRepository.getCharacterDetails(1017100)
        Assert.assertTrue(result is Result.Error)
    }
}

package com.sample.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.sample.domain.common.Result
import com.sample.domain.model.CharacterThumbnail
import com.sample.domain.model.MarvelCharacter
import com.sample.domain.repository.CharacterDetailsRepository
import com.sample.domain.usecase.characterdetails.GetCharacterDetailsUseCaseImpl
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception

@RunWith(JUnit4::class)
class GetCharacterDetailsUserCaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var characterDetailsRepository: CharacterDetailsRepository
    private lateinit var getCharacterDetailsUserCase: GetCharacterDetailsUseCaseImpl

    @Before
    fun start() {
        //Used for initiation of Mockk
        characterDetailsRepository = Mockito.mock(CharacterDetailsRepository::class.java)
        MockitoAnnotations.initMocks(this)
        getCharacterDetailsUserCase = GetCharacterDetailsUseCaseImpl(characterDetailsRepository)
    }

    @Test
    fun testInvokeSuccess() = runBlocking {
        whenever(characterDetailsRepository.getCharacterDetails(1017100)).thenReturn(
            Result.Success(
                    MarvelCharacter(
                        1017100, "BOB", "Test",
                        "Test",
                        CharacterThumbnail("Test", "Test")
                    )
            )
        )
        val result = getCharacterDetailsUserCase.invoke(1017100)
        Assert.assertTrue(result is Result.Success)
    }

    @Test
    fun testInvokeError() = runBlocking {
        whenever(characterDetailsRepository.getCharacterDetails(1017100)).thenReturn(
            Result.Error(
                Exception("401 Unauthorized")
            )
        )
        val result = getCharacterDetailsUserCase.invoke(1017100)
        Assert.assertTrue(result is Result.Error)
    }
}

package com.sample.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.sample.domain.common.Result
import com.sample.domain.model.MarvelCharacter
import com.sample.domain.repository.CharacterListRepository
import com.sample.domain.usecase.characterlist.GetCharacterListUseCaseImpl
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
class GetCharacterListUserCaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var characterListRepository: CharacterListRepository
    private lateinit var getCharacterListUseCase: GetCharacterListUseCaseImpl

    @Before
    fun start() {
        //Used for initiation of Mockk
        characterListRepository = Mockito.mock(CharacterListRepository::class.java)
        MockitoAnnotations.initMocks(this)
        getCharacterListUseCase = GetCharacterListUseCaseImpl(characterListRepository)

    }

    @Test
    fun testInvokeSuccess() = runBlocking {
        whenever(characterListRepository.getCharacters()).thenReturn(
            Result.Success(
                listOf(
                    MarvelCharacter(
                        1017100, "BOB", "Test",
                        "Test",
                        "Test.Test"
                    )
                )
            )
        )
        val result = getCharacterListUseCase.invoke()
        Assert.assertTrue(result is Result.Success)
    }

    @Test
    fun testInvokeError() = runBlocking {
        whenever(characterListRepository.getCharacters()).thenReturn(
            Result.Error(
                Exception("401 Unauthorized")
            )
        )
        val result = getCharacterListUseCase.invoke()
        Assert.assertTrue(result is Result.Error)
    }
}

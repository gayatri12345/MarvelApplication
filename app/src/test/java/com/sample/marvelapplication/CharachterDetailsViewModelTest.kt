package com.sample.marvelapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.sample.domain.common.Result
import com.sample.domain.model.CharacterThumbnail
import com.sample.domain.model.MarvelCharacter
import com.sample.domain.repository.CharacterDetailsRepository
import com.sample.domain.usecase.characterdetails.GetCharacterDetailsUseCaseImpl
import com.sample.marvelapplication.presentation.characterdetails.CharacterDetailsViewModel
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.lang.Exception

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CharachterDetailsViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var getCharacterDetailsUseCase: GetCharacterDetailsUseCaseImpl

    @MockK
    lateinit var characterDetailsRepository: CharacterDetailsRepository

    lateinit var charachtersViewModel: CharacterDetailsViewModel

    @Before
    fun start() {
        //Used for initiation of Mockk
        Dispatchers.setMain(testDispatcher)
        characterDetailsRepository = mock(CharacterDetailsRepository::class.java)
        MockitoAnnotations.initMocks(this)
        getCharacterDetailsUseCase = GetCharacterDetailsUseCaseImpl(characterDetailsRepository)
        charachtersViewModel =
            CharacterDetailsViewModel(getCharacterDetailsUseCase)

    }

    @Test
    fun testGetCharacterId() = runBlocking {
        whenever(getCharacterDetailsUseCase.invoke(1017100)).thenReturn(
            Result.Success(
                listOf(
                    MarvelCharacter(
                        1017100, "BOB", "Test",
                        "Test",
                        CharacterThumbnail("Test", "Test")
                    )
                )
            )
        )
        val result = charachtersViewModel.getCharacterDetails(1017100)
        Assert.assertNotNull(result)
    }

    @Test
    fun testGetCharacterIdError() = runBlocking {
        whenever(getCharacterDetailsUseCase.invoke(1017100)).thenReturn(
            Result.Error(
                Exception("401 Unauthorized")
            )
        )
        val result = charachtersViewModel.getCharacterDetails(1017100)
        Assert.assertNotNull(result)
    }
}

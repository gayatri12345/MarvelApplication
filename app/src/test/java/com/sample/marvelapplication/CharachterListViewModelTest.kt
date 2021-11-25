package com.sample.marvelapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.sample.domain.common.Result
import com.sample.domain.model.CharacterThumbnail
import com.sample.domain.model.MarvelCharacter
import com.sample.domain.repository.CharacterListRepository
import com.sample.domain.usecase.characterlist.GetCharacterListUseCaseImpl
import com.sample.marvelapplication.presentation.characterlist.CharacterListViewModel
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
class CharachterListViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var getCharacterListUseCase: GetCharacterListUseCaseImpl

    @MockK
    lateinit var characterListRepository: CharacterListRepository

    lateinit var characterListsViewModel: CharacterListViewModel

    @Before
    fun start() {
        //Used for initiation of Mockk
        Dispatchers.setMain(testDispatcher)
        characterListRepository = mock(CharacterListRepository::class.java)
        MockitoAnnotations.initMocks(this)
        getCharacterListUseCase = GetCharacterListUseCaseImpl(characterListRepository)
        characterListsViewModel =
            CharacterListViewModel(getCharacterListUseCase)
    }

    @Test
    fun testGetCharacterList() = runBlocking {
        whenever(getCharacterListUseCase.invoke()).thenReturn(
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
        val result = characterListsViewModel.getCharacterList()
        Assert.assertNotNull(result)
    }

    @Test
    fun testGetCharacterListError() = runBlocking {
        whenever(getCharacterListUseCase.invoke()).thenReturn(
            Result.Error(
                Exception("401 Unauthorized")
            )
        )
        val result = characterListsViewModel.getCharacterList()
        Assert.assertNotNull(result)
    }
}

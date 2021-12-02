package com.sample.data.di

import com.sample.data.datasource.mapper.characterdetails.CharacterDetailsResponseMapperImpl
import com.sample.data.datasource.mapper.charcterlist.CharacterListResponseMapperImpl
import com.sample.data.datasource.source.characterdetails.CharacterDetailsDataSourceImpl
import com.sample.data.repository.CharacterDetailsRepositoryImpl
import com.sample.data.datasource.source.characterlist.CharacterListDataSourceImpl
import com.sample.data.repository.CharacterListRepositoryImpl

object DataModule {
    private val networkModule by lazy {
        NetworkModule()
    }

    @Volatile
    var characterListRepository: CharacterListRepositoryImpl? = null

    @Volatile
    var characterDetailsRepository: CharacterDetailsRepositoryImpl? = null

    /**
     * This method provides character list repository
     */
    fun provideCharacterListRepository(): CharacterListRepositoryImpl {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return characterListRepository ?: createCharacterRepository()
        }
    }

    /**
     * This method provides character details repository
     */
    fun provideCharacterDetailsRepository(): CharacterDetailsRepositoryImpl {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return characterDetailsRepository ?: createCharacterDetailsRepository()
        }
    }

    /**
     * This method creates character repository
     */
    private fun createCharacterRepository(): CharacterListRepositoryImpl {
        val newRepo =
            CharacterListRepositoryImpl(
                CharacterListDataSourceImpl(
                    networkModule.createCharacterApi(),
                    CharacterListResponseMapperImpl()
                )
            )
        characterListRepository = newRepo
        return newRepo
    }

    /**
     * This method creates character details repository
     */
    private fun createCharacterDetailsRepository(): CharacterDetailsRepositoryImpl {
        val newRepo =
            CharacterDetailsRepositoryImpl(
                CharacterDetailsDataSourceImpl(
                    networkModule.createCharacterApi(),
                    CharacterDetailsResponseMapperImpl()
                )
            )
        characterDetailsRepository = newRepo
        return newRepo
    }
}

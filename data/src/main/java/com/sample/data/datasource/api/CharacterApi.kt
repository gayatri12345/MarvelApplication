package com.sample.data.datasource.api

import com.sample.data.entities.CharacterListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * This is the interface for Character apis
 */
interface CharacterApi {

    @GET("/v1/public/characters")
    suspend fun getCharacterList(): Response<CharacterListResponse>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterDetail(@Path(API_CHARACTER_ID) characterId: Int): Response<CharacterListResponse>

    companion object {
        private const val API_CHARACTER_ID = "characterId"
    }
}

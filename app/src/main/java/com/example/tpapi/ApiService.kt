package com.example.tpapi

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("offre")
    suspend fun getOffres(): Response<MutableList<Offer>>

    @GET("offre/{id}")
    suspend fun getOffres(@Path("id") id:Int): Response<Offer>

    @POST("offre")
    suspend fun saveOffre(@Body newOffre: Offer): Response<Offer>

    @DELETE("offre/{id}")
    suspend fun deleteOffre(@Path("id") id: Int): Response<Unit>

    @PUT("offre/{id}")
    suspend fun updateOffre(@Path("id") id: Int, @Body offre: Offer): Response<Offer>
}

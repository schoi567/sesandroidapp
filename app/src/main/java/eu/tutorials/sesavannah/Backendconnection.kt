package eu.tutorials.sesavannah

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Backendconnection {
    @FormUrlEncoded
    @POST("/applicants")
    fun createApplicant(
        @Field("firstname") firstName: String,
        @Field("lastname") lastName: String
        //... other fields
    ): Call<Void>  // Void if the server sends no response body
}

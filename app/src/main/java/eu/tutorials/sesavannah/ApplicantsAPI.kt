package eu.tutorials.sesavannah

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApplicantsAPI {
    @FormUrlEncoded
    @POST("/applicants")
    fun createApplicant(
        @Field("firstname") firstName: String,
        @Field("lastname") lastName: String
    ): Call<Void>
}

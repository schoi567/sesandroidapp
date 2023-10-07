package eu.tutorials.sesavannah
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.GET

interface ApplicantsAPI {
    @FormUrlEncoded
    @POST("/applicants")
    fun createApplicant(
        @Field("firstname") firstName: String,
        @Field("lastname") lastName: String
    ): Call<Void>

    @GET("/admin")
    fun getAdminData(): Call<List<String>>  // Assuming the data is a list of strings for simplicity. Adjust accordingly.


}

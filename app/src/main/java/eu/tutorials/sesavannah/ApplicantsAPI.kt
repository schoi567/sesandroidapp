package eu.tutorials.sesavannah
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.Part

interface ApplicantsAPI {
    @Multipart
    @POST("/applicants")
    fun createApplicant(
        @Part("firstname") firstName: RequestBody,
        @Part("lastname") lastName: RequestBody,
        @Part("email") email: RequestBody,
        @Part("department") selectedDepartment: RequestBody,
        @Part resumePart: MultipartBody.Part
    ): Call<Void>

    @GET("/admin")
    fun getAdminData(): Call<List<AdminData>>
}
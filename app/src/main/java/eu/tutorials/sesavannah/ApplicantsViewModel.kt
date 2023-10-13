package eu.tutorials.sesavannah
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplicantsViewModel : ViewModel() {

    private val _adminDataLiveData = MutableLiveData<List<AdminData>>()
    val adminDataLiveData: LiveData<List<AdminData>> get() = _adminDataLiveData

    private val _responseLiveData = MutableLiveData<Response<Void>>()
    val responseLiveData: LiveData<Response<Void>> get() = _responseLiveData

    fun fetchAdminData() {
        val request = ServiceBuilder.buildService(ApplicantsAPI::class.java)
        val call = request.getAdminData()

        call.enqueue(object : Callback<List<AdminData>> {
            override fun onResponse(call: Call<List<AdminData>>, response: Response<List<AdminData>>) {
                if (response.isSuccessful) {
                    _adminDataLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<AdminData>>, t: Throwable) {
                // Handle error, e.g. update another LiveData with error information
            }
        })
    }

    fun createApplicant(firstName: String, lastName: String, email: String, department: String, resumePart: MultipartBody.Part) {
        val request = ServiceBuilder.buildService(ApplicantsAPI::class.java)

        val firstNameReq = createPlainTextRequestBody(firstName)
        val lastNameReq = createPlainTextRequestBody(lastName)
        val emailReq = createPlainTextRequestBody(email)
        val departmentReq = createPlainTextRequestBody(department)

        val call = request.createApplicant(firstNameReq, lastNameReq, emailReq, departmentReq, resumePart)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                _responseLiveData.postValue(response)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle error, e.g. update another LiveData with error info
            }
        })
    }

    private fun createPlainTextRequestBody(content: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), content)
    }
}

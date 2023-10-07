package eu.tutorials.sesavannah
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.LiveData

class ApplicantsViewModel : ViewModel() {

    private val _responseLiveData: MutableLiveData<Response<Void>> = MutableLiveData()
    val responseLiveData: LiveData<Response<Void>>
        get() = _responseLiveData

    fun createApplicant(firstName: String, lastName: String) {  // Assuming only two parameters for simplicity
        val request: ApplicantsAPI = ServiceBuilder.buildService(ApplicantsAPI::class.java)
        val call = request.createApplicant(firstName, lastName)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                _responseLiveData.postValue(response)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // You can use another LiveData to handle errors or extend your current LiveData to handle errors
            }
        })
    }
}

package eu.tutorials.sesavannah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplicantsViewModel : ViewModel() {

    private val _adminDataLiveData: MutableLiveData<List<AdminData>> = MutableLiveData()
    val adminDataLiveData: LiveData<List<AdminData>>
        get() = _adminDataLiveData

    private val _responseLiveData: MutableLiveData<Response<Void>> = MutableLiveData()
    val responseLiveData: LiveData<Response<Void>>
        get() = _responseLiveData

    fun fetchAdminData() {
        val request: ApplicantsAPI = ServiceBuilder.buildService(ApplicantsAPI::class.java)
        val call = request.getAdminData() // Assuming getAdminData returns Call<List<AdminData>> now

        call.enqueue(object : Callback<List<AdminData>> {
            override fun onResponse(call: Call<List<AdminData>>, response: Response<List<AdminData>>) {
                if (response.isSuccessful) {
                    _adminDataLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<AdminData>>, t: Throwable) {
                // Handle error, possibly updating another LiveData with error information
            }
        })
    }

    /*
    fun fetchAdminData() {
    val request: ApplicantsAPI = ServiceBuilder.buildService(ApplicantsAPI::class.java)
    val call = request.getAdminData()

    call.enqueue(object : Callback<List<AdminData>> {
        override fun onResponse(call: Call<List<AdminData>>, response: Response<List<AdminData>>) {
            if (response.isSuccessful) {
                _adminDataLiveData.postValue(response.body())
            }
        }

        override fun onFailure(call: Call<List<AdminData>>, t: Throwable) {
            // Handle error
        }
    })
}

    *
    * */



    fun createApplicant(firstName: String, lastName: String) {
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

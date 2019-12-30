package ayaan.tarikul.kotlinsampleapp.retrofit

import ayaan.tarikul.kotlinsampleapp.model.ReposDataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/users/tarikul1988/repos")
    fun getAllReposInfo(): Call<List<ReposDataModel>>
}
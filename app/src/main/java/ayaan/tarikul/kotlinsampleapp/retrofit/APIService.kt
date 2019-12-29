package ayaan.tarikul.kotlinsampleapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/users/tarikul1988/repos")
    abstract fun getAllReposInfo(): Call<AllReposList>
}
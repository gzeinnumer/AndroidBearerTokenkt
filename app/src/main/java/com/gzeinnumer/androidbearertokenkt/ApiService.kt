package com.gzeinnumer.androidbearertokenkt

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("details")
    fun data() : Call<ResponseData>
}

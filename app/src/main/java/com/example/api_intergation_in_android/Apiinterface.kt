package com.example.api_intergation_in_android

import retrofit2.Call
import retrofit2.http.GET

interface Apiinterface {

    // it is the last name of json after/
    @GET("products")

     fun getProductsData() : Call<MyData>
}
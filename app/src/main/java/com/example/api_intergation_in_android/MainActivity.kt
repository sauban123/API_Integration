package com.example.api_intergation_in_android

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.WindowInsetsAnimation
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class MainActivity : AppCompatActivity() {

   lateinit var recyclerView: RecyclerView
   lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)
        val retrofitData = retrofitBuilder.getProductsData()
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: retrofit2.Response<MyData?>) {
                // if api call successs then use the data of api and show into app
                val responseBody  = response.body()
                 val productList = responseBody?.products!!
                myAdapter = MyAdapter(this@MainActivity,productList)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }
                override fun onFailure(call: Call<MyData?>, t: Throwable) {
                    // if api call fails
                    Log.d("MainActivity", "onFailure: "+ t.message)
                }
            })
       }
}
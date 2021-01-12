package com.ageen.webservice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view);
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ReposAdapter()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(GitHubService::class.java)
        val repos = service.listRepos("avizaleonel")

        repos.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {

                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_LONG).show()
                } else {
                    response.body()?.let { repos ->
                        (recyclerView.adapter as ReposAdapter).setNameList(repos)
                    }
                }
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                repos.cancel()
            }


        })



    }
}



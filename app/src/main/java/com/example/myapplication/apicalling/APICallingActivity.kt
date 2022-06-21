package com.example.myapplication.apicalling

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.apicalling.request.LoginRequest
import com.example.myapplication.databinding.ActivityApicallingBinding
import com.example.myapplication.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APICallingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApicallingBinding
    var userList = ArrayList<UserDetailsList>()
    var responseList = ArrayList<Data>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApicallingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set in adapter list
        val adapterGetList = ApiAdapter(responseList)
        val userListAdapter = UserListAdapter(userList)
        //set in recyclerview adapter list
        binding.apply {
            recyclerViewApi.adapter = adapterGetList
            recyclerViewApiUserList.adapter = userListAdapter
        }

        //access method
        val callForGetList = RetrofitProvider.getInstance().apiService.getList()

        // display data in RecyclerView
        callForGetList.enqueue(object : Callback<GetList> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<GetList>, response: Response<GetList>) {
                Log.d(getString(R.string.response), getString(R.string.responsesuccess))
                Log.d("Data", response.body().toString())
                response.body()?.let { responseList.addAll(it.data) }
                adapterGetList.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<GetList>, t: Throwable) {
                Log.d(getString(R.string.response), getString(R.string.responsefail))
            }

        })

        // GET Method With Path
        val getUserDetails = RetrofitProvider.getInstance().apiService.getUserDetails("1")
        getUserDetails.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.d(getString(R.string.response), getString(R.string.responsesuccess))
                Log.d("getUserDetails", response.body().toString())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d(getString(R.string.response), getString(R.string.responsefail))
            }

        })

        // GET Method With Query
       val callUserList = RetrofitProvider.getInstance().secondApiService.getUserList("2")
        callUserList.enqueue(object : Callback<ListOfUser>{

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ListOfUser>, response: Response<ListOfUser>) {
                Log.d("userlist", getString(R.string.responsesuccess))
                Log.d("getUserList", response.body().toString())
                response.body()?.data?.let { userList.addAll(it) }
                userListAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ListOfUser>, t: Throwable) {
                Log.d("user list", getString(R.string.responsefail))
            }

        })



    }
}
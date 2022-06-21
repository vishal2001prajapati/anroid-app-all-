package com.example.myapplication.apicalling

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.apicalling.request.LoginRequest
import com.example.myapplication.databinding.ActivityLoginBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginApiCall()

    }

    private fun loginApiCall() {

        binding.btnLogin.setOnClickListener {
            val loginReq = LoginRequest(binding.userEmail.text.toString(), binding.userPassword.text.toString())
            RetrofitProvider.getInstance().apiService.loginUser(loginReq).enqueue(object : Callback<LoginApiData> {
                override fun onResponse(call: Call<LoginApiData>, response: Response<LoginApiData>) {
                    Log.d("login", getString(R.string.responsesuccess))
                    if (binding.userEmail.text?.isEmpty() == true && binding.userPassword.text?.isEmpty() == true) {
                        binding.edittextEmail.error = getString(R.string.enter_email_address)
                        binding.edittextPassword.error = getString(R.string.enter_password)
                    } else if (response.code() == 200) {
                        Log.d("login Success", response.body()?.token.toString())
                        Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()
                    } else {

                        val error = response.errorBody()!!.string()
                        val jsonOb = JSONObject(error)
                        Toast.makeText(this@LoginActivity, jsonOb.getString("error"), Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<LoginApiData>, t: Throwable) {
                    Log.d("login", getString(R.string.responsefail))
                }

            })
        }

    }
}
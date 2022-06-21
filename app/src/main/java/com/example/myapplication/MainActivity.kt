package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.apicalling.APICallingActivity
import com.example.myapplication.apicalling.LoginActivity
import com.example.myapplication.coroutine.CoroutineActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragment.DemoOneFragment
import com.example.myapplication.fragment.DemoTwoFragment
import com.example.myapplication.recyclerview.RecyclerViewActivity
import com.example.myapplication.sqlite.SqliteActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val REQUEST_CODE = 200
const val CAMERA_REQUEST_CODE = 123
const val STORAGE_REQUEST_CODE = 113

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(getString(R.string.activitylifecycle), getString(R.string.oncreate))

        binding.btnRecyclerView.setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnApiCalling.setOnClickListener {
            val intent = Intent(this, APICallingActivity::class.java)
            startActivity(intent)
        }

        binding.btnLoginApiCalling.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnFragmentOne.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, DemoOneFragment()).commit()
        }

        binding.btnFragmentTwo.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, DemoTwoFragment()).commit()
        }

        binding.btnCoroutine.setOnClickListener {
            val intent = Intent(this, CoroutineActivity::class.java)
            startActivity(intent)
        }

        // Coroutine with withContext
        GlobalScope.launch {
            executeTask()
        }

        // Coroutine
        CoroutineScope(Dispatchers.IO).launch {
            displayFollowers()
        }


        // this is for multiple permission in single array
//        binding.btnLocationAndSmsPermission.setOnClickListener {
//            if (checkPer()) {
//                Toast.makeText(this, "Permission Already Granted", Toast.LENGTH_SHORT).show()
//            } else {
//                ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, READ_SMS,CAMERA), REQUEST_CODE)
//            }
//        }

        // this is individual permission
        binding.btnCameraPermission.setOnClickListener {
            checkPermission(android.Manifest.permission.CAMERA, CAMERA_REQUEST_CODE)
        }

        binding.btnStoragePermission.setOnClickListener {
            checkPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE, CAMERA_REQUEST_CODE)
        }

        binding.btnSqlite.setOnClickListener {
            val intent = Intent(this, SqliteActivity::class.java)
            startActivity(intent)
        }

    }

    private suspend fun executeTask() {
        Log.d("withContext", "Before")

        //    GlobalScope.launch {
        //          executeTask()
        //    }
        // line by line call
        withContext(Dispatchers.IO) {
            delay(1000)
            Log.d("withContext", "Inside")
        }
        Log.d("withContext", "After")

    }


    private suspend fun displayFollowers() {
        CoroutineScope(Dispatchers.IO).launch {
            val fb = async { getFbFollowers() }
            val insta = async { getInstaFollowers() }
            Log.d("getData", "Facebook:- ${fb.await()} Instagram:- ${insta.await()}")
        }
    }

    private suspend fun getFbFollowers(): Int {
        delay(1000)
        return 50
    }

    private suspend fun getInstaFollowers(): Int {
        delay(1000)
        return 120
    }

    // multiple permission
//        private fun checkPer(): Boolean {
//        val resultLocation: Int = ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
//        val resultMsgRead: Int = ActivityCompat.checkSelfPermission(this, READ_SMS)
//        val resultCamera: Int = ActivityCompat.checkSelfPermission(this, CAMERA)
//        return resultLocation == PackageManager.PERMISSION_GRANTED && resultMsgRead == PackageManager.PERMISSION_GRANTED && resultCamera == PackageManager.PERMISSION_GRANTED
//    }

    // single permission
    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Already Granted", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)))

            }
        } else if (requestCode == STORAGE_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()

            }
        }

        // multiple permission
//                val loc: Int = grantResults[0]
//                val msg: Int = grantResults[1]
//                val camera: Int = grantResults[2]
//
//                val checkLocation: Boolean = loc == PackageManager.PERMISSION_GRANTED
//                val checkMsg: Boolean = msg == PackageManager.PERMISSION_GRANTED
//                val checkCamera: Boolean = camera == PackageManager.PERMISSION_GRANTED

//                if (checkLocation && checkMsg && checkCamera) {
//                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)))
//                }
    }

    override fun onStart() {
        super.onStart()
        Log.d(getString(R.string.activitylifecycle), getString(R.string.onstart))
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(getString(R.string.activitylifecycle), getString(R.string.onrestart))
    }


    override fun onPause() {
        super.onPause()
        Log.d(getString(R.string.activitylifecycle), getString(R.string.onpause))
    }

    override fun onResume() {
        super.onResume()
        Log.d(getString(R.string.activitylifecycle), getString(R.string.onresume))
    }

    override fun onStop() {
        super.onStop()
        Log.d(getString(R.string.activitylifecycle), getString(R.string.onstop))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(getString(R.string.activitylifecycle), getString(R.string.ondestroy))
    }

}
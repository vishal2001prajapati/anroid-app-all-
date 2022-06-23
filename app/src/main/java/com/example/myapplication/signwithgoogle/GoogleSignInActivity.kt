package com.example.myapplication.signwithgoogle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityGoogleSignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class GoogleSignInActivity : AppCompatActivity() {

    lateinit var binding: ActivityGoogleSignInBinding
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SignInWithGoogle()

    }

    private fun SignInWithGoogle() {
        val googleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("your token").requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        // button click
        binding.btnGoogleSignIn.setOnClickListener {
            Log.d("signIn", "onCreate: begin Google SignIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, 100)

        }

    }

    private fun checkUser() {
        // check user login or not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null ) {
            // already login
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            Log.d("signIn", "onActivityResult: Google SignIn Intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)

            } catch (e: Exception) {
                Log.d("signIn", "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d("signIn", "firebaseAuthWithGoogleAccount: Firebase auth with Google account")
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnSuccessListener { authResult ->
                Log.d("signIn", "firebaseAuthWithGoogleAccount: Login")
                // get login user
                val firebaseUser = firebaseAuth.currentUser
                //get userInfo
                val id = firebaseUser?.uid
                val email = firebaseUser?.email
                Log.d("signIn", "firebaseAuthWithGoogleAccount: uid:$id")
                Log.d("signIn", "firebaseAuthWithGoogleAccount: email:$email")
                // check user new or existing
                if (authResult.additionalUserInfo?.isNewUser == true) {
                    // new user then create account
                    Log.d("signIn", "firebaseAuthWithGoogleAccount: email created:$email")
                    Toast.makeText(this, "email created:$email", Toast.LENGTH_SHORT).show()
                } else {
                    // existing user
                    Log.d("signIn", "firebaseAuthWithGoogleAccount: existing:$email")
                    Toast.makeText(this, "login..:$email", Toast.LENGTH_SHORT).show()
                }

                //  start profile activity
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()

            }.addOnFailureListener { e ->
                Log.d("signIn", "firebaseAuthWithGoogleAccount: Login failed :${e.message}")
                Toast.makeText(this, " Login failed :${e.message}", Toast.LENGTH_SHORT).show()
            }

    }
}
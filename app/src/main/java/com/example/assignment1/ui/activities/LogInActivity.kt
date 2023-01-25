package com.example.assignment1.ui.activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.assignment1.R
import com.example.assignment1.databinding.ActivityLogInBinding
import com.example.assignment1.ui.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LogInActivity : AppCompatActivity() {

    private var auth = FirebaseAuth.getInstance()
    lateinit var googleSignInClient : GoogleSignInClient
    private lateinit var mProgressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        val binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        createFirebaseRequest()

        binding.fabLogInGoogle.setOnClickListener{
            showProgressBar()
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        val signInIntent =googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val data =  it.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithAccount(account)
            }catch (e : ApiException){
                hideProgressBar()
                e.printStackTrace()
            }
        }else{
            hideProgressBar()
            showErrorSnackBar("Error occurred")
            return@registerForActivityResult
        }
    }

    private fun firebaseAuthWithAccount(account: GoogleSignInAccount?) {
        val credential =  GoogleAuthProvider.getCredential(account!!.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    updateUi(auth.currentUser)
                }else{
                    showErrorSnackBar("Error occurred while signing")
                    hideProgressBar()
                }
            }
    }

    private fun updateUi(currentUser: FirebaseUser?) {
        if (currentUser == null){
            hideProgressBar()
            return
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

    private fun createFirebaseRequest() {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, options)
    }

    companion object{
        const val SIGN_IN_REQUEST = 1
    }

    private fun showProgressBar(){
        mProgressDialog = Dialog(this)
        mProgressDialog.setContentView(R.layout.progress_dialog)

        /// To make bg transparent
        mProgressDialog.window!!.setBackgroundDrawable(getDrawable(android.R.color.transparent))

        mProgressDialog.setCancelable(false)
        mProgressDialog.show()

    }

    private fun hideProgressBar(){
        mProgressDialog.dismiss()
    }

    private fun showErrorSnackBar(message:String){
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.reddish_brown))
        snackBar.show()
    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}
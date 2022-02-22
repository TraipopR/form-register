package com.example.formregister

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.formregister.databinding.ActivityMemberBinding
import com.google.firebase.auth.FirebaseAuth

class MemberActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemberBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val userData = auth.currentUser
        binding.txtOutput.text = userData?.uid.toString() + " " + userData!!.email

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logout Complete", Toast.LENGTH_LONG).show()

            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
            finish()
        }
    }
}
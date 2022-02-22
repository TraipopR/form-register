package com.example.formregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.formregister.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            val it = Intent(this, MemberActivity::class.java)
            startActivity(it)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.txtEmail.text.toString().trim()
            val pass = binding.txtPassword.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "กรุณากรอก Email.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                Toast.makeText(this, "กรุณากรอก Password.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (pass.length < 8) {
                        binding.txtPassword.error = "กรอกรหัสผ่านให้มากกว่า 8 ตัวอักษร"
                    } else {
                        Toast.makeText(this, "Login ล้มเหลวเนื่องจาก: " + task.exception!!.message, Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Login Success!", Toast.LENGTH_LONG).show()
                    val it = Intent(this, MemberActivity::class.java)
                    startActivity(it)
                    finish()
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            val it = Intent(this, RegisterActivity::class.java)
            startActivity(it)
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }
}
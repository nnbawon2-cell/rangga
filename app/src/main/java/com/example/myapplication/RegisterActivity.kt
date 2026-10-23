package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val fullName = binding.etFullName.text?.toString()?.trim().orEmpty()
            val email = binding.etEmail.text?.toString()?.trim().orEmpty()
            val password = binding.etPassword.text?.toString()?.trim().orEmpty()
            val confirmPassword = binding.etConfirmPassword.text?.toString()?.trim().orEmpty()

            if (fullName.isEmpty()) {
                binding.tilFullName.error = getString(R.string.err_empty_name)
                return@setOnClickListener
            } else {
                binding.tilFullName.error = null
            }

            if (email.isEmpty()) {
                binding.tilEmail.error = getString(R.string.err_empty_email)
                return@setOnClickListener
            } else {
                binding.tilEmail.error = null
            }

            if (password.isEmpty()) {
                binding.tilPassword.error = getString(R.string.err_empty_password)
                return@setOnClickListener
            } else {
                binding.tilPassword.error = null
            }

            if (password != confirmPassword) {
                binding.tilConfirmPassword.error = getString(R.string.err_password_mismatch)
                return@setOnClickListener
            } else {
                binding.tilConfirmPassword.error = null
            }

            Toast.makeText(this, getString(R.string.msg_register_success), Toast.LENGTH_SHORT).show()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
    }
}

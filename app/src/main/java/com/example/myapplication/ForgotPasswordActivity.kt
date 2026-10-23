package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnResetPassword.setOnClickListener {
            val email = binding.etEmail.text?.toString()?.trim().orEmpty()

            if (email.isEmpty()) {
                binding.tilEmail.error = getString(R.string.err_empty_email)
                return@setOnClickListener
            } else {
                binding.tilEmail.error = null
            }

            Toast.makeText(this, getString(R.string.msg_reset_link_sent), Toast.LENGTH_LONG).show()
            finish()
        }

        binding.tvBackToLogin.setOnClickListener {
            finish()
        }
    }
}

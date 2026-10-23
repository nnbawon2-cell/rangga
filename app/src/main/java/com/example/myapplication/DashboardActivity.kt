package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val logoutAction = {
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }

        binding.btnLogoutTop.setOnClickListener { logoutAction() }
        binding.btnLogout.setOnClickListener { logoutAction() }

        binding.cardMenuProfile.setOnClickListener {
            Toast.makeText(this, "Menu Profil diklik", Toast.LENGTH_SHORT).show()
        }

        binding.cardMenuReports.setOnClickListener {
            Toast.makeText(this, "Menu Laporan diklik", Toast.LENGTH_SHORT).show()
        }

        binding.cardMenuSettings.setOnClickListener {
            Toast.makeText(this, "Menu Pengaturan diklik", Toast.LENGTH_SHORT).show()
        }

        binding.cardStat1.setOnClickListener {
            Toast.makeText(this, "Rincian Saldo diklik", Toast.LENGTH_SHORT).show()
        }

        binding.cardStat2.setOnClickListener {
            Toast.makeText(this, "Rincian Tugas diklik", Toast.LENGTH_SHORT).show()
        }

        binding.cardStat3.setOnClickListener {
            Toast.makeText(this, "Rincian Pemberitahuan diklik", Toast.LENGTH_SHORT).show()
        }

        binding.cardStat4.setOnClickListener {
            Toast.makeText(this, "Rincian Poin diklik", Toast.LENGTH_SHORT).show()
        }
    }
}

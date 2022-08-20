package com.example.sendmessagewidthemailaddress

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.sendmessagewidthemailaddress.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        initViews()
    }

    private fun initViews(){
        binding.btnGo.setOnClickListener {
            var email: String = ""
            var title: String = ""
            var desc: String = ""
            var check = true

            if (binding.editEmail.text.isEmpty()){
                binding.editEmailLayout.error = "Please enter your email"
                check = false
            } else{
                email = binding.editEmail.text.toString().trim()
                binding.editEmailLayout.error = null
            }

            if (binding.editTitle.text.isEmpty()){
                binding.editTitleLayout.error = "Please enter title"
                check = false
            } else{
                title = binding.editTitle.text.toString().trim()
                binding.editTitleLayout.error = null
            }

            if (binding.editDescription.text.isEmpty()){
                binding.editDescriptionLayout.error = "Please enter message"
                check = false
            } else{
                desc = binding.editDescription.text.toString().trim()
                binding.editDescriptionLayout.error = null
            }

            if (check){
                sendMessage(email, title, desc)
            }
        }
    }
    private fun sendMessage(email: String, title: String, desc: String){
        val intent = Intent(Intent.ACTION_SEND).apply {
            data = Uri.parse("mailto:")
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, email)
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, desc)
        }
        try {
            startActivity(Intent.createChooser(intent, "Share"))
        }catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
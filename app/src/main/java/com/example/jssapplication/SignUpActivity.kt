package com.example.jssapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jssapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialize Firebase Auth
        auth = FirebaseAuth.getInstance()


        binding.textView10.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
        binding.SignupButton.setOnClickListener {

            //get the text from field
            val name: String = binding.editTextText3.text.toString()
            val collageid : String = binding.collageid.text.toString()
            val password: String = binding.editTextTextPassword2.text.toString()
            val confirmpassword: String = binding.editTextTextPassword3.text.toString()

            // check the field
            if (collageid.isEmpty() || name.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
                Toast.makeText(this, "Please Fill All The Details", Toast.LENGTH_SHORT).show()
            } else if (password != confirmpassword) {
                Toast.makeText(this, "Password Must be Same", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(collageid, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Registation Successful : ", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LogInActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Registation Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}
package com.example.jssapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jssapplication.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    private val binding:ActivityLogInBinding by lazy {
        ActivityLogInBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialize Firebase Auth
        auth= FirebaseAuth.getInstance()


        binding.button2.setOnClickListener{
            val collageid:String = binding.collageId.text.toString()
            val password:String = binding.editTextTextPassword.text.toString()

            if (collageid.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please fill all the details", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(collageid , password)
                    .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Sign-In Sucessful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this,"Sign-In Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        binding.textView7.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
       finish()
        }

    }
}
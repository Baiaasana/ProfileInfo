package com.example.profileinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.example.profileinfo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSave.setOnClickListener {

            val email = binding.edtEmail.text.toString()
            val firstName = binding.edtFirstName.text.toString()
            val lastName = binding.edtLastName.text.toString()
            val username = binding.edtUsername.text.toString()
            val age = binding.edtAge.text.toString()
            checks(email, firstName, lastName, username, age)
        }

        binding.btnClear.setOnLongClickListener{

            clear(binding.edtEmail, binding.edtFirstName, binding.edtLastName, binding.edtUsername, binding.edtAge)
            Toast.makeText(this, "All fields are empty!", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun checks(email: String, firstName: String, lastName: String, username: String, age: String){

        if(email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || age.isEmpty()){
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        } else if(username.length < 10) {
            Toast.makeText(this, "Username must be more than 9 characters!", Toast.LENGTH_SHORT).show()
        } else if(!isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email!", Toast.LENGTH_SHORT).show()
        } else if (!checkIfNumberIsInteger(age) || age.toInt() < 0) {
            Toast.makeText(this, "Please enter a positive integer in the age field", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Your info has been successfully updated!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clear(email: EditText, firstName: EditText, lastName: EditText, username: EditText, age: EditText){

        email.text.clear()
        firstName.text.clear()
        lastName.text.clear()
        username.text.clear()
        age.text.clear()
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun checkIfNumberIsInteger(number: String): Boolean {
        try {
            number.toInt()
            return true
        } catch (e: NumberFormatException) {}

        try {
            number.toFloat()
            return false
        } catch (e: NumberFormatException) {}
        return false
    }


}
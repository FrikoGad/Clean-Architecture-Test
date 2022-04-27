package com.example.test1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test1.R
import com.example.test1.data.repository.UserRepositoryImpl
import com.example.test1.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.test1.domain.models.SaveUserNameParam
import com.example.test1.domain.models.UserName
import com.example.test1.domain.usecase.GetUserNameUseCase
import com.example.test1.domain.usecase.SaveUserNameUseCase

class MainActivity : AppCompatActivity() {
    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = applicationContext))
    }
    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(userRepository = userRepository)
    }
    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(userRepository = userRepository)
    }

    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)

        val dataTextView = findViewById<TextView>(R.id.tv_data)
        val dataEditView = findViewById<EditText>(R.id.et_data)
        val sendButton = findViewById<Button>(R.id.btn_send)
        val receiveButton = findViewById<Button>(R.id.btn_receive)

        vm.resultLive.observe(this) { text ->
            dataTextView.text = text
        }

        sendButton.setOnClickListener {
            val text = dataEditView.text.toString()
            vm.save(text)
        }

        receiveButton.setOnClickListener {
            vm.load()
        }
    }
}
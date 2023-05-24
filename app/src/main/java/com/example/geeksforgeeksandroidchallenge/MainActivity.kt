package com.example.geeksforgeeksandroidchallenge

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.geeksforgeeksandroidchallenge.Data.News
import com.example.geeksforgeeksandroidchallenge.Network.NewsService
import com.example.geeksforgeeksandroidchallenge.ViewModel.NewsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.net.NetworkInterface

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel = ViewModelP

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.newsList.observe(this) {
            if (it != null) {
                Log.i("response", it.toTypedArray().contentToString())
            }
        }
    }
}
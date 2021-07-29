package com.aya.walmarttask.ui.main.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aya.walmarttask.data.api.ApiHelper
import com.aya.walmarttask.data.api.RetrofitBuilder
import com.aya.walmarttask.data.model.Anime
import com.aya.walmarttask.data.room.DatabaseBuilder
import com.aya.walmarttask.data.room.DatabaseHelperImpl
import com.aya.walmarttask.databinding.ActivityMainBinding
import com.aya.walmarttask.ui.base.ViewModelFactory
import com.aya.walmarttask.ui.main.adapter.MainAdapter
import com.aya.walmarttask.ui.main.viewmodel.MainViewModel
import com.aya.walmarttask.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService),
                DatabaseHelperImpl(
                    DatabaseBuilder.getInstance(this)
                )
            )
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.adapter = adapter
        binding.search.setOnClickListener {

            showDialog()
        }
    }




    // setup observers to get data from api request than set in recyclerView
    private fun setupObservers() {
        viewModel.getAnimes().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { animes -> retrieveList(animes) }
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }


    //Search using api request
    private fun search(query: String) {
        viewModel.searchAnime(query).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { animes -> retrieveList(animes.results) }
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }


    //update data in recyclerView using adapter
    private fun retrieveList(anime: List<Anime>) {
        adapter.apply {
            addAnimes(anime)
            notifyDataSetChanged()
        }
    }


    //Setup dialog box
    private fun showDialog() {
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Search")

        // Set up the input
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
            // Here you get get input text from the Edittext
            input.setHint("Search by Character...")
            val text = input.text.toString()
            if (text.isNotEmpty())
                search(input.text.toString())

        })
        builder.setNegativeButton("Cancel",null)

        builder.show()
    }

}
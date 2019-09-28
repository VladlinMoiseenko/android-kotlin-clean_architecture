package ru.vladlin.kotlinclean.news

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.articles.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.vladlin.kotlinclean.R
import ru.vladlin.kotlinclean.entities.Status

class NewsActivity : AppCompatActivity() {

    private val newsList: NewsViewModelBase by viewModel()
    private lateinit var listAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.articles)
        listAdapter = NewsListAdapter()

        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = listAdapter
        newsList.fetchNews()
    }

    override fun onStart() {
        super.onStart()
        newsList.getNewsLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    // Error handling
                }
                Status.LOADING -> {
                    // Progress
                }
                Status.SUCCESSFUL -> {
                    // On Successful response
                }
            }
            it?.data?.let { response ->
                listAdapter.updateList(response.articles)
            }
        })
    }
}
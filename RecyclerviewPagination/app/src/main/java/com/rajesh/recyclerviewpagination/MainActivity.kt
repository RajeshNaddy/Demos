package com.rajesh.recyclerviewpagination

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.widget.ProgressBar
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import com.rajesh.recyclerviewpagination.pagination.PaginationScrollListener
import android.view.View

/**
 * Created by Rajesh on 25/06/2019
 **/

class MainActivity : AppCompatActivity() {

    lateinit var adapter: DataAdapter
    var linearLayoutManager: LinearLayoutManager? = null
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    private val PAGE_START = 0

    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private var Loading = false
    // If current page is the last page (Pagination will stop after this page load)
    private var LastPage = false

    // total no. of pages to load. Initial load is page 0, after which 2 more pages will load.
    private val TOTAL_PAGES = 3
    // indicates the current page which Pagination is fetching.
    private var currentPage = PAGE_START


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        setupRecyclerView() // setup Recyclerview
        loadFirstPage() // Load First Page
    }

    private fun setupRecyclerView() {

        adapter = DataAdapter(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        linearLayoutManager = LinearLayoutManager(this) // LinearLayoutManager
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                Loading = true
                currentPage += 1

                //load after 2 seconds
                Handler().postDelayed({loadNextPage()},2000)

            }

            override fun getTotalPageCount(): Int {
                return TOTAL_PAGES
            }

            override fun isLastPage(): Boolean {
                return LastPage
            }

            override fun isLoading(): Boolean {
                return Loading
            }

        })
    }

    private fun loadFirstPage() {

        // fetching dummy datas
        val datas = Data.createDummyDatas(adapter.itemCount)
        progressBar.visibility = View.GONE
        adapter.addAll(datas) //add all datas to list
        adapter.notifyDataSetChanged() //notify adapter

        if (currentPage <= TOTAL_PAGES)
            adapter.addLoadingFooter()
        else
            LastPage = true
    }

    private fun loadNextPage() {
        Loading = false

        val datas = Data.createDummyDatas(adapter.itemCount)
        adapter.removeLoadingFooter()

        adapter.addAll(datas)
        if (currentPage != TOTAL_PAGES)
            adapter.addLoadingFooter()
        else
            LastPage = true
    }
}

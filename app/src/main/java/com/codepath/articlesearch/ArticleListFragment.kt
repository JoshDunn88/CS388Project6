package com.codepath.articlesearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import android.app.Application
import com.codepath.asynchttpclient.AsyncHttpClient
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity


private const val TAG = "ArticleListFragment"
class ArticleListFragment : Fragment() {
    private val articles = mutableListOf<DisplayArticle>()
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_article_list, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        articlesRecyclerView = view.findViewById(R.id.article_recycler_view)
        articlesRecyclerView.layoutManager = layoutManager
        articlesRecyclerView.setHasFixedSize(true)
        articleAdapter = ArticleAdapter(view.context, articles)
        articlesRecyclerView.adapter = articleAdapter

        // Update the return statement to return the inflated view from above
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchArticles()
    }

    fun fetchArticles() {

        lifecycleScope.launch {
            (activity?.application as ArticleApplication).db.articleDao().getAll()
                .collect { databaseList ->
                    databaseList.map { entity ->
                        DisplayArticle(
                            entity.headline,
                            entity.byline
                        )
                    }.also { mappedList ->
                        articles.clear()
                        articles.addAll(mappedList)
                        articleAdapter.notifyDataSetChanged()
                    }
                }
        }

    }


    companion object {
        fun newInstance(): ArticleListFragment {
            return ArticleListFragment()
        }
    }
}
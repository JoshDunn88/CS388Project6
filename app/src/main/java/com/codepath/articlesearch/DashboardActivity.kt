package com.codepath.articlesearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "DetailActivity"

class DashboardActivity: AppCompatActivity() {
    lateinit var averageCaloriesDisplay: TextView
    lateinit var minCaloriesDisplay: TextView
    lateinit var maxCaloriesDisplay: TextView
    lateinit var dashRecordButton: Button
    lateinit var clearButt: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        averageCaloriesDisplay = findViewById(R.id.avgCalView)
        minCaloriesDisplay = findViewById(R.id.minCalView)
        maxCaloriesDisplay = findViewById(R.id.maxCalView)
        dashRecordButton = findViewById(R.id.addFromDashButton)
        clearButt = findViewById(R.id.clearButton)

        clearButt.setOnClickListener{let{
            lifecycleScope.launch(IO) {
                (application as ArticleApplication).db.articleDao().deleteAll()
            }}
        }
    }
}
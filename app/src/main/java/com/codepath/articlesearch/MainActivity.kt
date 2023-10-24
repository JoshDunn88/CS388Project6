package com.codepath.articlesearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity/"


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var makeEntryButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val dashboardFragment: Fragment = DashFragment()
        val articleListFragment: Fragment = ArticleListFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNav)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_log -> fragment = articleListFragment
                R.id.nav_dash -> fragment = dashboardFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_log

        supportActionBar?.title="Nutrition Tracker"
        makeEntryButton = findViewById(R.id.goToRecord)
        makeEntryButton.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(intent)
        }
        }
    private fun replaceFragment(newFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, newFragment)
        fragmentTransaction.commit()
    }
    }

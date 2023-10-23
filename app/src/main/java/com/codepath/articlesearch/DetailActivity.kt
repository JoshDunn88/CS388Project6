package com.codepath.articlesearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
     lateinit var nameInputField: EditText
     lateinit var calorieInputField: EditText
     lateinit var recordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        nameInputField = findViewById(R.id.nameField)
        calorieInputField = findViewById(R.id.calorieField)
        recordButton = findViewById(R.id.addFoodButton)

        recordButton.setOnClickListener{let{
            lifecycleScope.launch(IO) {
                (application as ArticleApplication).db.articleDao().insert(
                    ArticleEntity(
                        headline = nameInputField.text.toString(),
                        byline = calorieInputField.text.toString()
                    )
                )
            }}
            val i = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(i)
        }
    }

}
package com.codepath.articlesearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DashFragment : Fragment() {
    lateinit var averageCaloriesDisplay: TextView
    lateinit var minCaloriesDisplay: TextView
    lateinit var maxCaloriesDisplay: TextView
    lateinit var clearButt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_dash, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the data shit method within onViewCreated
        averageCaloriesDisplay = view.findViewById(R.id.avgCalView)
        minCaloriesDisplay = view.findViewById(R.id.minCalView)
        maxCaloriesDisplay = view.findViewById(R.id.maxCalView)
        clearButt = view.findViewById(R.id.clearButton)



        clearButt.setOnClickListener{let{
            lifecycleScope.launch(Dispatchers.IO) {
                (activity?.application as ArticleApplication).db.articleDao().deleteAll()
            }}
                maxCaloriesDisplay.text="Total Calories: 0"
                minCaloriesDisplay.text="Total Meals: 0"
                averageCaloriesDisplay.text="Average Calories/Meal: 0"
        }
        var totalMeals=0
        var totalCalories=0
        //var avgCalories=0

        let{
            lifecycleScope.launch(IO) {

                totalMeals=(activity?.application as ArticleApplication).db.articleDao().getEntryCount()
                maxCaloriesDisplay.text="Total Meals: " + totalMeals
               if (totalMeals>0){
                    totalCalories=(activity?.application as ArticleApplication).db.articleDao().getCalorieSum()
                   averageCaloriesDisplay.text="Average Calories/Meal: " + (totalCalories/totalMeals)
                }
                minCaloriesDisplay.text="Total Calories Consumed: " + totalCalories
            }}


    }

    companion object {
        fun newInstance(): DashFragment {
            return DashFragment()
        }
    }
}
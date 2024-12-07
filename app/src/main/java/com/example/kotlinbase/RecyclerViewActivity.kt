package com.example.kotlinbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.kotlinbase.adapter.FruitRecyclerAdapter
import com.example.kotlinbase.entity.Fruit
import java.lang.StringBuilder

class RecyclerViewActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        var recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        initFruits()
        //val layoutManager = LinearLayoutManager(this)
        val layoutManager = StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL)
        //layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        val adapter = FruitRecyclerAdapter(fruitList)
        recyclerView.adapter = adapter

    }

    private fun initFruits(){
        repeat(10){
            fruitList.add(Fruit(getRandomLengthString("Apple"),R.drawable.apple_pic))
            fruitList.add(Fruit(getRandomLengthString("Banana"),R.drawable.banana_pic))
            fruitList.add(Fruit(getRandomLengthString("Orange"),R.drawable.orange_pic))
            fruitList.add(Fruit(getRandomLengthString("Watermelon"),R.drawable.watermelon_pic))
            fruitList.add(Fruit(getRandomLengthString("Pear"),R.drawable.pear_pic))
            fruitList.add(Fruit(getRandomLengthString("Grape"),R.drawable.grape_pic))
            fruitList.add(Fruit(getRandomLengthString("Pinapple"),R.drawable.pineapple_pic))
            fruitList.add(Fruit(getRandomLengthString("Strawberry"),R.drawable.strawberry_pic))
            fruitList.add(Fruit(getRandomLengthString("Cherry"),R.drawable.cherry_pic))
            fruitList.add(Fruit(getRandomLengthString("Mango"),R.drawable.mango_pic))
        }
    }

    fun getRandomLengthString(str: String): String{
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n){
            builder.append(str)
        }
        return builder.toString()
    }

}
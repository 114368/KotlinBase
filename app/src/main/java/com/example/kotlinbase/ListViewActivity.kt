package com.example.kotlinbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.kotlinbase.adapter.FruitAdapter
import com.example.kotlinbase.entity.Fruit

class ListViewActivity : AppCompatActivity() {

    private val data = listOf("Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple"
    ,"Strawberry","Cherry","Mango","Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple"
        ,"Strawberry","Cherry","Mango")

    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        initFruits()
        val listView = findViewById<ListView>(R.id.listView)
        //val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        val adapter = FruitAdapter(this,R.layout.fruit_item,fruitList)
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val fruit = fruitList[i]
            Toast.makeText(this,fruit.name,Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits(){
        repeat(10){
            fruitList.add(Fruit("Apple",R.drawable.apple_pic))
            fruitList.add(Fruit("Banana",R.drawable.banana_pic))
            fruitList.add(Fruit("Orange",R.drawable.orange_pic))
            fruitList.add(Fruit("Watermelon",R.drawable.watermelon_pic))
            fruitList.add(Fruit("Pear",R.drawable.pear_pic))
            fruitList.add(Fruit("Grape",R.drawable.grape_pic))
            fruitList.add(Fruit("Pinapple",R.drawable.pineapple_pic))
            fruitList.add(Fruit("Strawberry",R.drawable.strawberry_pic))
            fruitList.add(Fruit("Cherry",R.drawable.cherry_pic))
            fruitList.add(Fruit("Mango",R.drawable.mango_pic))
        }
    }
}
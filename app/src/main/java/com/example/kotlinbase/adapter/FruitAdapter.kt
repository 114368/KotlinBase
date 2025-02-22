package com.example.kotlinbase.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlinbase.R
import com.example.kotlinbase.entity.Fruit

class FruitAdapter(activity: Activity, val resourceId: Int, data: List<Fruit>) :
    ArrayAdapter<Fruit>(activity,resourceId,data){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(resourceId,parent,false)
            val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
            val fruitName: TextView = view.findViewById(R.id.fruitName)
            viewHolder = ViewHolder(fruitImage,fruitName)
            view.tag = viewHolder
        }else{
            view =convertView
            viewHolder = view.tag as ViewHolder
        }

        val fruit = getItem(position) //获取当前项的Fruit实例
        if (fruit != null){
            viewHolder.fruitImage.setImageResource(fruit.imageId)
            viewHolder.fruitName.setText(fruit.name)
        }
        return view
    }

    inner class ViewHolder(val fruitImage: ImageView,val fruitName: TextView){
    }

}
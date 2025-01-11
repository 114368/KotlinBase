package com.example.kotlinbase

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.kotlinbase.database.MyDatabaseHelper
import java.lang.NullPointerException

class DatabaseActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        val button = findViewById<Button>(R.id.createDatabase)
        val dbHelper = MyDatabaseHelper(this,"BookStore.db",2)
        button.setOnClickListener {
            dbHelper.writableDatabase
        }

        val addData = findViewById<Button>(R.id.addData)
        addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                put("name","The Da Vinci Code")
                put("author","Dan Brown")
                put("pages",454)
                put("price",16.96)
            }
            val values2 = ContentValues().apply {
                put("name","The Lost Symbol")
                put("author","Dan Brown")
                put("pages",510)
                put("price",12.96)
            }
            db.insert("Book",null,values1)
            db.insert("Book",null,values2)
        }

        val updataButton = findViewById<Button>(R.id.updataData)
        updataButton.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price",10.99)
            db.update("Book",values,"name = ?", arrayOf("The Da Vinci Code"))
        }

        val deleteButton = findViewById<Button>(R.id.deleteData)
        deleteButton.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book","pages > ?", arrayOf("500"))
        }

        val queryButton = findViewById<Button>(R.id.queryData)
        queryButton.setOnClickListener {
            val db = dbHelper.writableDatabase
            val cursor = db.query("Book",null,null,null,null,null,null)
            if(cursor.moveToFirst()){
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.i("hejie","name is $name")
                    Log.i("hejie","author is $author")
                    Log.i("hejie","pages is $pages")
                    Log.i("hejie","price is $price")
                }while (cursor.moveToNext())
            }
            cursor.close()

            }


        val replaceButton = findViewById<Button>(R.id.replaceData)
        replaceButton.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction()
            try {
                db.delete("Book",null,null)
                /*if(true){
                    throw NullPointerException()
                }*/
                val values = ContentValues().apply {
                    put("name","game of Thrones")
                    put("author","George Martin")
                    put("pages",720)
                    put("price",20.58)
                }
                db.insert("Book",null,values)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
        }

        /*
        添加数据：
        db.execSQL("insert into Book (name, author, pages, price) values (?, ?, ?, ?)",
        arrayOf("The Da Vinci Code", "Dan Brown", "454", "16.96"))

        db.execSQL("insert into Book (name, author, pages, price) values (?, ?, ?, ?)",
        arrayOf("The Lost Symbol", "Dan Brown", "510", "12.96"))

        更新数据
        db.execSQL("update Book set price = ? where name = ?", arrayOf("10.99", "The Da vincl Code"))

        删除数据
         db.execSQL("delete from Book where pages > ?", arrayOf("500")

        查询数据
        val cursor = db.rawQuery("select * from Book",null)
         */
    }
}
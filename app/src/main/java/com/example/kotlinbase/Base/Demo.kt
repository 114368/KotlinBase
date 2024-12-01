package com.example.kotlinbase.Base

fun main(){
    /*val range = 0..10
    for (i in range){
        println(i)
    }*/

    /*for(i in 0 until 10 step 2){
        println(i)
    }*/

    /*for(i in 10 downTo 1){
        println(i)
    }*/
    /*val list = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val newList = list.map { it.uppercase() }
    for ( fruit in newList){
        println(fruit)
    }*/
    /*val list = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val newList = list.filter { it.length <= 5 }
    for ( fruit in newList){
        println(fruit)
    }*/
    /*val list = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val anyResult = list.any { it.length<= 5 }
    val allResult = list.all { it.length <= 5 }
    println(" anyResult:"+anyResult + " allResult:"+allResult)*/
    /*Thread(Runnable {

    }).start()*/
    /*Thread{

    }.start()*/
    printParams(12,str2 =  "sss")
}

fun printParams(num: Int = 10, str: String = "aaa", str2: String){
    println("num= $num, str= $str, str2 = $str2")
}

class Demo {


}
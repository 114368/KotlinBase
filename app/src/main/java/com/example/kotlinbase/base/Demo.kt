package com.example.kotlinbase.base

fun main(args: Array<String>){
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
    //printParams(12,str2 =  "sss")

    /*val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val result = with(StringBuilder()) {
        append("Start eat fruits.\n")
        for(fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        toString()
    }
    println(result)*/
    /*val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val result = StringBuilder().run{
        append("Start eat fruits.\n")
        for(fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        toString()
    }*/
    //println(10.0f/3)
    /*val numbers = intArrayOf(1, 2, 3)
    val oceans = listOf("Atlantic", "Pacific")
    val oddList = listOf(numbers, oceans, "salmon")*/
    //println("hello ${args[0]}")
    val a = arrayOf(1, 2, 3)
    val list = asList(-1, 0, *a, 4)
    println(list.toList())
}

fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

fun printParams(num: Int = 10, str: String = "aaa", str2: String){
    println("num= $num, str= $str, str2 = $str2")
}


class Demo {


}
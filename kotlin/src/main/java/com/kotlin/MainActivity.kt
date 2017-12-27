package com.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        tv_hello.text = "设置数据";

//        tv_hello.text = sum(3, 4);
//        sum()

//        vars(1,2,3,4,5)

//        val sumLambda : (Int, Int) -> Int = {x,y -> x+y}
//        Log.e("lambda(匿名函数)",sumLambda(1,2).toString())

//        var a = 1;
//        val s1 = "a is $a"
//        Log.e("字符串模板",s1)
//        a = 2
//        val s2 = "${s1.replace("is", "was")}, but now is $a"
//        Log.e("字符串模板",s2)

//        isNull(null)
//        isNull("333")

//        var args : Array<String> = arrayOf("2","3")
//        canNull(args)

//        getStringLength(2.14)

//        val x: IntArray = intArrayOf(1, 2, 3)
//        Log.e("=====",(x[1] + x[2]).toString())

//        dd()

        xh()
    }

    fun sum(a : Int, b : Int): String {
        return (a + b).toString()
    }

    fun sum(): Unit {
        print(3 + 4)
    }

    fun vars(vararg v : Int) {
        for (vt in v) {
            Log.e("可变长参数函数",vt.toString())
        }
    }

    fun isNull(age : String?) { //类型后面加?表示可为空
//        var agee : String = age; // 报错，要申明agee是否能为空，或者加判断
//       var  agee : String = age!! // 字段后加!!像Java一样抛出空异常
//        var agee = age?.toInt() // 不做处理返回 null
//        var agee = age?.toInt() ?: -1 //age为空返回-1
        var agee = age ?: -1
        Log.e("NULL检查机制",agee.toString())
    }

    /**
      *  当 str 中的字符串内容不是一个整数时, 返回 null:
     * */
    fun parseInt(str: String): Int? {
        // ...
        return str.toInt()
    }

    /**
     * 返回值可为 null 的函数
     * */
    fun canNull(args: Array<String>) {
        if (args.size < 2) {
            print("Two integers expected")
            return
        }
        val x = parseInt(args[0])
        val y = parseInt(args[1])
        // 直接使用 `x * y` 会导致错误, 因为它们可能为 null.
        if (x != null && y != null) {
            // 在进行过 null 值检查之后, x 和 y 的类型会被自动转换为非 null 变量
            print(x * y)
        }
    }

    /**
     * 类型检测及自动类型转换
     * */
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            Log.e("字符串",obj.length.toString())
            // 做过类型判断以后，obj会被系统自动转换为String类型
            return obj.length
        } else if(obj is Int){
            Log.e("数字",obj.toString())
            return obj
        } else if(obj is Double){
            Log.e("Double类型",obj.toString())
            return obj.toInt()
        }

        //在这里还有一种方法，与Java中instanceof不同，使用!is
        // if (obj !is String){
        //   // XXX
        // }

        // 这里的obj仍然是Any类型的引用
        return null
    }

    /**
     * 区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
     * */
    fun toTo(args: Array<String>) {
        print("循环输出：")
        for (i in 1..4) print(i) // 输出“1234”
        println("\n----------------")
        print("设置步长：")
        for (i in 1..4 step 2) print(i) // 输出“13”
        println("\n----------------")
        print("使用 downTo：")
        for (i in 4 downTo 1 step 2) print(i) // 输出“42”
        println("\n----------------")
        print("使用 until：")
        // 使用 until 函数排除结束元素
        for (i in 1 until 4) {   // i in [1, 4) 排除了 4
            print(i)
        }
        println("\n----------------")
    }

    /**
     * 迭代
     * for (item in collection) print(item)
     * */
    fun dd() {
        val items = listOf("apple", "banana", "kiwi")
        for (item in items) {
            Log.e("迭代",item)
        }

        for (index in items.indices) {
//            println("item at $index is ${items[index]}")
            Log.e("迭代","item at $index is ${items[index]}")
        }
    }

    /**
     * 循环控制
     * */
    fun xh() {
//        println("----while 使用-----")
        var x = 5
        while (x > 0) {
//            println( x--)
            Log.e("循环X",x--.toString())
        }
//        println("----do...while 使用-----")
        var y = 5
        do {
            Log.e("循环Y",y--.toString())
        } while(y>0)
    }
}

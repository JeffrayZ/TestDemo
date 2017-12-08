package com.kotlin.bean

/***
 * 描述信息：
 *
 * ${user}
 *
 * @author Jeffray
 *
 * @created ${date} ${time}
 ***/
class Product(price:Double, id:Long) {
    init {
        System.out.print("这是初始化的内容")
    }
}
package com.example.pk2app

class Item{
    private var id:Int =0
    private var name: String = ""
    private var price: String = ""
    private var description:String = ""

    constructor(id:Int, name:String, price:String, description:String) {
        this.id = id
        this.name = name
        this.price= price
        this.description = description
    }

    fun getId():Int{
        return id
    }

    fun getName():String{
        return name
    }

    fun getPrice():String{
        return price
    }

    fun getDescription():String{
        return description
    }

    override fun toString(): String {
        return name
    }


}
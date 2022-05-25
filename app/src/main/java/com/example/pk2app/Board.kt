package com.example.pk2app

class Board {
    private var id:Int =0
    private var board: String = ""
    private var customer: String = ""
    private var totalPrice:String = ""

    constructor(id:Int, board:String, customer:String, totalPrice:String) {
        this.id = id
        this.board = board
        this.customer= customer
        this.totalPrice = totalPrice
    }

    fun getId():Int{
        return id
    }

    fun getBoard():String{
        return board
    }

    fun getTotalPrice():String{
        return totalPrice
    }

    fun getCustomer():String{
        return customer
    }

}
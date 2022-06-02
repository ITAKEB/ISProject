package com.example.pk2app

class ItemBoard {
    private var id:Int =0
    private var boardId: Int = 0
    private var itemId: Int = 0
    private var itemTitle: String = ""
    private var itemTotal: Long = 0
    private var itemPrice: Long = 0
    private var quantity: Int = 0

    constructor(id:Int, boardId:Int, itemId:Int, itemTitle:String, itemTotal:Long, itemPrice:Long, quantity:Int) {
        this.id = id
        this.boardId = boardId
        this.itemId = itemId
        this.itemTitle = itemTitle
        this.itemTotal = itemTotal
        this.itemPrice = itemPrice
        this.quantity = quantity
    }

    fun getId():Int{
        return id
    }

    fun getBoardId():Int{
        return boardId
    }

    fun getItemTitle():String{
        return itemTitle
    }

    fun getItemTotal():Long{
        return itemTotal
    }

    fun getItemPrice():Long{
        return itemPrice
    }

    fun getQuantity():Int{
        return quantity
    }

    fun getItemId():Int{
        return itemId
    }



}
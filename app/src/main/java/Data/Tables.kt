package Data

import com.example.pk2app.Board
import com.example.pk2app.Item

class Tables
{
    abstract class Items{
        companion object{
            val ID = "id"
            val TABLE_NAME= "items"
            val COLUMN_NAME = "name"
            val COLUMN_PRICE = "price"
            val COLUMN_DESCRIPTION = "description"
            val items: MutableList<Item> = ArrayList()
        }
    }

    abstract class Board{
        companion object{
            val ID = "id"
            val TABLE_NAME= "boards"
            val COLUMN_BOARD = "board"
            val COLUMN_CUSTOMER = "customer"
            val COLUMN_TOTAL = "totalPrice"
            val boards: MutableList<com.example.pk2app.Board> = ArrayList()
        }
    }
}
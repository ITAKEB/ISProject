package Data

import com.example.pk2app.Board
import com.example.pk2app.Item
import com.example.pk2app.ItemBoard

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

    abstract class Boards{
        companion object{
            val ID = "id"
            val TABLE_NAME= "boards"
            val COLUMN_BOARD = "board"
            val COLUMN_CUSTOMER = "customer"
            val COLUMN_TOTAL = "totalPrice"
            val boards: MutableList<Board> = ArrayList()
        }
    }

    /*
    * constructor(id:Int, boardId:Int, itemTitle:String, itemTotal:Int, itemPrice:Int, quantity:Int) {
    * */

    abstract class ItemsBoard{
        companion object{
            val ID = "id"
            val TABLE_NAME = "items_board"
            val COLUMN_BOARDID = "board_id"
            val COLUMN_ITEM_TITLE = "item_title"
            val COLUMN_ITEM_TOTAL = "item_total"
            val COLUMN_ITEM_PRICE = "item_price"
            val COLUMN_QUANTITY = "quantity"
            val itemsBoard: MutableList<ItemBoard> = ArrayList()
        }
    }
}
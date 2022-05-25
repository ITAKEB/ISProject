package Data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.pk2app.Board
import com.example.pk2app.Item
import com.example.pk2app.ItemBoard
import com.example.pk2app.Items

class DataDbHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val db: SQLiteDatabase
    private val values: ContentValues

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "items"
    }

    init {
        db = this.writableDatabase
        values = ContentValues()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE " + Tables.Items.TABLE_NAME
                    + " (" + Tables.Items.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Tables.Items.COLUMN_NAME + " TEXT NOT NULL,"
                    + Tables.Items.COLUMN_PRICE + " TEXT NOT NULL,"
                    + Tables.Items.COLUMN_DESCRIPTION + " TEXT NULL)"
        )

        db!!.execSQL(
            "CREATE TABLE " + Tables.Boards.TABLE_NAME
                    + " (" + Tables.Boards.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Tables.Boards.COLUMN_BOARD + " TEXT NOT NULL,"
                    + Tables.Boards.COLUMN_CUSTOMER + " TEXT NOT NULL,"
                    + Tables.Boards.COLUMN_TOTAL + " TEXT NULL)"
        )

        db!!.execSQL(
            "CREATE TABLE " + Tables.ItemsBoard.TABLE_NAME
                    + " (" + Tables.ItemsBoard.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Tables.ItemsBoard.COLUMN_BOARDID + " INTEGER NOT NULL,"
                    + Tables.ItemsBoard.COLUMN_ITEM_TITLE + " TEXT NOT NULL,"
                    + Tables.ItemsBoard.COLUMN_ITEM_TOTAL + " INTEGER NOT NULL,"
                    + Tables.ItemsBoard.COLUMN_ITEM_PRICE + " INTEGER NOT NULL,"
                    + Tables.ItemsBoard.COLUMN_QUANTITY + " INTEGER NOT NULL)"
        )

        db!!.execSQL(
            "CREATE TABLE " + Tables.PayedBoards.TABLE_NAME
                    + " (" + Tables.PayedBoards.ID + " INTEGER PRIMARY KEY,"
                    + Tables.PayedBoards.COLUMN_BOARD + " TEXT NOT NULL,"
                    + Tables.PayedBoards.COLUMN_CUSTOMER + " TEXT NOT NULL,"
                    + Tables.PayedBoards.COLUMN_TOTAL + " TEXT NULL)"
        )


    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertItem(name: String, price: String, description: String) {
        values.put(Tables.Items.COLUMN_NAME, name)
        values.put(Tables.Items.COLUMN_PRICE, price)
        values.put(Tables.Items.COLUMN_DESCRIPTION, description)
        db.insert(Tables.Items.TABLE_NAME, null, values)
    }


    fun getItemData(): MutableList<Item> {
        Tables.Items.items.clear()
        val columnas = arrayOf(
            Tables.Items.ID,
            Tables.Items.COLUMN_NAME,
            Tables.Items.COLUMN_PRICE,
            Tables.Items.COLUMN_DESCRIPTION
        )

        val c = db.query(Tables.Items.TABLE_NAME, columnas, null, null, null, null, null)

        if (c.moveToFirst()) {
            do {
                Tables.Items.items.add(
                    Item(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3)
                    )
                )
            } while (c.moveToNext())
        }

        return Tables.Items.items

    }

    fun insertBoard(board: String, customer: String, total: String) {
        values.put(Tables.Boards.COLUMN_BOARD, board)
        values.put(Tables.Boards.COLUMN_CUSTOMER, customer)
        values.put(Tables.Boards.COLUMN_TOTAL, total)
        db.insert(Tables.Boards.TABLE_NAME, null, values)
    }

    fun getBoardData(): MutableList<Board> {
        Tables.Boards.boards.clear()
        val columnas = arrayOf(
            Tables.Boards.ID,
            Tables.Boards.COLUMN_BOARD,
            Tables.Boards.COLUMN_CUSTOMER,
            Tables.Boards.COLUMN_TOTAL
        )

        val c = db.query(Tables.Boards.TABLE_NAME, columnas, null, null, null, null, null)

        if (c.moveToFirst()) {
            do {
                Tables.Boards.boards.add(
                    Board(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3)
                    )
                )
            } while (c.moveToNext())
        }

        return Tables.Boards.boards

    }

    fun getBoard(id: Int): MutableList<Board>  {
        Tables.Boards.actualBoard.clear()

        val c = db.rawQuery(
            "SELECT * FROM " + Tables.Boards.TABLE_NAME + " WHERE id = ? LIMIT 1",
            arrayOf(id.toString())
        )
        if (c.moveToFirst()) {
            do {
                Tables.Boards.actualBoard.add(
                    Board(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3)
                    )
                )
            } while (c.moveToNext())
        }

        return Tables.Boards.actualBoard


    }

    fun insertPayedBoard(id: Int, board: String, customer: String, total: String) {
        values.put(Tables.PayedBoards.ID, id)
        values.put(Tables.PayedBoards.COLUMN_BOARD, board)
        values.put(Tables.PayedBoards.COLUMN_CUSTOMER, customer)
        values.put(Tables.PayedBoards.COLUMN_TOTAL, total)
        db.insert(Tables.PayedBoards.TABLE_NAME, null, values)
    }

    fun getPayedBoardData(): MutableList<Board> {
        Tables.PayedBoards.boards.clear()
        val columnas = arrayOf(
            Tables.PayedBoards.ID,
            Tables.PayedBoards.COLUMN_BOARD,
            Tables.PayedBoards.COLUMN_CUSTOMER,
            Tables.PayedBoards.COLUMN_TOTAL
        )

        val c = db.query(Tables.PayedBoards.TABLE_NAME, columnas, null, null, null, null, null)

        if (c.moveToFirst()) {
            do {
                Tables.PayedBoards.boards.add(
                    Board(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3)
                    )
                )
            } while (c.moveToNext())
        }

        return Tables.PayedBoards.boards

    }

    fun insertItemBoard(
        boardId: Int,
        itemTitle: String,
        itemTotal: Long,
        itemPrice: Long,
        quantity: Int
    ) {
        values.put(Tables.ItemsBoard.COLUMN_BOARDID, boardId)
        values.put(Tables.ItemsBoard.COLUMN_ITEM_TITLE, itemTitle)
        values.put(Tables.ItemsBoard.COLUMN_ITEM_TOTAL, itemTotal)
        values.put(Tables.ItemsBoard.COLUMN_ITEM_PRICE, itemPrice)
        values.put(Tables.ItemsBoard.COLUMN_QUANTITY, quantity)
        db.insert(Tables.ItemsBoard.TABLE_NAME, null, values)
    }

    fun getItemsBoardData(id:Int): MutableList<ItemBoard> {
        Tables.ItemsBoard.itemsBoard.clear()

        val c = db.rawQuery(
            "SELECT * FROM " + Tables.ItemsBoard.TABLE_NAME + " WHERE board_id = ?",
            arrayOf(id.toString())
        )
        if (c.moveToFirst()) {
            do {
                Tables.ItemsBoard.itemsBoard.add(
                    ItemBoard(
                        c.getInt(0),
                        c.getInt(1),
                        c.getString(2),
                        c.getLong(3),
                        c.getLong(4),
                        c.getInt(5)
                    )
                )
            } while (c.moveToNext())
        }

        return Tables.ItemsBoard.itemsBoard

    }


}
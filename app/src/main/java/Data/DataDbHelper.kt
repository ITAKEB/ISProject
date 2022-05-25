package Data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.pk2app.Item

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
                    + Tables.Items. COLUMN_DESCRIPTION + " TEXT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertItem(name: String, price: String, description: String) {
        values.put(Tables.Items.COLUMN_NAME, name)
        values.put(Tables.Items.COLUMN_PRICE, price)
        values.put(Tables.Items. COLUMN_DESCRIPTION, description)
        db.insert(Tables.Items.TABLE_NAME, null, values)
    }

    fun getData(): MutableList<Item> {
        Tables.Items.items.clear()
        val columnas = arrayOf(
            Tables.Items.ID,
            Tables.Items.COLUMN_NAME,
            Tables.Items.COLUMN_PRICE,
            Tables.Items. COLUMN_DESCRIPTION
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

}
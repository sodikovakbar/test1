package com.oil.bottomnavigationview.db
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.oil.bottomnavigationview.db.mycontacts.DB_NAME
import com.oil.bottomnavigationview.db.mycontacts.ID
import com.oil.bottomnavigationview.db.mycontacts.NAME
import com.oil.bottomnavigationview.db.mycontacts.NUMBER
import com.oil.bottomnavigationview.db.mycontacts.TABLE_NAME
import com.oil.bottomnavigationview.db.mycontacts.VERSION

class Mydbhelper(context: Context)
    :SQLiteOpenHelper(context,DB_NAME,null, VERSION),MyDbInterface {
    override fun onCreate(db: SQLiteDatabase?) {
        val query="create table $TABLE_NAME(id integer not null primary key autoincrement unique,$NAME text not null,$NUMBER text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }

    override fun addContact(myContact: MyContact) {
        val database=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(NAME,myContact.name)
        contentValues.put(NUMBER,myContact.number)
        database.insert(TABLE_NAME,null,contentValues)
        database.close()

    }

    override fun getAllContact(): List<MyContact> {
        val list = ArrayList<MyContact>()
        val query="select*from $TABLE_NAME"
        val database=this.readableDatabase
        var cursor=database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val myContact=MyContact(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(myContact)
            }while (cursor.moveToNext())
        }
        return list
    }
    fun getAllSortName(): ArrayList<MyContact> {
        val list = ArrayList<MyContact>()
        val query="select*from $TABLE_NAME ORDER BY $NAME COLLATE NOCASE ASC"
        val database=this.readableDatabase
        var cursor=database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val myContact=MyContact(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(myContact)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
    fun getAllSortId(): ArrayList<MyContact> {
        val list = ArrayList<MyContact>()
        val query="select*from $TABLE_NAME ORDER BY $ID ASC "
        val database=this.readableDatabase
        var cursor=database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val myContact=MyContact(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(myContact)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    override fun deleteContact(myContact: MyContact) {
        val database=this.writableDatabase
        database.delete(TABLE_NAME,"id=?", arrayOf(myContact.id.toString()))
        database.close()
    }

    override fun upDataContact(myContact: MyContact) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, myContact.id)
        contentValues.put(NAME, myContact.name)
        contentValues.put(NUMBER, myContact.number)
        database.update(TABLE_NAME,contentValues,"id=?", arrayOf(myContact.id.toString()))
        database.close()
    }
}
package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper


/**
 */
class Sqlite : SQLiteOpenHelper {
    constructor(context: Context?) : super(context, "as", null, 1) {}
    constructor(
        context: Context?,
        name: String?,
        factory: CursorFactory?,
        version: Int
    ) : super(context, name, factory, version) {
    }

    override fun onCreate(db: SQLiteDatabase) {
        val a =
            "Create Table MessageTypes (TypeID integer   primary key, TypeDescription text ,newMsg integer  )"
        db.execSQL(a)
        val b =
            "Create Table Messages     (MsgID  integer   primary key,MsgDescription text,TypeDescription integer,origPos integer ,newMsg int,fav int,orderOfFav int)"
        db.execSQL(b)
        val c = "Create table Favs(MsgID integer)"
        db.execSQL(c)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
    }

    fun saveMsgTypes(cMT: MsgTypes) {
        val db = writableDatabase
        val sql =
            "insert into MessageTypes(TypeID,TypeDescription,newMsg) values(" + cMT.getTypeID()
                .toString() + ",'" + cMT.getTypeDescription().toString() + "'," + cMT.getNewMsg()
                .toString() + ")"
        db.execSQL(sql)
        db.close()
    }

    val msgTypes: List<Any>
        get() {
            var u: MsgTypes
            val myList: MutableList<MsgTypes> = ArrayList<MsgTypes>()
            val db = readableDatabase
            val c = db.rawQuery("SELECT *   FROM MessageTypes ", null)
            if (c.moveToFirst()) {
                do {
                    u = MsgTypes()
                    u.setTypeID(c.getInt(0))
                    u.setTypeDescription(c.getString(1))
                    u.setNewMsg(c.getString(2))
                    val countCursor = db.rawQuery(
                        "SELECT count(*) as c from messages where TypeDescription=" + c.getInt(0),
                        null
                    )
                    if (countCursor.moveToFirst()) {
                        u.setCounter(countCursor.getInt(0))
                    }
                    myList.add(u)
                } while (c.moveToNext())
            }
            c.close()
            db.close()
            return myList
        }

    fun saveMessages(msg: Msgs) {
        val db = writableDatabase
        val sql =
            "insert into Messages(MsgID,MsgDescription,TypeDescription,origPOS,newMsg) values(" + msg.getMsgID()
                .toString() + ",'" + msg.getMsgDescription()
                .toString() + "'," + msg.getTypeDescription().toString() + "," + msg.getOrigPos()
                .toString() + "," + msg.getNewMsg().toString() + ")"
        db.execSQL(sql)
        db.close()
    }

    fun getMessages(typeID: Int): List<Msgs> {
        var u: Msgs
        val myList: MutableList<Msgs> = ArrayList<Msgs>()
        val db = readableDatabase
        val c = db.rawQuery(
            " SELECT *   FROM Messages   where TypeDescription='$typeID' order by msgID desc",
            null
        )
        if (c.moveToFirst()) {
            do {
                u = Msgs()
                u.setMsgID(c.getInt(0))
                u.setMsgDescription(c.getString(1))
                u.setTypeDescription(c.getInt(2))
                u.setOrigPos(c.getInt(3))
                u.setNewMsg(c.getInt(4))
                u.setOrderFav(c.getInt(6))
                myList.add(u)
            } while (c.moveToNext())
        }
        c.close()
        db.close()
        return myList
    }

    fun clearTables() {
        val db = writableDatabase
        val sql = "delete from MessageTypes"
        db.execSQL(sql)
        db.close()
    }

    fun ClearMsgs() {
        val db = writableDatabase
        val sql = "delete from messages"
        db.execSQL(sql)
        db.close()
    }

    fun changeFav(msg: Msgs, intFav: Int) {
        val db = writableDatabase
        var sql = "select max(orderOfFav) from messages"
        var intMaxOrderOfFav = 0
        val c = db.rawQuery(sql, null)
        if (c.moveToFirst());
        run { intMaxOrderOfFav = c.getInt(0) }
        intMaxOrderOfFav = intMaxOrderOfFav + 1
        if (intFav == 0) {
            sql =
                "update Messages set fav=" + intFav + " ,orderOfFav=0 where msgID=" + msg.getMsgID()
            db.execSQL(sql)
            sql = "delete from  favs where msgID=" + msg.getMsgID()
            db.execSQL(sql)
        } else {
            sql =
                "update Messages set fav=" + intFav + " ,orderOfFav=" + intMaxOrderOfFav + " where msgID=" + msg.getMsgID()
            db.execSQL(sql)
            sql = "insert into favs values(" + msg.getMsgID().toString() + ")"
            db.execSQL(sql)
        }
        c.close()
        db.close()
    }

    //    public void updateFavOnRefersh() {
    //        SQLiteDatabase db = getWritableDatabase();
    //        String sql = "update messages set fav=1 where msgid in(select msgid from favs)";
    //        db.execSQL(sql);
    //
    //    }
    //
    fun getIFMsgIsFav(m: Msgs): Int {
        var result = 0
        val db = writableDatabase
        val countCursor =
            db.rawQuery("SELECT fav  from messages where msgID=" + m.getMsgID(), null)
        if (countCursor.moveToFirst()) {
            result = countCursor.getInt(0)
        }
        db.close()
        return result
    }

    //    public List<Msgs> getFavMessagesOrderedASC() {
    val favMessages: List<Any>
        get() {
            var u: Msgs
            val myList: MutableList<Msgs> = ArrayList<Msgs>()
            val db = readableDatabase
            val c =
                db.rawQuery(
                    " SELECT *   FROM Messages   where fav=1  order by orderOfFav desc",
                    null
                )
            if (c.moveToFirst()) {
                do {
                    u = Msgs()
                    u.setMsgID(c.getInt(0))
                    u.setMsgDescription(c.getString(1))
                    u.setTypeDescription(c.getInt(2))
                    u.setOrigPos(c.getInt(3))
                    u.setNewMsg(c.getInt(4))
                    u.setOrderFav(c.getInt(6))
                    myList.add(u)
                } while (c.moveToNext())
            }
            c.close()
            db.close()
            return myList
        }
    //        Msgs u;
    //
    //        List<Msgs> myList = new ArrayList<>();
    //        SQLiteDatabase db = getReadableDatabase();
    //        Cursor c = db.rawQuery(" SELECT *   FROM Messages   where fav=1  order by orderOfFav asc", null);
    //
    //        if (c.moveToFirst()) {
    //
    //            do {
    //                u = new Msgs();
    //                u.setMsgID(c.getInt(0));
    //                u.setMsgDescription(c.getString(1));
    //                u.setTypeId(c.getInt(2));
    //                u.setOrigPos(c.getInt(3));
    //                u.setNewMsg(c.getInt(4));
    //                u.setOrderFav(c.getInt(6));
    //
    //                myList.add(u);
    //            }
    //            while (c.moveToNext());
    //        }
    //        c.close();
    //        db.close();
    //        return myList;
    //    }
    //
    //
    //    public List<Msgs> getFavMessagesNotOrdered() {
    //        Msgs u;
    //
    //        List<Msgs> myList = new ArrayList<>();
    //        SQLiteDatabase db = getReadableDatabase();
    //        Cursor c = db.rawQuery(" SELECT *   FROM Messages   where fav=1  ", null);
    //
    //        if (c.moveToFirst()) {
    //
    //            do {
    //                u = new Msgs();
    //                u.setMsgID(c.getInt(0));
    //                u.setMsgDescription(c.getString(1));
    //                u.setTypeId(c.getInt(2));
    //                u.setOrigPos(c.getInt(3));
    //                u.setNewMsg(c.getInt(4));
    //                u.setOrderFav(c.getInt(6));
    //                myList.add(u);
    //            }
    //            while (c.moveToNext());
    //        }
    //        c.close();
    //        db.close();
    //        return myList;
    //    }
    //
}
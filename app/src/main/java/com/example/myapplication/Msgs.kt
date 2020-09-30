package com.example.myapplication

import android.os.Parcel

class Msgs {
    private var MsgID = 0

    private var MsgDescription: String? = null

    private var TypeDescription = 0

    private var newMsg = 0

    private var origPos = 0

    private var isFav = 0

    private var orderFav = 0


    fun Msgs() {}

    fun Msgs(msgID: Int, msgDescription: String?, TypeDescription: Int, newMsg: Int) {
        var TypeDescription = TypeDescription
        MsgID = msgID
        MsgDescription = msgDescription
        TypeDescription = TypeDescription
        this.newMsg = newMsg
    }

    fun Msgs(msgDescription: String?, typeDescription: Int, newMsg: Int) {
        MsgDescription = msgDescription
        TypeDescription = typeDescription
        this.newMsg = newMsg
    }

    fun getMsgID(): Int {
        return MsgID
    }

    fun setMsgID(msgID: Int) {
        MsgID = msgID
    }

    fun getMsgDescription(): String? {
        return MsgDescription
    }

    fun setMsgDescription(msgDescription: String?) {
        MsgDescription = msgDescription
    }

    fun getTypeDescription(): Int {
        return TypeDescription
    }

    fun setTypeDescription(typeDescription: Int) {
        TypeDescription = typeDescription
    }

    fun getNewMsg(): Int {
        return newMsg
    }

    fun setNewMsg(newMsg: Int) {
        this.newMsg = newMsg
    }

    fun getOrigPos(): Int {
        return origPos
    }

    fun setOrigPos(origPos: Int) {
        this.origPos = origPos
    }

    fun getIsFav(): Int {
        return isFav
    }

    fun setIsFav(isFav: Int) {
        this.isFav = isFav
    }

    fun getOrderFav(): Int {
        return orderFav
    }

    fun setOrderFav(orderFav: Int) {
        this.orderFav = orderFav
    }

    override fun toString(): String {
        return "Msgs(MsgID=$MsgID, MsgDescription=$MsgDescription, TypeDescription=$TypeDescription, newMsg=$newMsg, origPos=$origPos, isFav=$isFav, orderFav=$orderFav)"
    }


}

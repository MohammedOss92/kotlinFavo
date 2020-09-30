package com.example.myapplication

class MsgTypes {

    private var TypeID = 0

    private var TypeDescription: String? = null

    private var newMsg: String? = null

    private var counter = 0

    fun MsgTypes(typeID: Int, typeDescription: String, newMsg: String?, counter: Int) {
        TypeID = typeID
        TypeDescription = typeDescription
        this.newMsg = newMsg
        this.counter = counter
    }

    fun MsgTypes() {}

    fun getTypeID(): Int {
        return TypeID
    }

    fun setTypeID(typeID: Int) {
        TypeID = typeID
    }

    fun getTypeDescription(): String? {
        return TypeDescription
    }

    fun setTypeDescription(typeDescription: String) {
        TypeDescription = typeDescription
    }

    fun getNewMsg(): String? {
        return newMsg
    }

    fun setNewMsg(newMsg: String?) {
        this.newMsg = newMsg
    }

    fun getCounter(): Int {
        return counter
    }

    fun setCounter(counter: Int) {
        this.counter = counter
    }

    override fun toString(): String {
        return "MsgTypes(TypeID=$TypeID, TypeDescription=$TypeDescription, newMsg=$newMsg, counter=$counter)"
    }


}

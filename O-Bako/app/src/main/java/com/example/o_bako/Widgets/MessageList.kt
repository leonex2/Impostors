package com.example.o_bako.Widgets

class MessageList() {
    private val myMessage = arrayListOf(
            "Have a Good Day!",
            "It's a Great Day to Start",
            "Stay Healthy",
            "Don't Worry, Be Happy !"
    )
    private var index = -1
    fun addMessage(str: String) {
        myMessage.add(str)
    }
    fun removeMessage(str: String){
        myMessage.remove(str)
    }
    fun backToStart() { index = -1}
    fun getMessage() : String {
        if(myMessage.size==0)
            return "Ehe~"
        if(index+1==myMessage.size)
            backToStart()
        index+=1
        return myMessage.get(index)
    }
}
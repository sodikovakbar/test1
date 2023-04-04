package com.oil.bottomnavigationview.db
    interface MyDbInterface {


        fun addContact(myContact: MyContact)
        fun getAllContact():List<MyContact>
        fun deleteContact(myContact: MyContact)
        fun upDataContact(myContact: MyContact)
    }

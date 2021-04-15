package com.example.o_bako

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.o_bako.others.ContactList
import com.example.o_bako.others.ContactRecycle
import kotlinx.android.synthetic.main.activity_invite_friend.*

class InviteFriend : AppCompatActivity(),
        LoaderManager.LoaderCallbacks<Cursor>{

    var Display_Name = ContactsContract.Contacts.DISPLAY_NAME
    var Display_Number = ContactsContract.CommonDataKinds.Phone.NUMBER
    private var myFriendList : MutableList<ContactList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_friend)
        LoaderManager.getInstance(this).initLoader(1, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        var MyContentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        var myProjection = arrayOf(Display_Name,Display_Number)
        return CursorLoader(
                this, MyContentUri, myProjection,
                null, null, "$Display_Name ASC"
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        myFriendList.clear()
        if (data != null) {
            data.moveToFirst()
            while (!data.isAfterLast) {
                myFriendList.add(
                        ContactList(
                                nama = data.getString(data.getColumnIndex(Display_Name)),
                                noHP = data.getString(data.getColumnIndex(Display_Number)),
                                status = true
                        )
                )
                data.moveToNext()
            }
        }
        val contactAdapter = ContactRecycle(myFriendList)
        viewItem.apply {
            layoutManager = LinearLayoutManager(this@InviteFriend)
            adapter = contactAdapter
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        viewItem.adapter?.notifyDataSetChanged()
    }
}
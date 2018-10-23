package com.login_page.hp.contacts;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    ListView list;
    private static final String TAG = "ContactActivity";
    Integer contactName,phone;
    String contact,id,phone_number;

    contacts c;
    List< contacts> al = new ArrayList();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        Cursor cursor = managedQuery(ContactsContract.Contacts.CONTENT_URI,
                null,null,null,null);

        while (cursor.moveToNext()){

            contactName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact = cursor.getString(contactName);

            id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));


            if (Integer.parseInt(cursor.getString(cursor.getColumnIndex
                    (ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
            {
                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.
                        Phone.CONTENT_URI,null,ContactsContract.
                        CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                while (phones.moveToNext()) {
                    phone_number = phones.getString(phones.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));

//                    Log.i("Number", phone_number);
                }
                phones.close();
            }
            c = new contacts(contact,phone_number);
            al.add(c);
        }

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new RecyclerAdapter(al);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
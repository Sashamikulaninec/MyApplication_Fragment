package com.example.sasha.myapplication_fragment;

/**
 * Created by sasha on 14.08.15.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sasha.myapplication_fragment.Model.Item;
import com.example.sasha.myapplication_fragment.db.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class FragmentContacts extends Fragment {
    private Item item;
    protected RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Item> mData = new ArrayList<Item>();
    private MyDBHelper mMyDBHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts, null);
        mMyDBHelper    = new MyDBHelper(getActivity(),"db_contacts", null, 1);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mAdapter       = new MyRecyclerAdapter(getActivity(), mData);
        mRecyclerView  = (RecyclerView) v.findViewById(R.id.rvList);
        mData          = getContacts();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        return v;
    }

    public List<Item> getContacts() {

        SQLiteDatabase db = mMyDBHelper.getWritableDatabase();
        List<Item> list = new ArrayList<Item>();
        if (checkDataBase(db) == false) {
            writeToDb();
            readFromDb(list);
        }else {
            readFromDb(list);
        }
        return list;
    }
    private boolean checkDataBase(SQLiteDatabase db){

        Cursor mCursor = db.rawQuery("SELECT * FROM " + MyDBHelper.CONTACTS_TABLE_NAME, null);
        Boolean rowExists;

        if (mCursor.moveToFirst())
        {
            rowExists = true;
        } else
        {
            rowExists = false;
        }
        return rowExists;
    }

    public void writeToDb(){
        SQLiteDatabase db = mMyDBHelper.getWritableDatabase();

        Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {

            String mname = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String mphoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            ContentValues cv = new ContentValues();
            cv.put(MyDBHelper.CONTACT_NAME, mname);
            cv.put(MyDBHelper.CONTACT_NUMBER, mphoneNumber);
            db.insert(MyDBHelper.CONTACTS_TABLE_NAME, null, cv);
        }
        phones.close();
    }

    public void readFromDb( List list){
        SQLiteDatabase db = mMyDBHelper.getWritableDatabase();

        String select = "SELECT * FROM " + MyDBHelper.CONTACTS_TABLE_NAME;
        Cursor c = db.rawQuery(select, null);
        int nameIndex = c.getColumnIndex(MyDBHelper.CONTACT_NAME);
        int numberIndex = c.getColumnIndex(MyDBHelper.CONTACT_NUMBER);
        int idIndex = c.getColumnIndex(MyDBHelper.CONTACT_ID);
        c.moveToFirst();
        while (c.moveToNext()) {

            item = new Item(c.getString(nameIndex),c.getString(numberIndex), c.getInt(idIndex));
            list.add(item);

        }
        db.close();
    }
}

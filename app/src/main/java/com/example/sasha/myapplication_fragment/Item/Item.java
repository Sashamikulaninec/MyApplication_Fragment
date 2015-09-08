package com.example.sasha.myapplication_fragment.Item;

/**
 * Created by sasha on 21.08.15.
 */
public class Item {

    private String mName;
    private String mPhoneNumber;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Item(String _Name, String _PhoneNunber, int id) {
        this.id = id;
        this.mName = _Name;
        this.mPhoneNumber = _PhoneNunber;

    }

    @Override
    public String toString() {
        return "Item{" +
                "mName='" + mName + '\'' +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                ", id=" + id +
                '}';
    }

    public Item() {

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String _Name) {
        mName = _Name;
    }

    public String getmPhoneNumber() {


        return mPhoneNumber;
    }

    public void setmPhoneNumber(String _PhoneNumber) {
        mPhoneNumber = _PhoneNumber;
    }
}

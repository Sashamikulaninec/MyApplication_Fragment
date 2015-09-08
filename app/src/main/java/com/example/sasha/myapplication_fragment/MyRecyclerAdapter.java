package com.example.sasha.myapplication_fragment;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sasha.myapplication_fragment.Model.Item;
import com.example.sasha.myapplication_fragment.db.MyDBHelper;

import java.util.List;

/**
 * Created by sasha on 21.08.15.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<Item> mData;
    private MyDBHelper mMyDBHelper;

    public MyRecyclerAdapter(Context _context, List<Item> _data) {
        mContext = _context;
        mData = _data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup _viewGroup, int _viewType) {
        final View itemView = LayoutInflater.from(mContext).inflate(R.layout.custom, _viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int position)  {

        myViewHolder.itemView.setOnLongClickListener(new MyLongClick(position));
        myViewHolder.itemView.setOnClickListener(new MyLongClick(position));
        myViewHolder.onBind();

    }
    public void dbUpdate(String n, String nm, int position){

        Item item = mData.get(position);
        item.setmName(n);
        item.setmPhoneNumber(nm);

        mMyDBHelper = new MyDBHelper(mContext,"db_contacts", null, 1 );
        SQLiteDatabase db = mMyDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDBHelper.CONTACT_ID, item.getId());
        values.put(MyDBHelper.CONTACT_NAME, n);
        values.put(MyDBHelper.CONTACT_NUMBER, nm);
        String where = MyDBHelper.CONTACT_ID + " = " + item.getId();

        db.update(MyDBHelper.CONTACTS_TABLE_NAME, values,where , null);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class MyLongClick implements View.OnLongClickListener, View.OnClickListener{
        int position;

        MyLongClick(int position) {
            this.position = position;
        }

        @Override
        public boolean onLongClick(View view) {
            final TextView TelNumber;
            final TextView Name;
            Name = (TextView) view.findViewById(R.id.name);
            TelNumber = (TextView) view.findViewById(R.id.number);
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            final EditText name = new EditText(mContext);
            final EditText number = new EditText(mContext);
            name.setHint("Enter name");
            number.setHint("Enter number");
            LinearLayout layout = new LinearLayout(mContext);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(name);
            layout.addView(number);
            builder.setView(layout);
            builder.setMessage("Edit contact")
                    .setTitle("Edit contact")
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String nameDB;
                            String numberDB;

                            String editName = name.getText().toString();
                            Name.setText(editName);

                            String editNumber = number.getText().toString();
                            TelNumber.setText(editNumber);


                            Name.setBackgroundColor(Color.parseColor("#ff00ff"));
                            TelNumber.setBackgroundColor(Color.parseColor("#ffff00"));

                            nameDB = Name.getText().toString();
                            numberDB = TelNumber.getText().toString();

                            dbUpdate(nameDB, numberDB, position);

                        }

                    });
            builder.create().show();

            return true;

        }

        @Override
        public void onClick(View view) {
            TextView TelNumber;
            String number;

            TelNumber = (TextView) view.findViewById(R.id.number);
            number = TelNumber.getText().toString();
            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(callIntent);
        }
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        CustomView customView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            customView = (CustomView) itemView.findViewById(R.id.custom);
        }

        public void onBind() {
            Item item = mData.get(getPosition());
            customView.setTextName(item.getmName());
            customView.setTextNumber(item.getmPhoneNumber());

        }
    }
}



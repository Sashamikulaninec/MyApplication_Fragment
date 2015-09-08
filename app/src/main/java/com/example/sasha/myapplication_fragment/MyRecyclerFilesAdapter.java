package com.example.sasha.myapplication_fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.sasha.myapplication_fragment.Model.MyFile;

import java.util.List;

public class MyRecyclerFilesAdapter extends RecyclerView.Adapter<MyRecyclerFilesAdapter.MyHolder> {

    private final Context mMyContext;
    private final List<MyFile> mFileData;

    public MyRecyclerFilesAdapter(Context _context, List<MyFile> _data) {
        mMyContext = _context;
        mFileData = _data;
    }

    @Override
    public MyHolder onCreateViewHolder(final ViewGroup viewGroup, int _viewType) {
        final View itemView = LayoutInflater.from(mMyContext).inflate(R.layout.files, viewGroup, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder myHolder, final int i) {
        myHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return mFileData == null ? 0 : mFileData.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        TextView textView;


        public MyHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.file_type_AM);
        }

        public void onBind() {
            MyFile myFile = mFileData.get(getPosition());
            textView.setText(myFile.getFileName());
        }
    }
}

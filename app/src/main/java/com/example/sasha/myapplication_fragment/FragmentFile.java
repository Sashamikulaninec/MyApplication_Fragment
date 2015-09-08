package com.example.sasha.myapplication_fragment;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sasha.myapplication_fragment.Model.MyFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FragmentFile extends Fragment {

    private MyFile myFile;
    protected RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<MyFile> mFileData = new ArrayList<MyFile>();
    private TextView myPath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_file, null);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.fileList);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mAdapter = new MyRecyclerFilesAdapter(getActivity(), mFileData);
        myPath = (TextView) v.findViewById(R.id.file_path_AM);
        mFileData = getFiles();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        return v;

    }

    public List<MyFile> getFiles() {
        String root = Environment.getExternalStorageDirectory().getPath();
        List<MyFile> list = new ArrayList<MyFile>();
        myPath.setText("Location: " + root);

        File f = new File(root);
        File[] files = f.listFiles();

        if (!root.equals(root)) {
            list.add(new MyFile((f.getParent())));
        }

        for (int i = 0; i < files.length; i++) {
            File file = files[i];

            if (!file.isHidden() && file.canRead()) {

                if (file.isDirectory()) {
                    String fileName = file.getName();
                    myFile = new MyFile(fileName);
                    list.add(myFile);

                }
            }
        }
        return list;
    }
}


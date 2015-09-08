package com.example.sasha.myapplication_fragment;

import android.app.Activity;
import android.app.AlertDialog;
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

import com.example.sasha.myapplication_fragment.Item.MyFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sasha on 02.09.15.
 */
public class FragmentFile extends Fragment {
    private static final String TAG = "w";
    private static final String TAB = "e";
    private MyFile myFile;
    protected RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<MyFile> mFileData = new ArrayList<MyFile>();
    private TextView myPath;



    // Get reference to interface in Activity

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
                String filePath = file.getPath();
                Log.e(TAB, filePath);
                if (file.isDirectory()) {
                    String fileName = file.getName();
                    Log.e(TAG, fileName);
                    myFile = new MyFile(fileName);
                    list.add(myFile);


                }
            }




        }
        return list;

    }
}


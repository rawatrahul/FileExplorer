package com.rrawat.fileexplorer;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class ListFileActivity extends ListActivity {

    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_files);

        // Use the current directory as title
        path = "/sdcard";
        if (getIntent().hasExtra("path")) {
            path = getIntent().getStringExtra("path");
        }
        setTitle(path);

        // Read all files sorted into the values-array
        ArrayList values = new ArrayList();
        File dir = new File(path);
        if (!dir.canRead()) {
            setTitle(getTitle() + " (inaccessible)");
        }
        String[] list = dir.list();
        if (list != null) {
            for (String file : list) {
                if (!file.startsWith(".")) {
                    values.add(file);
                }
            }
        }
        /*
        List dirs = new ArrayList();
        List files = new ArrayList();
        FileFilter filterDirectoriesOnly = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
        FileFilter filterFilesOnly = new FileFilter() {
            public boolean accept(File file) {
                return !file.isDirectory();
            }
        };


        File[] dirList = dir.listFiles(filterDirectoriesOnly);
        File[] fileList = dir.listFiles(filterFilesOnly);
        String[] dirArr=new String[dirList.length],fileArr=new String[fileList.length];
        for (int i = 0; i < dirList.length; i++) {
            File file = dirList[i];
           // Log.d("***Rahul***",file.getName());
            dirArr[i]=file.getName();
        }
        for (int i = 0; i < fileList.length; i++) {
            File file = fileList[i];
            fileArr[i]=file.getName();
        }


        if (dirArr != null) {
            for (String file : dirArr) {
                if (!file.startsWith(".")) {
                    dirs.add(file);
                }
            }
        }
        if (fileArr != null) {
            for (String file : fileArr) {
                if (!file.startsWith(".")) {
                    files.add(file);
                }
            }
        }



        Collections.sort(dirs);
        Collections.sort(files);*/
        Collections.sort(values);

        // Put the data into the list

//        this.setListAdapter(new ArrayAdapter<String>(this,R.layout.myfolder,R.id.Itemname,dirs));
//        this.setListAdapter(new ArrayAdapter<String>(this,R.layout.myfiles,R.id.Itemname,files));
        setListAdapter(new FilesAndFoldersAdapter(this, values,path));
        /*ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, values);
        setListAdapter(adapter);*/

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String filename = (String) getListAdapter().getItem(position);
        if (path.endsWith(File.separator)) {
            filename = path + filename;
        } else {
            filename = path + File.separator + filename;
        }
        if (new File(filename).isDirectory()) {
            Intent intent = new Intent(this, ListFileActivity.class);
            intent.putExtra("path", filename);
            startActivity(intent);
        } else {
            Toast.makeText(this, filename + " is not a directory", Toast.LENGTH_LONG).show();
        }
    }
}


package com.rrawat.fileexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by RRawat on 08-05-2015.
 */
public class FilesAndFoldersAdapter extends ArrayAdapter<String> {
    String tempPath = null;
    public FilesAndFoldersAdapter(Context context, ArrayList<String> values,String path) {
        super(context, 0, values);
        tempPath = path +"/";
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String tmp = getItem(position);
        String filePath = tempPath + tmp;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mylist, parent, false);
        }

        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.Itemname);
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.icon);

        // Populate the data into the template view using the data object
        tvName.setText(tmp);

        if (new File(filePath).isDirectory()) {
            ivImage.setImageResource(R.drawable.folder);
        } else {
            ivImage.setImageResource(R.drawable.file);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
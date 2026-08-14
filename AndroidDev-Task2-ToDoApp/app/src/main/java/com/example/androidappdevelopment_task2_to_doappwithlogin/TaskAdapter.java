package com.example.androidappdevelopment_task2_to_doappwithlogin;


import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Task> taskList;
    private final DatabaseHelper databaseHelper;

    public TaskAdapter(
            Context context,
            ArrayList<Task> taskList,
            DatabaseHelper databaseHelper
    ) {
        this.context = context;
        this.taskList = taskList;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return taskList.get(position).getId();
    }

    @Override
    public View getView(
            int position,
            View convertView,
            ViewGroup parent
    ) {
        final View rowView;
        if (convertView == null) {
            rowView = LayoutInflater.from(context)
                    .inflate(
                            R.layout.item_task,
                            parent,
                            false
                    );
        } else {
            rowView = convertView;
        }

        TextView txtTaskName =
                rowView.findViewById(R.id.txtTaskName);

        TextView txtNotes =
                rowView.findViewById(R.id.txtNotes);

        CheckBox checkCompleted =
                rowView.findViewById(R.id.checkCompleted);

        Button btnDelete =
                rowView.findViewById(R.id.btnDelete);

        Task task = taskList.get(position);

        txtTaskName.setText(task.getTaskName());
        txtNotes.setText(task.getNotes());

        checkCompleted.setOnCheckedChangeListener(null);

        checkCompleted.setChecked(task.isCompleted());

        if (task.isCompleted()) {

            txtTaskName.setPaintFlags(
                    txtTaskName.getPaintFlags()
                            | Paint.STRIKE_THRU_TEXT_FLAG
            );

        } else {

            txtTaskName.setPaintFlags(
                    txtTaskName.getPaintFlags()
                            & (~Paint.STRIKE_THRU_TEXT_FLAG)
            );
        }

        checkCompleted.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    task.setCompleted(isChecked);

                    databaseHelper.updateTaskStatus(
                            task.getId(),
                            isChecked
                    );

                    notifyDataSetChanged();
                }
        );

        btnDelete.setOnClickListener(v -> {

            databaseHelper.deleteTask(task.getId());

            taskList.remove(position);

            notifyDataSetChanged();
        });

        return rowView;
    }
}

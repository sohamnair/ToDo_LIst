package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFragAdapter extends RecyclerView.Adapter<ListFragAdapter.MyViewHolder> {

    Context context;
    ArrayList<ListFragDataFetch> list;

    public ListFragAdapter(Context context, ArrayList<ListFragDataFetch> list) {
        this.context = context;
        this.list = list;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listfragcardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListFragDataFetch fetch = list.get(position);
        holder.name.setText(fetch.getDate());
        holder.dob.setText(fetch.getTask());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, dob;
        LinearLayout l1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            dob = itemView.findViewById(R.id.dob);
            l1 = itemView.findViewById(R.id.l1);
        }
    }
}

package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.todolist.HomeFragAdapter.*;

public class HomeFragAdapter extends RecyclerView.Adapter<HomeFragAdapter.MyViewHolder> {

    Context context;
    ArrayList<HomeFragDataFetch> list;

    public HomeFragAdapter(Context context, ArrayList<HomeFragDataFetch> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.homefragcardview,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HomeFragDataFetch fetch = list.get(position);
        String s = fetch.getName()+", "+fetch.getAge();
        holder.name.setText(s);
        holder.dob.setText(fetch.getDob());
        holder.l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key",fetch.getContact()); // Put anything what you want
                ListFragment fragment2 = new ListFragment();
                fragment2.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment2).addToBackStack(null).commit();
            }
        });
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

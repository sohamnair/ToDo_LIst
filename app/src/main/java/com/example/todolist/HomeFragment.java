package com.example.todolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Toolbar tb;
    TextView title, switchmode;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch mode;

    SharedPreferences sp, sp1;
    Boolean bool;
    String num;

    RecyclerView recyclerView;
    HomeFragAdapter adapter;
    ArrayList<HomeFragDataFetch> list;

    TextView name, dob;
    CardView card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        tb = v.findViewById(R.id.hometb);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);

        title = v.findViewById(R.id.title);
        switchmode = v.findViewById(R.id.switchmode);
        mode = v.findViewById(R.id.switch1);

        card = v.findViewById(R.id.homecard);

        name = v.findViewById(R.id.name);
        dob = v.findViewById(R.id.dob);

        recyclerView = v.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();

//        list.add(new HomeFragDataFetch("Sam","1111","12"));
//        list.add(new HomeFragDataFetch("Sam","1111","12"));
//        list.add(new HomeFragDataFetch("Sam","1111","12"));
//        list.add(new HomeFragDataFetch("Sam","1111","12"));
//        list.add(new HomeFragDataFetch("Sam","1111","12"));
//        list.add(new HomeFragDataFetch("Sam","1111","12"));
//        list.add(new HomeFragDataFetch("Sam","1111","12"));
//        list.add(new HomeFragDataFetch("Sam","1111","12"));

        adapter = new HomeFragAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        sp1 = ((AppCompatActivity)getActivity()).getSharedPreferences("login", Context.MODE_PRIVATE);
        num = sp1.getString("num","");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("UserData");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {
                    HomeFragDataFetch fetch = ds.getValue(HomeFragDataFetch.class);
                    if(!(fetch.getContact().equals(num))) {
                        list.add(fetch);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("UserData").child(num);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String strname = snapshot.child("Name").getValue().toString()+", "+snapshot.child("Age").getValue().toString();
                String strdob = snapshot.child("Dob").getValue().toString();
                name.setText(strname);
                dob.setText(strdob);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key",""); // Put anything what you want
                ListFragment fragment2 = new ListFragment();
                fragment2.setArguments(bundle);
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment2).addToBackStack(null).commit();

            }
        });

        sp = ((AppCompatActivity)getActivity()).getSharedPreferences("pref", Context.MODE_PRIVATE);
        bool = sp.getBoolean("state", false);

        if(bool) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            mode.setChecked(true);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            mode.setChecked(false);
        }


        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("state",true);
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("state",false);
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });


        return v;
    }

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment HomeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

}
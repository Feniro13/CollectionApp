package com.Feniro.collectionapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.R;
import com.Feniro.collectionapp.adapter.ItemAddAdapter;

import java.util.List;

public class Fragment_AddToCollection extends Fragment {
    RecyclerView recyclerView;
    ItemAddAdapter itemAddAdapter;

    Context context;
    List<String> list;
    int position;

    TextView name;

    public Fragment_AddToCollection(Context context, List<String> list, int position, String item) {
        super(R.layout.fragment_add_to_collection);
        this.context = context;
        this.list = list;
        this.position = position;
        this.name.setText(item);



    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}

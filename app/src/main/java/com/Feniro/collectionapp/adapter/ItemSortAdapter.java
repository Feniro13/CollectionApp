package com.Feniro.collectionapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.R;

import java.util.List;

public class ItemSortAdapter extends RecyclerView.Adapter<ItemSortAdapter.ItemSortViewHolder> {

    List<String> items;
    Context context;

    public ItemSortAdapter(List<String> items, Context context) {
        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public ItemSortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_view_diff_sort, parent, false);
        return new ItemSortAdapter.ItemSortViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSortViewHolder holder, int position) {
        holder.textView.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemSortViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ItemSortViewHolder(@NonNull View items) {
            super(items);

            textView = items.findViewById(R.id.item_view_diff_sort_textview);
        }
    }
}

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

public class ItemAddAdapter extends RecyclerView.Adapter<ItemAddAdapter.ItemAddViewHolder> {
    Context context;
    public List<String> list;
    int numberOfColumns;

    public ItemAddAdapter(Context context, List<String> list, int numberOfColumns) {
        this.context = context;
        this.list = list;
        this.numberOfColumns = numberOfColumns;
    }


    @NonNull
    @Override
    public ItemAddAdapter.ItemAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_add_to_collection, parent, false);
        return new ItemAddAdapter.ItemAddViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAddAdapter.ItemAddViewHolder holder, int position) {
        holder.editTextNameColumn.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static final class ItemAddViewHolder extends RecyclerView.ViewHolder {

        TextView editTextNameColumn;

        public ItemAddViewHolder(@NonNull View itemView) {
            super(itemView);
            editTextNameColumn = itemView.findViewById(R.id.item_column_edittext);
        }
    }
}

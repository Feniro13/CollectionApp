package com.Feniro.collectionapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.R;
import com.Feniro.collectionapp.model.ColumnModel;

import java.util.List;

public class ColumnAdapter extends RecyclerView.Adapter<ColumnAdapter.ColumnViewHolder> {

    Context context;
    List<ColumnModel> list;

    public ColumnAdapter(Context context, List<ColumnModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ColumnAdapter.ColumnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_column, parent, false);
        return new ColumnViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull ColumnAdapter.ColumnViewHolder holder, int position) {
        holder.editText.getText();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static final class ColumnViewHolder extends RecyclerView.ViewHolder{

        EditText editText;

        public ColumnViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.item_column_edittext);
        }
    }
}

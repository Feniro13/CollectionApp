package com.Feniro.collectionapp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.R;
import com.Feniro.collectionapp.model.ColumnModel;

import java.util.List;

public class ColumnAdapter extends RecyclerView.Adapter<ColumnAdapter.ColumnViewHolder> {

    Context context;
    List<ColumnModel> list;
    List<String> names;


    public ColumnAdapter(Context context, List<ColumnModel> list, List<String> names) {
        this.list = list;
        this.context = context;
        this.names = names;
    }

    @NonNull
    @Override
    public ColumnAdapter.ColumnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_column, parent, false);
        return new ColumnViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull ColumnAdapter.ColumnViewHolder holder, int position) {
        holder.editTextNameColumn.setHint(list.get(position).getName());
        if(!names.get(position).equals("")) {
            holder.editTextNameColumn.setText(names.get(position));
        }
        holder.editTextNameColumn.setText(names.get(position));
        String gotText = holder.editTextNameColumn.getText().toString();
        if(gotText.equals("")) {
            return;
        }
        names.set(position, gotText);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static final class ColumnViewHolder extends RecyclerView.ViewHolder {

        TextView editTextNameColumn;

        public ColumnViewHolder(@NonNull View itemView) {
            super(itemView);
            editTextNameColumn = itemView.findViewById(R.id.item_column_edittext);
        }
    }
}

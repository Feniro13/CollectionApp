package com.Feniro.collectionapp.adapter;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
    public List<String> names;


    public ColumnAdapter(Context context, List<ColumnModel> list, List<String> names) {
        this.list = list;
        this.context = context;
        this.names = names;
    }

    @NonNull
    @Override
    public ColumnAdapter.ColumnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_create_column, parent, false);
        return new ColumnViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(ColumnViewHolder holder, int position) {
        holder.editTextNameColumn.setHint(list.get(position).getName());
        if(!names.get(position).equals("")) {
            holder.editTextNameColumn.setText(names.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public final class ColumnViewHolder extends RecyclerView.ViewHolder {

        TextView editTextNameColumn;

        public ColumnViewHolder(@NonNull View itemView) {
            super(itemView);
            editTextNameColumn = itemView.findViewById(R.id.item_column_edittext);
            editTextNameColumn.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    names.set(getAdapterPosition(), editTextNameColumn.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }
}

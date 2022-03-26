package com.Feniro.collectionapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.R;
import com.Feniro.collectionapp.database.entities.CollectionEntity;
import com.Feniro.collectionapp.model.ColumnModel;

import java.util.List;

public class EntitiesAdapter extends RecyclerView.Adapter<EntitiesAdapter.EntitiesViewHolder> {
    Context context;
    List<CollectionEntity> list;

    public EntitiesAdapter(Context context, List<CollectionEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EntitiesAdapter.EntitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_collectionview, parent, false);
        return new EntitiesAdapter.EntitiesViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull EntitiesAdapter.EntitiesViewHolder holder, int position) {
        holder.editText.getText();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static final class EntitiesViewHolder extends RecyclerView.ViewHolder{

        EditText editText;

        public EntitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.item_column_edittext);
        }
    }
}

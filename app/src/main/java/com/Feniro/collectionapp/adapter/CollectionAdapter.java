package com.Feniro.collectionapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.R;
import com.Feniro.collectionapp.model.CollectionModel;

import java.util.List;


public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    Context context;
    List<CollectionModel> collections;

    public CollectionAdapter(Context context, List<CollectionModel> collections) {
        this.context = context;
        this.collections = collections ;
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_collection, parent, false);
        return new CollectionViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.CollectionViewHolder holder, int position) {
        holder.textView.setText(collections.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    public static final class CollectionViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.TextCollection);

        }
    }
}

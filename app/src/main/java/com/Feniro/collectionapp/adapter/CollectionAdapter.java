package com.Feniro.collectionapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.R;

import java.util.List;


public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    Context context;
    List<String> collections;
    ViewGroup viewGroup;

    Button delete, choose, rename;

    public CollectionAdapter(Context context, List<String> collections, ViewGroup viewGroup) {
        this.context = context;
        this.collections = collections;
        this.viewGroup = viewGroup;
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_collection_to_edit, parent, false);
        return new CollectionViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.CollectionViewHolder holder, int position) {
        holder.textView.setText(collections.get(position));
        holder.button.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_rule_collections_action, viewGroup, false);
            Button delete = dialogView.findViewById(R.id.dialog_button_deletecol);
            Button rename = dialogView.findViewById(R.id.dialog_button_renamecol);
            Button back = dialogView.findViewById(R.id.dialog_button_back);
            Button open = dialogView.findViewById(R.id.dialog_button_editcol);

            delete.setOnClickListener(view1 -> {
                String name = collections.get(position);

            });

            rename.setOnClickListener(view1 -> {
                String name = collections.get(position);

            });

            back.setOnClickListener(view1 -> {


            });

            open.setOnClickListener(view1 -> {

            });


            builder.setView(dialogView);

//            builder.setPositiveButton("Редактировать коллекцию", (dialogInterface, i) -> {
//
//            });
//
//            builder.setNegativeButton("Удалить коллекцию", (dialogInterface, i) -> {
//
//            });
//
//            builder.setNeutralButton("Назад", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//
//                }
//            });

            builder.show();

        });

    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    public static final class CollectionViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        Button button;

        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.TextCollection);
            button = itemView.findViewById(R.id.Item_Collection_Button);


        }
    }
}

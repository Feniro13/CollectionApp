package com.Feniro.collectionapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.CollectionView;
import com.Feniro.collectionapp.R;
import com.Feniro.collectionapp.database.GlobalDatabase;
import com.Feniro.collectionapp.database.LocalDatabase;
import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntities;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    Context context;
    List<String> collections;
    ViewGroup viewGroup;


    GlobalDatabase globalDatabase;
    LocalDatabase localDatabase;

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

            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.show();

            Button delete = dialogView.findViewById(R.id.dialog_button_deletecol);
            Button rename = dialogView.findViewById(R.id.dialog_button_renamecol);
            Button back = dialogView.findViewById(R.id.dialog_button_back);
            Button open = dialogView.findViewById(R.id.dialog_button_editcol);

            delete.setOnClickListener(view1 -> {
                String name = collections.get(position);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Вы уверены?");
                builder1.setPositiveButton("Да", (dialogInterface, i) -> {
                    globalDatabase.dao_global().deleteCollectionByName(name);
                    localDatabase.dao().deleteEntitiesByName(name);
                    collections.remove(position);
                    alertDialog.dismiss();
                });
                builder1.setNegativeButton("Назад", (dialogInterface, i) -> {
                }); });



                rename.setOnClickListener(view1 -> {
                    String name = collections.get(position);
                    DatabaseGlobalEntities entity = globalDatabase.dao_global().getByName(name);
                    List<DatabaseLocalEntities> listLocal = localDatabase.dao().getAllByName(name);
        //                entity.name = ...
        //                for(int i = 0; i < listLocal.size(); i++) {
        //                    listLocal.get(i).DatabaseName = ...
        //                }
                    alertDialog.dismiss();
                });

                back.setOnClickListener(view1 -> { alertDialog.dismiss(); });

                open.setOnClickListener(view1 -> {
                    Intent intent = new Intent(context, CollectionView.class);
                    intent.putExtra("check", collections.get(position));
                    context.startActivity(intent);
                });


            builder.setView(dialogView);
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

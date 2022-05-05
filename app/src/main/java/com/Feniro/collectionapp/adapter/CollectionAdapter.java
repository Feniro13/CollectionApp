package com.Feniro.collectionapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.CollectionView;
import com.Feniro.collectionapp.R;
import com.Feniro.collectionapp.database.GlobalDatabase;
import com.Feniro.collectionapp.database.LocalDatabase;
import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntities;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        this.globalDatabase = GlobalDatabase.getDatabase(context); // Глобальная база данных
        this.localDatabase = LocalDatabase.getDatabase(context);
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_edit_collection, parent, false);
        return new CollectionViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.CollectionViewHolder holder, int position) {
        holder.textView.setText(collections.get(position));
        holder.textView.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_edit_action, viewGroup, false);
            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.show();
            String name = collections.get(position);

            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog.getWindow().setDimAmount(0.2f);
            Button delete = dialogView.findViewById(R.id.dialog_button_deletecol);
            Button rename = dialogView.findViewById(R.id.dialog_button_renamecol);
            Button back = dialogView.findViewById(R.id.dialog_button_back);
            Button open = dialogView.findViewById(R.id.dialog_button_editcol);
            TextView nameShow = dialogView.findViewById(R.id.dialog_textView_colname);
            nameShow.setText(name);

            delete.setOnClickListener(view1 -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                alertDialog.dismiss();
                builder1.setTitle("Вы уверены? Это действие необратимо");
                builder1.setPositiveButton("Да [удалить]", (dialogInterface, i) -> {
                    globalDatabase.dao_global().deleteCollectionByName(name);
                    localDatabase.dao().deleteEntitiesByName(name);
                    collections.remove(position);
                    notifyItemRemoved(position);
                });
                builder1.setNegativeButton("Назад", (dialogInterface, i) -> {
                    alertDialog.show();
                });
                builder1.show();});



            rename.setOnClickListener(view1 -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                View view2 = LayoutInflater.from(view1.getContext()).inflate(R.layout.dialog_edit_rename, viewGroup, false);
                builder1.setView(view2);
                final AlertDialog alertDialog1 = builder1.show();
                alertDialog1.show();
                alertDialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog1.getWindow().setDimAmount(0.2f);

                Button renameCollections = view2.findViewById(R.id.dialog_edit_rename_done);
                renameCollections.setOnClickListener(view3 -> {
                    EditText editText = view2.findViewById(R.id.dialog_edit_rename_edittext);
                    String newName = editText.getText().toString();
                    editText.setText("");
                    TextView warning = view2.findViewById(R.id.dialog_edit_rename_warner);
                    List<DatabaseGlobalEntities> databases = globalDatabase.dao_global().getAll();
                    for(int i = 0; i < databases.size(); i++) {
                        if(databases.get(i).name.equals(newName)) {
                            warning.setText("Вы уже создали коллекцию с таким названием");
                            return;
                        }
                    }
                    DatabaseGlobalEntities entityNew = globalDatabase.dao_global().getByName(collections.get(position));
                    entityNew.name = newName;

                    Date currDate = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                    String dateText = dateFormat.format(currDate);

                    entityNew.dataChanged = dateText;

                    globalDatabase.dao_global().deleteCollectionByName(collections.get(position));
                    globalDatabase.dao_global().insert(entityNew);

                    DatabaseLocalEntities entity1 = localDatabase.dao().getNamesOfColumnsByName(collections.get(position));
                    DatabaseLocalEntities entityNew1 = entity1;
                    entityNew1.DatabaseName = newName;
                    localDatabase.dao().delete(entity1);
                    localDatabase.dao().insert(entityNew1);

                    List<DatabaseLocalEntities> listLocal = localDatabase.dao().getAllByNameByColumn1(collections.get(position));
                    for(int i = 0; i < listLocal.size(); i++) {
                        entity1 = listLocal.get(1);
                        entity1.DatabaseName = newName;
                        localDatabase.dao().insert(entity1);
                        localDatabase.dao().delete(listLocal.get(i));
                    }
                    collections.remove(position);
                    notifyItemRemoved(position);
                    collections.add(newName);
                    notifyItemChanged(position);
                    alertDialog1.dismiss();
                });
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

    public final class CollectionViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.TextCollection);
        }
    }
}

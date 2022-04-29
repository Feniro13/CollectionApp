package com.Feniro.collectionapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.R;
import com.Feniro.collectionapp.database.LocalDatabase;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

import java.util.List;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder> {
    List<DatabaseLocalEntities> listOfItems;
    int numberOfColumns;
    Context context;
    ViewGroup viewGroup;
    LocalDatabase localDatabase;
    ItemAddAdapter itemAddAdapter;

    public ItemViewAdapter(List<DatabaseLocalEntities> listOfItems, int numberOfColumns, Context context, ViewGroup viewGroup) {
        this.listOfItems = listOfItems;
        this.numberOfColumns = numberOfColumns;
        this.context = context;
        this.viewGroup = viewGroup;
    }

    @NonNull
    @Override
    public ItemViewAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_view_item, parent, false);
        return new ItemViewAdapter.ItemViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewAdapter.ItemViewHolder holder, int position) {
        if(position == 0) {

        }
        int kol = 0;
        holder.button.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view_item_action, viewGroup, false);

            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.show();

            int id = listOfItems.get(position).id;
            Button edit = dialogView.findViewById(R.id.dialog_item_view_button_edit);
            Button back = dialogView.findViewById(R.id.dialog_item_view_button_back);
            Button delete = dialogView.findViewById(R.id.dialog_item_view_button_delete);

            edit.setOnClickListener(view1 -> {
                View dialogViewEdit = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view_add_item, viewGroup, false);
                builder.setView(dialogViewEdit);
                builder.show();

                RecyclerView recyclerView = dialogViewEdit.findViewById(R.id.dialog_add_item_recyclerview);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);

                itemAddAdapter = new ItemAddAdapter(context,listOfItems.get(0), listOfItems.get(position), numberOfColumns);

            });

            back.setOnClickListener(view1 -> alertDialog.dismiss());

            delete.setOnClickListener(view1 -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Вы уверены?");
                builder1.setPositiveButton("Да", (dialogInterface, i) -> {
                    localDatabase.dao().deleteEntityById(id);
                    listOfItems.remove(position);
                    alertDialog.dismiss();
                });
                builder1.setNegativeButton("Назад", (dialogInterface, i) -> {
                }); });
        });

        {
            holder.textView1.setText(listOfItems.get(position).column1);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView2.setText(listOfItems.get(position).column2);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView3.setText(listOfItems.get(position).column3);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView4.setText(listOfItems.get(position).column4);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView5.setText(listOfItems.get(position).column5);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView6.setText(listOfItems.get(position).column6);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView7.setText(listOfItems.get(position).column7);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView8.setText(listOfItems.get(position).column8);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView9.setText(listOfItems.get(position).column9);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView10.setText(listOfItems.get(position).column10);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView11.setText(listOfItems.get(position).column11);
            kol++;
            if (kol == numberOfColumns) {
                return;
            }
            holder.textView12.setText(listOfItems.get(position).column12);
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
        TextView textView8;
        TextView textView9;
        TextView textView10;
        TextView textView11;
        TextView textView12;
        Button button;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.item_View_1);
            textView2 = itemView.findViewById(R.id.item_View_2);
            textView3 = itemView.findViewById(R.id.item_View_3);
            textView4 = itemView.findViewById(R.id.item_View_4);
            textView5 = itemView.findViewById(R.id.item_View_5);
            textView6 = itemView.findViewById(R.id.item_View_6);
            textView7 = itemView.findViewById(R.id.item_View_7);
            textView8 = itemView.findViewById(R.id.item_View_8);
            textView9 = itemView.findViewById(R.id.item_View_10);
            textView11 = itemView.findViewById(R.id.item_View_11);
            textView12 = itemView.findViewById(R.id.item_View_12);
            button = itemView.findViewById(R.id.item_view_clicker);
        }

    }
}

package com.Feniro.collectionapp.adapter;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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

    RecyclerView itemRecycler;
    DatabaseLocalEntities mainEntity;
    String name;

    ItemAddAdapter itemAddAdapter;

    public ItemViewAdapter(List<DatabaseLocalEntities> listOfItems, int numberOfColumns, Context context, ViewGroup viewGroup, DatabaseLocalEntities mainEntity, String name) {
        this.listOfItems = listOfItems;
        this.numberOfColumns = numberOfColumns;
        this.context = context;
        this.viewGroup = viewGroup;
        this.mainEntity = mainEntity;
        this.name = name;

        this.localDatabase = LocalDatabase.getDatabase(context);
    }

    @NonNull
    @Override
    public ItemViewAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_view_item, parent, false);
        return new ItemViewAdapter.ItemViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewAdapter.ItemViewHolder holder, int position) {
        int kol = 0;
        holder.textView1.setText(listOfItems.get(position).column1);
        holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.second_main));
        holder.textView1.setOnClickListener(view -> {
            clickItemListener(position, view, listOfItems.get(position).column1);
        });
        if(position == 0) {
            holder.textView1.setBackgroundColor(context.getResources().getColor(R.color.second_main));
        }
        kol++;
        if (kol == numberOfColumns) {
//            holder.textView2.setVisibility(View.GONE);
//            holder.textView3.setVisibility(View.GONE);
//            holder.textView4.setVisibility(View.GONE);
//            holder.textView5.setVisibility(View.GONE);
//            holder.textView6.setVisibility(View.GONE);
//            holder.textView7.setVisibility(View.GONE);
//            holder.textView8.setVisibility(View.GONE);
//            holder.textView9.setVisibility(View.GONE);
//            holder.textView10.setVisibility(View.GONE);
//            holder.textView11.setVisibility(View.GONE);
//            holder.textView12.setVisibility(View.GONE);
              return;
        }
        holder.textView2.setText(listOfItems.get(position).column2);
        holder.textView2.setOnClickListener(view -> {
            clickItemListener(position, view, listOfItems.get(position).column2);
        });
        if(position == 0) {
            holder.textView2.setBackgroundColor(context.getResources().getColor(R.color.second_main));
        }
        kol++;
        if (kol == numberOfColumns) {
        //          holder.textView3.set;
//                holder.textView4.setVisibility(View.GONE);
//                holder.textView5.setVisibility(View.GONE);
//                holder.textView6.setVisibility(View.GONE);
//                holder.textView7.setVisibility(View.GONE);
//                holder.textView8.setVisibility(View.GONE);
//                holder.textView9.setVisibility(View.GONE);
//                holder.textView10.setVisibility(View.GONE);
//                holder.textView11.setVisibility(View.GONE);
//                holder.textView12.setVisibility(View.GONE);
//                return;
        }
        holder.textView3.setText(listOfItems.get(position).column3);
        holder.textView3.setOnClickListener(view -> {
            clickItemListener(position, view, listOfItems.get(position).column3);
        });
        if(position == 0) {
            holder.textView3.setBackgroundColor(context.getResources().getColor(R.color.second_main));
        }
        kol++;
//            if (kol == numberOfColumns) {
//                holder.textView4.setVisibility(View.GONE);
//                holder.textView5.setVisibility(View.GONE);
//                holder.textView6.setVisibility(View.GONE);
//                holder.textView7.setVisibility(View.GONE);
//                holder.textView8.setVisibility(View.GONE);
//                holder.textView9.setVisibility(View.GONE);
//                holder.textView10.setVisibility(View.GONE);
//                holder.textView11.setVisibility(View.GONE);
//                holder.textView12.setVisibility(View.GONE);
//                return;
//            }
//            holder.textView4.setText(listOfItems.get(position).column4);
//            kol++;
//            if (kol == numberOfColumns) {
//                holder.textView5.setWidth(0);
//                holder.textView6.setWidth(0);
//                holder.textView7.setWidth(0);
//                holder.textView8.setWidth(0);
//                holder.textView9.setWidth(0);
//                holder.textView10.setWidth(0);
//                holder.textView11.setWidth(0);
//                holder.textView12.setWidth(0);
//                return;
//            }
//            holder.textView5.setText(listOfItems.get(position).column5);
//            kol++;
//            if (kol == numberOfColumns) {
//                holder.textView6.setWidth(0);
//                holder.textView7.setWidth(0);
//                holder.textView8.setWidth(0);
//                holder.textView9.setWidth(0);
//                holder.textView10.setWidth(0);
//                holder.textView11.setWidth(0);
//                holder.textView12.setWidth(0);
//                return;
//            }
//            holder.textView6.setText(listOfItems.get(position).column6);
//            kol++;
//            if (kol == numberOfColumns) {
//                holder.textView7.setWidth(0);
//                holder.textView8.setWidth(0);
//                holder.textView9.setWidth(0);
//                holder.textView10.setWidth(0);
//                holder.textView11.setWidth(0);
//                holder.textView12.setWidth(0);
//                return;
//            }
//            holder.textView7.setText(listOfItems.get(position).column7);
//            kol++;
//            if (kol == numberOfColumns) {
//                holder.textView8.setWidth(0);
//                holder.textView9.setWidth(0);
//                holder.textView10.setWidth(0);
//                holder.textView11.setWidth(0);
//                holder.textView12.setWidth(0);
//                return;
//            }
//            holder.textView8.setText(listOfItems.get(position).column8);
//            kol++;
//            if (kol == numberOfColumns) {
//                holder.textView9.setWidth(0);
//                holder.textView10.setWidth(0);
//                holder.textView11.setWidth(0);
//                holder.textView12.setWidth(0);
//                return;
//            }
//            holder.textView9.setText(listOfItems.get(position).column9);
//            kol++;
//            if (kol == numberOfColumns) {
//                holder.textView10.setWidth(0);
//                holder.textView11.setWidth(0);
//                holder.textView12.setWidth(0);
//                return;
//            }
//            holder.textView10.setText(listOfItems.get(position).column10);
//            kol++;
//            if (kol == numberOfColumns) {
//                holder.textView11.setWidth(0);
//                holder.textView12.setWidth(0);
//                return;
//            }
//            holder.textView11.setText(listOfItems.get(position).column11);
//            kol++;
//            if (kol == numberOfColumns) {
//                holder.textView12.setWidth(0);
//                return;
//            }
//            holder.textView12.setText(listOfItems.get(position).column12);
        }

    @Override
    public int getItemCount() {
        return listOfItems.size();
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
        ConstraintLayout constraintLayout;

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
            textView9 = itemView.findViewById(R.id.item_View_9);
            textView10 = itemView.findViewById(R.id.item_View_10);
            textView11 = itemView.findViewById(R.id.item_View_11);
            textView12 = itemView.findViewById(R.id.item_View_12);
            constraintLayout = itemView.findViewById(R.id.holderLayout);
        }

    }

    public DatabaseLocalEntities getEntityFromAdapter(DatabaseLocalEntities localEntity){
        List<String> names = itemAddAdapter.entityList;
        int kol = 1;
        localEntity.column1 = names.get(0);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column2 = names.get(1);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column3 = names.get(2);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column4 = names.get(3);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column5 = names.get(4);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column6 = names.get(5);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column7 = names.get(6);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column8 = names.get(7);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column9 = names.get(8);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column10 = names.get(9);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column11 = names.get(10);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        localEntity.column12 = names.get(11);
        return localEntity;
    }

    public void setItemDialogRecycler(View view, DatabaseLocalEntities mainEntity, DatabaseLocalEntities localEntity) {
        itemRecycler = view.findViewById(R.id.dialog_add_item_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        itemRecycler.setLayoutManager(layoutManager);
        itemAddAdapter = new ItemAddAdapter(context, mainEntity, localEntity, numberOfColumns);
        itemRecycler.setAdapter(itemAddAdapter);
    }

    public void clickItemListener(int position, View view, String item) {
            if(position == 0) {
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view_action, viewGroup, false);
            builder.setView(dialogView);

            final AlertDialog alertDialog = builder.show();

            alertDialog.getWindow().setLayout((int) (180 * Resources.getSystem().getDisplayMetrics().density),
                    (int) (290 * Resources.getSystem().getDisplayMetrics().density));
            alertDialog.show();

            int id = listOfItems.get(position).id;
            Button edit = dialogView.findViewById(R.id.dialog_item_view_button_edit);
            Button back = dialogView.findViewById(R.id.dialog_item_view_button_back);
            Button delete = dialogView.findViewById(R.id.dialog_item_view_button_delete);
            Button search = dialogView.findViewById(R.id.dialog_item_view_search);

            search.setOnClickListener(view12 -> {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, item);
                context.startActivity(intent);
            });

            edit.setOnClickListener(view1 -> {
                View view11 = LayoutInflater.from(view1.getContext()).inflate(R.layout.dialog_view_add, viewGroup, false);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setView(view11);

                alertDialog.dismiss();

                final AlertDialog alertDialog1 = builder1.show();


                Button itemAdd = view11.findViewById(R.id.dialog_add_item_button_add);
                itemAdd.setText("Сохранить");
                Button itemBack = view11.findViewById(R.id.dialog_add_item_button_back);
                TextView warning = view11.findViewById(R.id.dialog_add_item_textview_warning);
                warning.setText(name);

                DatabaseLocalEntities localEntity = new DatabaseLocalEntities();
                localEntity.isFirstLine = 0;
                localEntity.DatabaseName = name;

                DatabaseLocalEntities thisEntity = listOfItems.get(position);

                setItemDialogRecycler(view11, mainEntity, thisEntity);

                itemBack.setOnClickListener(view2 -> alertDialog1.dismiss());

                itemAdd.setOnClickListener(view22 -> {
                    localDatabase.dao().insert(getEntityFromAdapter(localEntity));
                    localDatabase.dao().delete(thisEntity);
                    notifyItemChanged(position);
                    alertDialog1.dismiss();
                });

                alertDialog1.show();
            });

            back.setOnClickListener(view1 -> alertDialog.dismiss());

            delete.setOnClickListener(view1 -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.show();
                builder1.setTitle("Вы уверены?");
                builder1.setPositiveButton("Да", (dialogInterface, i) -> {
                    localDatabase.dao().deleteEntityById(id);
                    listOfItems.remove(position);
                    notifyItemRemoved(position);
                    alertDialog.dismiss();
                });
                builder1.setNegativeButton("Назад", (dialogInterface, i) -> {
                }); });
    }
}

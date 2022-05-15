package com.Feniro.collectionapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.R;
import com.Feniro.collectionapp.database.LocalDatabase;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

import java.util.List;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder> {
    public interface onItemClickListener{
        void ItemClickListener(int position, View view, String item, int column);
    }
    public interface onItemSortClickListener{
        void ItemSortClickListener(int columnNumber);
    }
    private final onItemClickListener onItemClickListener;
    private final onItemSortClickListener onItemSortClickListener;

    List<DatabaseLocalEntities> listOfItems;
    int numberOfColumns;
    Context context;
    ViewGroup viewGroup;
    LocalDatabase localDatabase;
    String name;
    public List<Integer> globalPositions;

    public ItemViewAdapter(List<DatabaseLocalEntities> listOfItems, int numberOfColumns, Context context, ViewGroup viewGroup, String name, onItemClickListener onItemClickListener, onItemSortClickListener onItemSortClickListener, List<Integer> globalPositions) {
        this.listOfItems = listOfItems;
        this.numberOfColumns = numberOfColumns;
        this.context = context;
        this.viewGroup = viewGroup;
        this.name = name;
        this.localDatabase = LocalDatabase.getDatabase(context);
        this.globalPositions = globalPositions;
        this.onItemClickListener = onItemClickListener;
        this.onItemSortClickListener = onItemSortClickListener;
    }

    @NonNull
    @Override
    public ItemViewAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_view_item, parent, false);
        return new ItemViewAdapter.ItemViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewAdapter.ItemViewHolder holder, int position) {
        if(globalPositions.get(position) == 1){
            holder.textView1.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView2.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView3.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView4.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView5.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView6.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView7.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView8.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView9.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView10.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView11.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView12.setBackgroundColor(context.getResources().getColor(R.color.second_main));
        }
        if(globalPositions.get(position) == 0){
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.black));
            holder.textView1.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView2.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView3.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView4.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView5.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView6.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView7.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView8.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView9.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView10.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView11.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.textView12.setBackgroundColor(context.getResources().getColor(R.color.main));
        }
        if(position == 0) {
            holder.textView1.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView2.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView3.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView4.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView5.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView6.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView7.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView8.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView9.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView10.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView11.setBackgroundColor(context.getResources().getColor(R.color.second_main));
            holder.textView12.setBackgroundColor(context.getResources().getColor(R.color.second_main));
        }
        int kol = 0;

        holder.textView1.setText(listOfItems.get(position).column1);
        holder.textView1.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(0);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column1, 0);
            }
        });
        kol++;
        if (kol == numberOfColumns) {
              return;
        }

        holder.textView2.setText(listOfItems.get(position).column2);
        holder.textView2.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(1);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column2, 1);
            }
        });
        kol++;
        if (kol == numberOfColumns) { return;
        }

        holder.textView3.setText(listOfItems.get(position).column3);
        holder.textView3.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(2);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column3, 2);
            }
        });
        kol++;
        if (kol == numberOfColumns) {
            return;
        }

        holder.textView4.setText(listOfItems.get(position).column4);
        holder.textView4.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(3);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column4, 3);
            }
        });
        kol++;
        if (kol == numberOfColumns) {
            return;
        }

        holder.textView5.setText(listOfItems.get(position).column5);
        holder.textView5.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(4);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column5, 4);
            }        });
        kol++;
        if (kol == numberOfColumns) {
            return;
        }

        holder.textView6.setText(listOfItems.get(position).column6);
        holder.textView6.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(5);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column6, 5);
            }        });
        kol++;
        if (kol == numberOfColumns) {
            return;
        }

        holder.textView7.setText(listOfItems.get(position).column7);
        holder.textView7.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(6);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column7, 6);
            }        });
        kol++;
        if (kol == numberOfColumns) {
            return;
        }

        holder.textView8.setText(listOfItems.get(position).column8);
        holder.textView8.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(7);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column8, 7);
            }        });
        kol++;
        if (kol == numberOfColumns) {
            return;
        }

        holder.textView9.setText(listOfItems.get(position).column9);
        holder.textView9.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(8);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column9, 8);
            }        });
        kol++;
        if (kol == numberOfColumns) {
            return;
        }

        holder.textView10.setText(listOfItems.get(position).column10);
        holder.textView10.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(9);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column10, 9);
            }        });
        kol++;
        if (kol == numberOfColumns) {
            return;
        }

        holder.textView11.setText(listOfItems.get(position).column11);
        holder.textView11.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(10);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column11, 10);
            }        });
        kol++;
        if (kol == numberOfColumns) {
            return;
        }

        holder.textView12.setText(listOfItems.get(position).column12);
        holder.textView12.setOnClickListener(view -> {
            if(position == 0){
                onItemSortClickListener.ItemSortClickListener(11);
            } else {
                onItemClickListener.ItemClickListener(position, view, listOfItems.get(position).column12, 11);
            }        });
        if(position == 0) {
            holder.textView12.setBackgroundColor(context.getResources().getColor(R.color.second_main));
        }
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
            if(numberOfColumns == 1) {
                constraintLayout.setMaxWidth(330);
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
                textView8.setVisibility(View.GONE);
                textView9.setVisibility(View.GONE);
                textView10.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView12.setVisibility(View.GONE);
            }
            if(numberOfColumns == 2) {
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
                textView8.setVisibility(View.GONE);
                textView9.setVisibility(View.GONE);
                textView10.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView12.setVisibility(View.GONE);
            }
            if(numberOfColumns == 3) {
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
                textView8.setVisibility(View.GONE);
                textView9.setVisibility(View.GONE);
                textView10.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView12.setVisibility(View.GONE);
            }
            if(numberOfColumns == 4) {
                textView5.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
                textView8.setVisibility(View.GONE);
                textView9.setVisibility(View.GONE);
                textView10.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView12.setVisibility(View.GONE);
            }
            if(numberOfColumns == 5) {
                textView6.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
                textView8.setVisibility(View.GONE);
                textView9.setVisibility(View.GONE);
                textView10.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView12.setVisibility(View.GONE);
            }
            if(numberOfColumns == 6) {
                textView7.setVisibility(View.GONE);
                textView8.setVisibility(View.GONE);
                textView9.setVisibility(View.GONE);
                textView10.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView12.setVisibility(View.GONE);
            }
            if(numberOfColumns == 7) {
                textView8.setVisibility(View.GONE);
                textView9.setVisibility(View.GONE);
                textView10.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView12.setVisibility(View.GONE);
            }
            if(numberOfColumns == 8) {
                textView9.setVisibility(View.GONE);
                textView10.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView12.setVisibility(View.GONE);
            }
            if(numberOfColumns == 9) {
                textView10.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView12.setVisibility(View.GONE);
            }
            if(numberOfColumns == 10) {
                textView11.setVisibility(View.GONE);
                textView12.setVisibility(View.GONE);
            }
            if(numberOfColumns == 11) {
                textView12.setVisibility(View.GONE);
            }
        }
    }
}

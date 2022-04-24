package com.Feniro.collectionapp.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Feniro.collectionapp.R;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

import java.util.ArrayList;
import java.util.List;

public class ItemAddAdapter extends RecyclerView.Adapter<ItemAddAdapter.ItemAddViewHolder> {
    Context context;
    DatabaseLocalEntities entity;
    DatabaseLocalEntities namesOfColumns;
    int numberOfColumns;

    List<String> namesOfColumnsList;
    public List<String> entityList;

    public ItemAddAdapter(Context context, DatabaseLocalEntities namesOfColumns, DatabaseLocalEntities list, int numberOfColumns) {
        this.context = context;
        this.entity = list;
        this.numberOfColumns = numberOfColumns;
        this.namesOfColumns = namesOfColumns;
    }


    @NonNull
    @Override
    public ItemAddAdapter.ItemAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_add_to_collection, parent, false);
        return new ItemAddAdapter.ItemAddViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAddAdapter.ItemAddViewHolder holder, int position) {
        namesOfColumnsList = ConverterToList(namesOfColumns, numberOfColumns);
        entityList = ConverterToList(entity, numberOfColumns);
        holder.title.setText(namesOfColumnsList.get(position));
        if(!entityList.get(position).equals("")) {
            holder.editText.setText(entityList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public final class ItemAddViewHolder extends RecyclerView.ViewHolder {

        EditText editText;
        TextView title;

        public ItemAddViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Item_add_to_collection_textView);
            editText = itemView.findViewById(R.id.Item_add_to_collection_edittext);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    entityList.set(getAdapterPosition(), editText.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }
    List<String> ConverterToList(DatabaseLocalEntities entity, int numberOfColumns) {
        List<String> returnList = new ArrayList<>();
        int kol = 1;
        returnList.add(entity.column1);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column2);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column3);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column4);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column5);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column6);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column7);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column8);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column9);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column10);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column11);
        if(kol + 1 == numberOfColumns) {
            return  returnList;
        }
        kol++;
        returnList.add(entity.column12);
        return  returnList;
    }
}

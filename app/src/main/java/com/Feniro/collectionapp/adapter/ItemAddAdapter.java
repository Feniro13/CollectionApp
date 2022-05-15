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
    int numberOfColumns;

    List<String> namesOfColumnsList;
    public List<String> entityList;

    public ItemAddAdapter(Context context, DatabaseLocalEntities namesOfColumns, DatabaseLocalEntities entity, int numberOfColumns) {
        this.context = context;
        this.numberOfColumns = numberOfColumns;
        this.namesOfColumnsList = ConverterToList(namesOfColumns, numberOfColumns);
        this.entityList = ConverterToList(entity, numberOfColumns);
    }


    @NonNull
    @Override
    public ItemAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.item_view_add_item, parent, false);
        return new ItemAddViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAddAdapter.ItemAddViewHolder holder, int position) {
        holder.title.setText(namesOfColumnsList.get(position));
        if(!entityList.get(position).equals(null)) {
            holder.editText.setText(entityList.get(position));
        }
        else {
            holder.editText.setHint(namesOfColumnsList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return namesOfColumnsList.size();
    }

    public final class ItemAddViewHolder extends RecyclerView.ViewHolder {

        TextView editText;
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
        if(entity.column1 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column1); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column2 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column2); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column3 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column3); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column4 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column4); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column5 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column5); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column6 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column6); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column7 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column7); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column8 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column8); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column9 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column9); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column10 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column10); }
        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column11 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column11); }
        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column12 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column12); }
        return  returnList;
    }
}

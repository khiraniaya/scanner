package com.example.scandb.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scandb.Model.ListItem;
import com.example.scandb.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>{

    List<ListItem> listItemsArrayList;
    Context context;

    public MyAdapter(List<ListItem>listItemsArrayList, Context context){
        this.listItemsArrayList = listItemsArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new MyAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {
        ListItem listItem = listItemsArrayList.get(position);
        holder.textViewType.setText(listItem.getType());
        holder.textViewCode.setText(listItem.getCode());
        Linkify.addLinks(holder.textViewCode,Linkify.ALL);
    }

    @Override
    public int getItemCount() {
        return listItemsArrayList.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView textViewCode,textViewType;
        CardView cardView;

        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCode = itemView.findViewById(R.id.textViewCode);
            textViewType = itemView.findViewById(R.id.textViewType);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String type = listItemsArrayList.get(getAdapterPosition()).getType();
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_SEND);
                    i.putExtra(Intent.EXTRA_TEXT,type);
                    i.setType("text/plain");
                    itemView.getContext().startActivity(i);
                }

            });

        }
    }

}

package com.example.kkanbu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context mContext;
    ArrayList<item> items = new ArrayList<item>();

    public Adapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        item item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void additem(item item) {
        items.add(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        TextView gender;
        TextView ph;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            gender = itemView.findViewById(R.id.gender);
            ph = itemView.findViewById(R.id.ph);
        }

        public void setItem(item item){
            imageView.setImageResource(item.resId);
            name.setText(item.name);
            gender.setText(item.gender);
            ph.setText(item.ph);
        }
    }
}

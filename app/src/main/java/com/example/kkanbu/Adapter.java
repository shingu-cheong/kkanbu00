package com.example.kkanbu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kkanbu.pojo.Olderman;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.OldermanViewHolder> {
    Context context;
    List<Olderman> oldermanArrayList;

    public Adapter(Context context, List<Olderman> oldermanArrayList){
        this.context = context;
        this.oldermanArrayList = oldermanArrayList;
    }


    @NonNull
    @Override
    public Adapter.OldermanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new OldermanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.OldermanViewHolder oldermanViewHolder, int position) {

        oldermanViewHolder.manname.setText(oldermanArrayList.get(position).getManName());
        oldermanViewHolder.mngph.setText(oldermanArrayList.get(position).getManManager());
        oldermanViewHolder.manph.setText(oldermanArrayList.get(position).getManPh());
    }

    @Override
    public int getItemCount() {
        return oldermanArrayList.size();
    }

    public class OldermanViewHolder extends RecyclerView.ViewHolder {
        TextView manname, manph, mngph;
        public OldermanViewHolder(@NonNull View itemView) {
            super(itemView);
            manname = itemView.findViewById(R.id.name);
            manph = itemView.findViewById(R.id.gender);
            mngph = itemView.findViewById(R.id.ph);


        }
    }
}

package com.zamansoftwaresolutions.datamangeusingfirebasesqllite.Firebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zamansoftwaresolutions.datamangeusingfirebasesqllite.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;
    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recName.setText(dataList.get(position).getDataName());
        //holder.recEmail.setText(dataList.get(position).getDataEmail());
        holder.recMobile.setText(dataList.get(position).getDataPhone());

        String name=dataList.get(position).getDataName();
        String email=dataList.get(position).getDataEmail();
        String mobile=dataList.get(position).getDataPhone();
        String image=dataList.get(position).getDataImage();



        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Click for Details", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailActivity.class);

                //Toast.makeText(context, "Click for Details"+dataList.get(position).getDataName(), Toast.LENGTH_SHORT).show();

                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Name", dataList.get(holder.getAdapterPosition()).getDataName());
                intent.putExtra("Mobile", dataList.get(holder.getAdapterPosition()).getDataPhone());
                intent.putExtra("Email", dataList.get(holder.getAdapterPosition()).getDataEmail());
                intent.putExtra("Address", dataList.get(holder.getAdapterPosition()).getDataAddress());
                intent.putExtra("Key", dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        int count=dataList.size();
        return count;

    }
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }

}

class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recName, recEmail, recMobile;
    CardView recCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recCard=itemView.findViewById(R.id.recCard);
        recImage=itemView.findViewById(R.id.recImage);
        recName=itemView.findViewById(R.id.recName);
        recMobile=itemView.findViewById(R.id.recMobile);


    }

}
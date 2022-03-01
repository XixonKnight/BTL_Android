package com.example.appbookingroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbookingroom.R;
import com.example.appbookingroom.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class DataHotelAdapter extends RecyclerView.Adapter<DataHotelAdapter.HotelViewHolder> {
    private List<Hotel> lstHotel = new ArrayList<>();

    public DataHotelAdapter(List<Hotel> lstHotel) {
        this.lstHotel = lstHotel;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel,parent,false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = lstHotel.get(position);
        holder.txtNote.setText("Welcome");
        holder.txtPrice.setText("Welcome");
        holder.txtNameHotel.setText("Welcome");
        holder.imageView.setImageResource(R.drawable.bgd_item_hotel);
    }

    @Override
    public int getItemCount() {
        return lstHotel.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView txtNote,txtNameHotel,txtPrice;
        public HotelViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.img_hotel);
            txtNote = view.findViewById(R.id.txt_note);
            txtPrice = view.findViewById(R.id.price);
            txtNameHotel = view.findViewById(R.id.txt_name_hotel);

        }
    }
}

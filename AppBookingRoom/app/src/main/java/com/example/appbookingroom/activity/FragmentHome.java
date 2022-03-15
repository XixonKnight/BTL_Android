package com.example.appbookingroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbookingroom.R;
import com.example.appbookingroom.adapter.DataHotelAdapter;
import com.example.appbookingroom.config.RecyclerItemClickListener;
import com.example.appbookingroom.model.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {
    private ImageView imageView;
    private RecyclerView lstViewHotel;
    private DataHotelAdapter hotelAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        imageView = (ImageView) view.findViewById(R.id.btn_menu);
        lstViewHotel = (RecyclerView) view.findViewById(R.id.lst_view_hotel);
        List<Hotel> lstHotel = new ArrayList<>();
        lstHotel.add(new Hotel("abc"));
        lstHotel.add(new Hotel("bcd"));
        lstHotel.add(new Hotel("bhf"));
        lstHotel.add(new Hotel("bklj"));
        hotelAdapter = new DataHotelAdapter(lstHotel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        lstViewHotel.setLayoutManager(linearLayoutManager);
        lstViewHotel.setAdapter(hotelAdapter);
        event();
    }

    private void init(){
//        lstViewHotel = find
    }

    private void event(){
        lstViewHotel.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), lstViewHotel, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), ActivityInfoHotel.class));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }
}
package com.example.appbookingroom.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbookingroom.R;
import com.example.appbookingroom.adapter.DataHotelAdapter;
import com.example.appbookingroom.common.CommonUtils;
import com.example.appbookingroom.model.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentSearch extends Fragment {

    private LinearLayout lstBtnTypeRoom;
    private RecyclerView lstHotel;
    private DataHotelAdapter hotelAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lstBtnTypeRoom = view.findViewById(R.id.lstBtnTypeRoom);
        lstHotel = view.findViewById(R.id.lstHotel);
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("abc"));
        hotels.add(new Hotel("bcd"));
        hotels.add(new Hotel("bhf"));
        hotels.add(new Hotel("bklj"));
        hotelAdapter = new DataHotelAdapter(hotels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        lstHotel.setLayoutManager(linearLayoutManager);
        lstHotel.setAdapter(hotelAdapter);
        customLstBtnTypeRoom();
    }

    private void customLstBtnTypeRoom() {
        String[] data = getResources().getStringArray(R.array.search_demo);
        for (String it : data) {
            AppCompatButton btn = CommonUtils.createdButton(getActivity(), "", it);
            lstBtnTypeRoom.addView(btn);
        }
    }
}
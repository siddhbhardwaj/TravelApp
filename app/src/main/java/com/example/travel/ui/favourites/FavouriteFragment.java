package com.example.travel.ui.favourites;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.Adapters.FavAdapter;
import com.example.travel.Helpers.TravelDB;
import com.example.travel.Models.FavItem;
import com.example.travel.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private TravelDB travelDB;
    private List<FavItem> favItemList = new ArrayList<>();
    private FavAdapter favAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favourite, container, false);


        travelDB = new TravelDB(getActivity());
        recyclerView = root.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        
        loadData();
        
        return root;
    }

    private void loadData() {
        if (favItemList != null) {
            favItemList.clear();
        }
        SQLiteDatabase db = travelDB.getReadableDatabase();
        Cursor cursor = travelDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(TravelDB.ITEM_TITLE));
                String id = cursor.getString(cursor.getColumnIndex(TravelDB.KEY_ID));
                int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(TravelDB.ITEM_IMAGE)));
                String countrydesc = cursor.getString(cursor.getColumnIndex(TravelDB.COUNTRY_DESC));
                String currency = cursor.getString(cursor.getColumnIndex(TravelDB.CURRENCY));
                String language =cursor.getString(cursor.getColumnIndex(TravelDB.LANGUAGE));
                String capital = cursor.getString(cursor.getColumnIndex(TravelDB.CAPITAL));
                String other =cursor.getString(cursor.getColumnIndex(TravelDB.OTHER));

                FavItem favItem = new FavItem(title, id, image, countrydesc, currency, language, capital, other);
                favItemList.add(favItem);
            }
        }finally {
            if (cursor !=null && cursor.isClosed())
                cursor.close();
            db.close();
        }

        favAdapter = new FavAdapter(getActivity(), favItemList);

        recyclerView.setAdapter(favAdapter);
    }
}

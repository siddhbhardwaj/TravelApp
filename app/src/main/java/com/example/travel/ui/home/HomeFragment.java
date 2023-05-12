package com.example.travel.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.travel.R;
import com.example.travel.ui.country.CountryFragment;
import com.example.travel.ui.favourites.FavouriteFragment;
import com.example.travel.ui.cities.CitiesFragment;

public class HomeFragment extends Fragment {

    Button b1;
    Button b2;
    Button b3, b4;
    TextView t1,t2,t3;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        b1 = v.findViewById(R.id.button2);
        b2 = v.findViewById(R.id.button3);
        b3 = v.findViewById(R.id.button4);
        b4 = v.findViewById(R.id.button5);
        t1= v.findViewById(R.id.editTextTextPersonName);
        t2= v.findViewById(R.id.editTextTextPersonName2);
        t3= v.findViewById(R.id.textView2);


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int no = Integer.parseInt(t1.getText().toString());
                int cost = Integer.parseInt(t2.getText().toString());
                int val = cost/no;
                String hmm = String.valueOf(val);
                t3.setText("Cost per person is " +hmm);

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new CountryFragment());
                fr.commit();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new FavouriteFragment());
                fr.commit();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new CitiesFragment());
                fr.commit();



            }
        });

        return v;
    }
}

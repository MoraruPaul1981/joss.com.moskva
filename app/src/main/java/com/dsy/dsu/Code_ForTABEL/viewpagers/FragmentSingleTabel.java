package com.dsy.dsu.Code_ForTABEL.viewpagers;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsy.dsu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSingleTabel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSingleTabel extends Fragment {
    // TODO: Rename and change types and number of parameters
    public static FragmentSingleTabel newInstance(@NonNull Bundle   bundleNewViewPager,
                                                  @NonNull int value,
                                                  @NonNull Cursor cursor) {
        FragmentSingleTabel fragment = new FragmentSingleTabel();
        bundleNewViewPager.putInt("positionViewPager",value);
        fragment.setArguments(bundleNewViewPager);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_tabel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Long    MainParentUUID=    getArguments().getLong("MainParentUUID", 0l);
        Integer   PositionCustomer=    getArguments().getInt("Position", 0);

        TextView textfragnetviewpager = (TextView)  view.findViewById(R.id.textfragnetviewpager);
        textfragnetviewpager.setText(MainParentUUID.toString());
        // TODO: 20.06.2023
        TextView textfragnetviewpager2 = (TextView)  view.findViewById(R.id.textfragnetviewpager2);
        textfragnetviewpager2.setText(PositionCustomer.toString());
    }
}
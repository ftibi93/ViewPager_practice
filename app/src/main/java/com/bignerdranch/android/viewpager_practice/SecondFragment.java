package com.bignerdranch.android.viewpager_practice;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    private static TextView mTextView;
    private static Button mButton;
    private static TextView mNameTextView;
    private Button mDeleteButton;

    private static People mPeople;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_second_fragment, container, false);

        mTextView = (TextView) v.findViewById(R.id.text_view_second_fragment);
        mTextView.setText("Második fragment");

        mButton = (Button) v.findViewById(R.id.button_second_fragment);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.changePage(0);
            }
        });

        mNameTextView = (TextView) v.findViewById(R.id.text_view_name_second_fragment);

        mDeleteButton = (Button) v.findViewById(R.id.delete_second_fragment);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPeople != null) {
                    PeopleLab.get(getContext()).removePeople(mPeople.getId());
                    StoredId.setUUID(null);
                    PeopleLab.get(getContext());
                }
            }
        });

        return v;
    }

    public static Fragment newInstance() {
        return new SecondFragment();
    }

    public static void changeText(String text) {
        mTextView.setText(text);
    }

    public static void changeData(Context context) {
        if(StoredId.getUUID() != null) {
            mPeople = PeopleLab.get(context).getPeople(StoredId.getUUID());
            mNameTextView.setText(mPeople.getName());
        }
    }

}

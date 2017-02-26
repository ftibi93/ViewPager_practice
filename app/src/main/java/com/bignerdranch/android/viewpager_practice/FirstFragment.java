package com.bignerdranch.android.viewpager_practice;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FirstFragment extends Fragment {

    private TextView mTextView;
    private Button mButton;
    private EditText mEditText;
    private Button mChangeButton;

    private RecyclerView mRecyclerView;
    private PeopleAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceBundle) {
        super.onCreateView(inflater, container, savedInstanceBundle);

        View v = inflater.inflate(R.layout.activity_first_fragment, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTextView = (TextView) v.findViewById(R.id.text_view_first_fragment);
        mTextView.setText("Első fragment");

        mButton = (Button) v.findViewById(R.id.button_first_fragment);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
            }
        });

        mEditText = (EditText) v.findViewById(R.id.edit_text_first_fragment);
        mChangeButton = (Button) v.findViewById(R.id.change_button);

        mChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondFragment.changeText(mEditText.getText().toString());

                People mPeople = new People();
                mPeople.setName(mEditText.getText().toString());
                PeopleLab.get(getContext()).addPeople(mPeople);

                updateUI();

                Toast.makeText(getActivity(), "People has been added to the list", Toast.LENGTH_SHORT).show();
            }
        });

        updateUI();

        return v;
    }

    private void updateUI() {
        PeopleLab peopleLab = PeopleLab.get(getActivity());
        List<People> peoples = peopleLab.getPeople();

        if(mAdapter == null) {
            mAdapter = new PeopleAdapter(peoples);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            //mAdapter.notifyDataSetChanged();
            mAdapter.changeDataSet(peoples);
        }
    }

    private class PeopleHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mNameTextView;
        private TextView mIdTextView;

        private People mPeople;

        public PeopleHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_name);
            mIdTextView = (TextView) itemView.findViewById(R.id.list_item_id);
        }

        public void bindPeople(People people) {
            mPeople = people;
            mNameTextView.setText(mPeople.getName());
            mIdTextView.setText(mPeople.getId().toString());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "asd", Toast.LENGTH_SHORT).show();
            StoredId.setUUID(mPeople.getId());
        }
    }

    private class PeopleAdapter extends RecyclerView.Adapter<PeopleHolder> {
        private List<People> mPeoples;

        public PeopleAdapter(List<People> peoples) {
            mPeoples = peoples;
        }

        @Override
        public PeopleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item, parent, false);
            return new PeopleHolder(view);
        }

        @Override
        public void onBindViewHolder(PeopleHolder holder, int position) {
            People people = mPeoples.get(position);
            holder.bindPeople(people);
        }

        @Override
        public int getItemCount() {
            return mPeoples.size();
        }

        //stack-rol
        public void changeDataSet(List<People> peoples) {
            this.mPeoples = peoples;
            notifyDataSetChanged();
        }
    }

    public static Fragment newInstance() {
        return new FirstFragment();
    }

}

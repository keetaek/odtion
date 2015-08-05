package com.odition.odition.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.odition.odition.R;
import com.odition.odition.model.Audition;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @Bind(R.id.txt_greetings)
    TextView greetingsTextView;

    @Bind(R.id.lst_auditions)
    ListView auditionsListView;
    private ParseQueryAdapter<Audition> audtionQueryAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        audtionQueryAdapter = new ParseQueryAdapter<Audition>(getActivity(), Audition.class) {
            @Override
            public View getItemView(Audition audition, View convertView, ViewGroup parent) {
                View rowView = convertView;

                // reuse view
                if (rowView == null) {
                    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                    rowView = layoutInflater.inflate(R.layout.list_item_audition, parent, false);

                    // configure view holder
                    AuditionViewHolder viewHolder = new AuditionViewHolder();
                    viewHolder.titleTextView = (TextView) rowView.findViewById(R.id.txt_title);
                    viewHolder.descriptionTextView = (TextView) rowView.findViewById(R.id.txt_description);
                    viewHolder.imageView = (ImageView) rowView.findViewById(R.id.image);
                    rowView.setTag(viewHolder);
                }

                AuditionViewHolder viewHolder = (AuditionViewHolder) rowView.getTag();
                viewHolder.titleTextView.setText(audition.getTitle());
                viewHolder.descriptionTextView.setText(audition.getSummary());

                return rowView;
            }

            class AuditionViewHolder {
                public TextView titleTextView;
                public TextView descriptionTextView;
                public ImageView imageView;
            }

        };
        auditionsListView.setAdapter(audtionQueryAdapter);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            greetingsTextView.setText("Hello, " + user.getUsername());
        } else {
            greetingsTextView.setText("Hello, stranger");
        }
    }


}

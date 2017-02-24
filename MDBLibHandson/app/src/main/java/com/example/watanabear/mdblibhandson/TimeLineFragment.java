package com.example.watanabear.mdblibhandson;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.watanabear.mdblibhandson.model.Tweet;
import com.example.watanabear.mdblibhandson.repository.TweetRealmRepository;
import com.example.watanabear.mdblibhandson.repository.TweetRepository;

import java.util.Arrays;

public class TimeLineFragment extends ListFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TimeLineFragment() {
        // Required empty public constructor
    }

    public static TimeLineFragment newInstance(String param1, String param2) {
        TimeLineFragment fragment = new TimeLineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TweetRepository repository = new TweetRealmRepository();

        LayoutInflater inflater = LayoutInflater.from(getContext());
        final ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(
                getContext(),R.layout.list_item_tweet){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final Tweet tweet = getItem(position);

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.list_item_tweet, parent, false);
                }
                ((TextView) convertView.findViewById(R.id.screen_name)).setText(tweet.getScreenName());
                ((TextView) convertView.findViewById(R.id.text)).setText(tweet.getText());

                return convertView;
            }
        };
        repository.loadTimeLine().subscribe(result -> {
            setListAdapter(adapter);
        }, throwable -> {
            Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
        });
    }
}

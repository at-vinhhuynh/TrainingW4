package com.example.vinh.trainingw4.mon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vinh.trainingw4.R;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/25/2017.
 */
public class OnePieceFragment extends Fragment {

    public static OnePieceFragment getInstance(String url) {
        OnePieceFragment onePieceFragment = new OnePieceFragment();
        Bundle args = new Bundle();
        args.putString("URL", url);
        onePieceFragment.setArguments(args);
        return onePieceFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onpiece, container, false);
        ImageView imgOnePiece = (ImageView) view.findViewById(R.id.imgOnePiece);
        Glide.with(getContext()).load(getArguments().getString("URL")).into(imgOnePiece);
        return view;
    }
}

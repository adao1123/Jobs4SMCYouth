package com.example.jobs4smcyouth.Fragments.Application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobs4smcyouth.R;

/**
 * Created by samsiu on 9/3/16.
 */
public class ApplicationRulesFragment extends Fragment {

    public ApplicationRulesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application_detail, container, false);
        return view;
    }
}


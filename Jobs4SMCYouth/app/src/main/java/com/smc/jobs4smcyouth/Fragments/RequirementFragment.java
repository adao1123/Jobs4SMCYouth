package com.smc.jobs4smcyouth.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smc.jobs4smcyouth.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequirementFragment extends Fragment {
    Button laborLawsButton;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    LaborLawsWebviewFragment laborLawsWebviewFragment;



    public RequirementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_requirement, container, false);
        laborLawsButton = (Button) v.findViewById(R.id.requirements_lawButton_id);
        laborLawsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laborLawsWebviewFragment = new LaborLawsWebviewFragment();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_id, laborLawsWebviewFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;
    }
}

package com.example.jobs4smcyouth.Fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.jobs4smcyouth.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipFragment extends Fragment {
    private CardView tip1Card;
    private CardView tip2Card;
    private CardView tip3Card;
    private CardView tip4Card;
    private CardView tip5Card;
    private CardView tip6Card;
    private CardView tip7Card;
    private CardView tip8Card;
    private CardView tip9Card;
    private CardView tip10Card;
    private TextView tip1Detail;
    private TextView tip2Detail;
    private TextView tip3Detail;
    private TextView tip4Detail;
    private TextView tip5Detail;
    private TextView tip6Detail;
    private TextView tip7Detail;
    private TextView tip8Detail;
    private TextView tip9Detail;
    private TextView tip10Detail;
    private Animation slideUp;
    private Animation slideDown;

    public TipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tip, container, false);
        initiateViews(v);
        loadAnimation();
        expandDetails();
        return v;
    }

    private void initiateViews(View v){
        tip1Card = (CardView) v.findViewById(R.id.tip_tipCard_rules);
        tip2Card = (CardView) v.findViewById(R.id.tip_tipCard_rules2);
        tip3Card = (CardView) v.findViewById(R.id.tip_tipCard_rules3);
        tip4Card = (CardView) v.findViewById(R.id.tip_tipCard_rules4);
        tip5Card = (CardView) v.findViewById(R.id.tip_tipCard_rules5);
        tip6Card = (CardView) v.findViewById(R.id.tip_tipCard_rules6);
        tip7Card = (CardView) v.findViewById(R.id.tip_tipCard_rules7);
        tip8Card = (CardView) v.findViewById(R.id.tip_tipCard_rules8);
        tip9Card = (CardView) v.findViewById(R.id.tip_tipCard_rules9);
        tip10Card = (CardView) v.findViewById(R.id.tip_tipCard_rules10);
        tip1Detail = (TextView) v.findViewById(R.id.tip_rule1_detail_tv_id);
        tip2Detail = (TextView) v.findViewById(R.id.tip_rule2_detail_tv_id);
        tip3Detail = (TextView) v.findViewById(R.id.tip_rule3_detail_tv_id);
        tip4Detail = (TextView) v.findViewById(R.id.tip_rule4_detail_tv_id);
        tip5Detail = (TextView) v.findViewById(R.id.tip_rule5_detail_tv_id);
        tip6Detail = (TextView) v.findViewById(R.id.tip_rule6_detail_tv_id);
        tip7Detail = (TextView) v.findViewById(R.id.tip_rule7_detail_tv_id);
        tip8Detail = (TextView) v.findViewById(R.id.tip_rule8_detail_tv_id);
        tip9Detail = (TextView) v.findViewById(R.id.tip_rule9_detail_tv_id);
        tip10Detail = (TextView) v.findViewById(R.id.tip_rule10_detail_tv_id);
        tip1Detail.setVisibility(View.GONE);
        tip2Detail.setVisibility(View.GONE);
        tip3Detail.setVisibility(View.GONE);
        tip4Detail.setVisibility(View.GONE);
        tip5Detail.setVisibility(View.GONE);
        tip6Detail.setVisibility(View.GONE);
        tip7Detail.setVisibility(View.GONE);
        tip8Detail.setVisibility(View.GONE);
        tip9Detail.setVisibility(View.GONE);
        tip10Detail.setVisibility(View.GONE);
    }

    private void loadAnimation(){
        slideUp = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_up);

        slideDown = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_down);

    }

    private void expandDetails(){
        tip1Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tip1Detail.getVisibility() == View.GONE){
                    tip1Detail.setVisibility(View.VISIBLE);
                } else{
                    tip1Detail.setVisibility(View.GONE);
                }
            }
        });

        tip2Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tip2Detail.getVisibility() == View.GONE){
                    tip2Detail.setVisibility(View.VISIBLE);
                } else{
                    tip2Detail.setVisibility(View.GONE);
                }
            }
        });

        tip3Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tip3Detail.getVisibility() == View.GONE){
                    tip3Detail.setVisibility(View.VISIBLE);
                } else{
                    tip3Detail.setVisibility(View.GONE);
                }
            }
        });

        tip4Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tip4Detail.getVisibility() == View.GONE){
                    tip4Detail.setVisibility(View.VISIBLE);
                } else{
                    tip4Detail.setVisibility(View.GONE);
                }
            }
        });

        tip5Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tip5Detail.getVisibility() == View.GONE){
                    tip5Detail.setVisibility(View.VISIBLE);
                } else{
                    tip5Detail.setVisibility(View.GONE);
                }
            }
        });

        tip6Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tip6Detail.getVisibility() == View.GONE){
                    tip6Detail.setVisibility(View.VISIBLE);
                } else{
                    tip6Detail.setVisibility(View.GONE);
                }
            }
        });

        tip7Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tip7Detail.getVisibility() == View.GONE){
                    tip7Detail.setVisibility(View.VISIBLE);
                } else{
                    tip7Detail.setVisibility(View.GONE);
                }
            }
        });

        tip8Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tip8Detail.getVisibility() == View.GONE){
                    tip8Detail.setVisibility(View.VISIBLE);
                } else{
                    tip8Detail.setVisibility(View.GONE);
                }
            }
        });

        tip9Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tip9Detail.getVisibility() == View.GONE){
                    tip9Detail.setVisibility(View.VISIBLE);
                } else{
                    tip9Detail.setVisibility(View.GONE);
                }
            }
        });

        tip10Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tip10Detail.getVisibility() == View.GONE){
                    tip10Detail.setVisibility(View.VISIBLE);
                } else{
                    tip10Detail.setVisibility(View.GONE);
                }
            }
        });



    }

}

package com.example.jobs4smcyouth.Utilities.EventBus;

import android.view.View;

/**
 * Created by samsiu on 9/3/16.
 */
public class ApplicationRulesClickEvent {

    private View view;

    public ApplicationRulesClickEvent(View view){
        this.view = view;
    }

    public View getView(){
        return view;
    }

    public void setView(View view){
        this.view = view;
    }
}

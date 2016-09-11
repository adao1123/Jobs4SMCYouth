package com.smc.jobs4smcyouth.Utilities.EventBus;

/**
 * Created by samsiu on 9/3/16.
 */
public class MainBus extends RxEventBus{
    public static MainBus instance;

    public static MainBus getInstance(){
        if(instance == null){
            instance = new MainBus();
        }
        return instance;
    }

    private MainBus(){

    }
}

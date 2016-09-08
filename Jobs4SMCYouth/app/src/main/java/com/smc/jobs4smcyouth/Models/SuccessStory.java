package com.smc.jobs4smcyouth.Models;

/**
 * Created by adao1 on 5/21/2016.
 */
public class SuccessStory {
    private String name;
    private String story;

    public SuccessStory(String name, String story) {
        this.name = name;
        this.story = story;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
